package br.ufal.ic.lexer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

/* Main class, the lexical analyser. */
public class Lexer {

    private int row;
    private int column;

    private FileInputStream file;

    private String current;
    private boolean rollback;

    /* store reserved words and identifiers */
    private Hashtable<String, TokenCategory> words = new Hashtable<>();

    private final String REGEX_CHAR = "\'(.?)\'";
    private final String REGEX_STR = "[\\\\d a-zA-Z_\\.,:;!+-\\?<>=\\(\\)\\[\\]{}\\'\"@%\\^\\\\]*";
    private final String REGEX_IDENTIFIER = "[a-zA-Z][\\d[a-z][A-Z]]*";
    private final String REGEX_NUMBER = "\\d+";

    public Lexer() {

        this.row = 1;
        this.column = 0;
        this.current = "";
        this.rollback = false;

        /* set reserved words */
        setReservedWords();
    }

    public Token nextToken() throws IOException {
        Token nextToken = null;

        while (this.file.available() > 0) {

            boolean found = false;

            if (!rollback) {
                current = current + String.valueOf((char) file.read());
            } else {
                rollback = false;
            }

            this.column++;

            switch (current) {
                case " ":
                case "\t":
                    current = "";
                    continue;
                case "\n":
                case "\r":
                    current = "";
                    this.row++;
                    this.column = 0;
                    continue;
                case "=":
                    char t = (char) file.read();
                    if (t == '=') {
                        nextToken = new Token(TokenCategory.TK_REL2, row, column, current);
                        current = "";
                    } else {
                        nextToken = new Token(TokenCategory.TK_ATR, row, column, current);
                        current = Character.toString(t);
                        rollback = true;
                    }
                    found = true;
                    break;
                case "#":
                    while ((char) file.read() != '\n' && this.file.available() > 0) ;

                    this.row++;
                    this.column = 0;
                    current = "";
                    continue;
                case "\'":
                    char c = (char) file.read();
                    while (c != '\'' && this.file.available() > 0) {
                        column++;
                        current = current + c;
                        c = (char) file.read();
                    }

                    column++;
                    current = current + c;

                    if (current.matches(REGEX_CHAR)) {
                        nextToken = new Token(TokenCategory.TK_CTECHAR, row, column, getChar(current));
                        current = "";
                        found = true;
                    }

                    break;
                case "\"":
                    char d = (char) file.read();
                    while (d != '\"' && this.file.available() > 0) {
                        column++;
                        current = current + d;
                        d = (char) file.read();
                    }
                    column++;
                    current = current + d;

                    if (current.matches(REGEX_STR)) {
                        nextToken = new Token(TokenCategory.TK_CTESTR, row, column, getChar(current));
                        current = "";
                        found = true;
                    }

                    break;
            }

            if (found)
                return nextToken;

            if (words.containsKey(current)) {
                nextToken = new Token(words.get(current), row, column, current);
                current = "";
                return nextToken;
            }

            if (current.matches(REGEX_NUMBER)) {
                char number = (char) file.read();

                while (Character.isDigit(number)) {
                    column++;
                    current = current + number;
                    number = (char) file.read();
                }

                if (number != '.') {
                    rollback = true;
                    nextToken = new Token(TokenCategory.TK_CTEINT, row, column, current);
                    current = "";
                    return nextToken;
                }
                column++;
                current = current + number;

                number = (char) file.read();

                while (Character.isDigit(number)) {
                    column++;
                    current = current + number;
                    number = (char) file.read();
                }

                rollback = true;

                nextToken = new Token(TokenCategory.TK_CTEREAL, row, column, current);
                current = "";
                return nextToken;
            }

            if (current.matches(REGEX_IDENTIFIER)) {
                char e = (char) file.read();
                while (Character.isLetterOrDigit(e)) {
                    column++;
                    current = current + e;
                    e = (char) file.read();
                }

                rollback = true;

                if (words.containsKey(current)) {
                    nextToken = new Token(words.get(current), row, column, current);
                    current = Character.toString(e);
                    return nextToken;
                }

                nextToken = new Token(TokenCategory.TK_ID, row, column, current);
                current = Character.toString(e);
                return nextToken;
            }
        }

        if (!current.equals("")) {
            return new Token(TokenCategory.TK_UNKNOW, row, column, current);
        }

        if (column == 0)
            column = 1;

        return new Token(TokenCategory.TK_EOF, row, column, current);
    }

