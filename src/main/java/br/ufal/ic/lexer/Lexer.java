package br.ufal.ic.lexer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import static br.ufal.ic.lexer.i18n.MessageBR.*;

/* Main class, the lexical analyser. */
public class Lexer {

    private int row;            /* Row being read */
    private int column;         /* Column that 'current' is at */
    private String current;     /* Default */
    private boolean rollback;   /* Will tell us if we have to rollback the reading in some cases */

    private FileInputStream file;

    /* Store reserved words and identifiers */
    private Hashtable<String, TokenCategory> words = new Hashtable<>();

    private final String REGEX_CHAR = "\'(.?)\'";
    private final String REGEX_STR = "\"[\\\\d a-zA-Z_\\.,:;!+-\\?<>=\\(\\)\\[\\]{}\\'\"@%\\^\\\\]*\"";
    private final String REGEX_IDENTIFIER = "[a-zA-Z][\\d[a-z][A-Z]]*";
    private final String REGEX_NUMBER = "\\d+";
    private final String REGEX_REAL = "\\d+\\.\\d+";

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

        while (this.file.available() > 0) {     /* While we have something to read... */

            boolean found = false;  /* Found nothing, yet... */

            if (!rollback) {    /* At first, don't to rollback: we are trying to read a new lexeme */
                current = current + String.valueOf((char) file.read());
                /* At first, current is empty, so... */
                /* 'current' gets the char that we are gonna read */
                /* Because we are trying to read a new lexeme, we get the next char ahead. */
                /* Here we are accumulating the chars we read */
            } else {
                rollback = false;
            }

            this.column++;      /* Reading first column */

            switch (current) {  /* Here we decide what to do according to what we have read previously */
                /* In case we read spaces, tabs, or newlines we just continue to reading */
                case " ":
                case "\t":
                    current = "";
                    continue;
                case "\n":
                case "\r":
                    current = "";
                    this.row++; /* But we increment a row each newline */
                    this.column = 0;
                    continue;
                case "=":
                    /* Read again */
                    char t = (char) file.read();    /* We cast to char because file.read() returns the data in bytes. */
                    if (t == '=') {     /* If next lexeme is '=', we create a new Token */
                        nextToken = new Token(TokenCategory.TK_REL2, row, column - (current.length() - 1), current);
                        current = "";
                    } else {
                        nextToken = new Token(TokenCategory.TK_ATR, row, column, current);
                        current = Character.toString(t);
                        rollback = true;
                    }
                    found = true;
                    break;
                case "#":   /* This is a comment in Hapais. */
                    /* Therefore, lets read till the end of line AND till there is something to read */
                    while ((char) file.read() != '\n' && this.file.available() > 0)
                        ; /* This second term prevent loops*/

                    this.row++;
                    this.column = 0;
                    current = "";
                    continue;
                case "\'":
                    char c = (char) file.read();

                    if(c == '\n'){
                        nextToken = new Token(TokenCategory.TK_CTECHAR, row, column - (current.length() - 1), getCharWithoutFirst(current), true, CTECHAR_ERR);
                        current = Character.toString(c);
                        rollback = true;
                        return  nextToken;
                    }

                    if (c == '\\') {
                        char aux = c;
                        column++;
                        if (this.file.available() > 0) {
                            column++;
                            c = (char) file.read();
                            if (c == '\"' || c == '\'' || c == '\\') {
                                current = current + c;
                            } else {
                                current = current + aux;
                            }
                        } else {
                            current = current + aux;
                        }
                    } else { /* c is not a escape */
                        /*
                         * If there is something to read and c is not a simple quote...
                         * Here we check if it is not a empty char.
                         * If so, accumulate.
                         */
                        if (this.file.available() > 0 && (c != '\'')) {
                                column++;
                                current = current + c;
                        }
                    }

                    /* Read next char if there is something to read */
                    if (this.file.available() > 0) {
                        c = (char) file.read();
                    }

                    /* Close char with a simple quote */
                    if (c == '\'') {
                        column++;
                        current = current + c;
                    }

                    /* Try to match what we currently have in 'current' */
                    if (current.matches(REGEX_CHAR)) {
                        nextToken = new Token(TokenCategory.TK_CTECHAR, row, column - (current.length() - 1),
                                getChar(current));
                        current = "";
                        found = true;
                    } else {
                        /* If we didn't get something like 'a',
                         * we set error true and take the first char we encounter: 'a<something> */
                        nextToken = new Token(TokenCategory.TK_CTECHAR, row, column - (current.length() - 1),
                                getCharWithoutFirst(current), true, CTECHAR_ERR);
                        current = Character.toString(c);
                        found = true;
                        rollback = true;
                    }

                    break;
                case "\"":  /* This double quote can define a new ctStr */
                    char d = (char) file.read(); /* Read next char */
                    /**
                    * While d IS NOT a double quote (that is, we haven't reached the string's end),
                     * AND a new line char
                     * AND there is something to read
                     * ...
                    * */
                    while (d != '\"' && d != '\n' && this.file.available() > 0) {
                        column++;
                        current = current + d;      /* Accumulate current */
                        d = (char) file.read();     /* Read next char */
                        /* If next char d is closing quote AND char */
                        if (d == '\"' && current.charAt(current.length() - 1) == '\\') {
                            int ind = current.lastIndexOf("\\");
                            current = new StringBuilder(current).replace(ind, ind + 1, "\"").toString();
                            d = (char) file.read();
                        }
                    }

                    if (d != '\n') {
                        current = current + d;
                        column++;
                    }

                    if (current.matches(REGEX_STR)) {
                        nextToken = new Token(TokenCategory.TK_CTESTR, row, column - (current.length() - 1), getChar(current));
                    } else {
                        nextToken = new Token(TokenCategory.TK_CTESTR, row, column - (current.length() - 1), getCharWithoutFirst(current), true, CTESTR_ERR);
                    }

                    current = "";
                    found = true;

                    if (d == '\n') {
                        current = Character.toString(d);
                        rollback = true;
                    }

                    break;
            }

            if (found)
                return nextToken;

            if (words.containsKey(current)) {
                nextToken = new Token(words.get(current), row, column - (current.length() - 1), current);
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
                    nextToken = new Token(TokenCategory.TK_CTEINT, row, column - (current.length() - 1), current);
                    current = Character.toString(number);
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

                if (current.matches(REGEX_REAL)) {
                    nextToken = new Token(TokenCategory.TK_CTEREAL, row, column - (current.length() - 1), current);
                } else {
                    nextToken = new Token(TokenCategory.TK_CTEREAL, row, column - (current.length() - 1), current, true, CTEREAL_ERR);
                }

                current = Character.toString(number);

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
                    nextToken = new Token(words.get(current), row, column - (current.length() - 1), current);
                    current = Character.toString(e);
                    return nextToken;
                }

                nextToken = new Token(TokenCategory.TK_ID, row, column - (current.length() - 1), current);
                current = Character.toString(e);
                return nextToken;
            }
        }

        if (!current.equals("")) {
            return new Token(TokenCategory.TK_UKN, row, column - (current.length() - 1), current);
        }

        if (column == 0)
            column = 1;

        return new Token(TokenCategory.TK_EOF, row, column, current);
    }

    private String getChar(String c) {
        return c.substring(1, c.length() - 1);
    }

    private String getCharWithoutFirst(String c) {
        return c.substring(1, c.length());
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