    private String getChar(String c) {
        return c.substring(1, c.length() - 1);
    }

    public FileInputStream getFile() {
        return file;
    }

    public void setFile(FileInputStream file) {
        this.file = file;
    }

    private void reserve(Word t) {
        words.put(t.getLexeme(), t.getCategory());
    }

    private void setReservedWords() {
        reserve(new Word(TokenCategory.TK_DEFMOD, "defmod"));
        reserve(new Word(TokenCategory.TK_DEF, "def"));
        reserve(new Word(TokenCategory.TK_DO, "do"));
        reserve(new Word(TokenCategory.TK_END, "end"));
        reserve(new Word(TokenCategory.TK_ENDMOD, "endmod"));
        reserve(new Word(TokenCategory.TK_UNTIL, "until"));
        reserve(new Word(TokenCategory.TK_REP, "rep"));
        reserve(new Word(TokenCategory.TK_WHEN, "when"));
        reserve(new Word(TokenCategory.TK_RETURN, "return"));
        reserve(new Word(TokenCategory.TK_OTRWISE, "otherwise"));
        reserve(new Word(TokenCategory.TK_VOID, "void"));
        reserve(new Word(TokenCategory.TK_INT, "int"));
        reserve(new Word(TokenCategory.TK_REAL, "real"));
        reserve(new Word(TokenCategory.TK_STR, "str"));
        reserve(new Word(TokenCategory.TK_BOOL, "bool"));
        reserve(new Word(TokenCategory.TK_CHAR, "char"));
        reserve(new Word(TokenCategory.TK_READ, "read"));
        reserve(new Word(TokenCategory.TK_PRINT, "print"));
        reserve(new Word(TokenCategory.TK_LTVREAD, "lastValueRead"));
        reserve(new Word(TokenCategory.TK_TRUE, "true"));
        reserve(new Word(TokenCategory.TK_FALSE, "false"));
        reserve(new Word(TokenCategory.TK_NIL, "nil"));


        reserve(new Word(TokenCategory.TK_SPTOR, ","));
        reserve(new Word(TokenCategory.TK_DPTS, ":"));
        reserve(new Word(TokenCategory.TK_PVGL, ";"));
        reserve(new Word(TokenCategory.TK_ABPAR, "("));
        reserve(new Word(TokenCategory.TK_FCPAR, ")"));
        reserve(new Word(TokenCategory.TK_ABCOL, "["));
        reserve(new Word(TokenCategory.TK_FCCOL, "]"));
        reserve(new Word(TokenCategory.TK_OPA, "+"));
        reserve(new Word(TokenCategory.TK_OPA, "-"));
        reserve(new Word(TokenCategory.TK_OPM, "*"));
        reserve(new Word(TokenCategory.TK_OPM, "/"));
        reserve(new Word(TokenCategory.TK_REL2, "!="));
        reserve(new Word(TokenCategory.TK_REL, ">"));
        reserve(new Word(TokenCategory.TK_REL, ">="));
        reserve(new Word(TokenCategory.TK_REL, "<"));
        reserve(new Word(TokenCategory.TK_REL, "<="));
        reserve(new Word(TokenCategory.TK_AND, "&&"));
        reserve(new Word(TokenCategory.TK_OR, "||"));
        reserve(new Word(TokenCategory.TK_NOT, "~"));
        reserve(new Word(TokenCategory.TK_CONCAT, "$"));

    }

}
