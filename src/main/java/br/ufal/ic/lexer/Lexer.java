package br.ufal.ic.lexer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    public Lexer() {

        this.row = 1;
        this.column = -1;
        this.current = "";
        this.rollback = false;

        /* set reserved words */
        reserve( new Word(TokenCategory.TK_DEFMOD, "defmod"));
        reserve( new Word(TokenCategory.TK_DEF, "def"));
        reserve( new Word(TokenCategory.TK_DO, "do"));
        reserve( new Word(TokenCategory.TK_END, "end"));
        reserve( new Word(TokenCategory.TK_ENDMOD, "endmod"));
        reserve( new Word(TokenCategory.TK_UNTIL, "until"));
        reserve( new Word(TokenCategory.TK_REP, "rep"));
        reserve( new Word(TokenCategory.TK_WHEN, "when"));
        reserve( new Word(TokenCategory.TK_RETURN, "return"));
        reserve( new Word(TokenCategory.TK_OTRWISE, "otherwise"));
        reserve( new Word(TokenCategory.TK_VOID, "void"));
        reserve( new Word(TokenCategory.TK_INT, "int"));
        reserve( new Word(TokenCategory.TK_REAL, "real"));
        reserve( new Word(TokenCategory.TK_STR, "str"));
        reserve( new Word(TokenCategory.TK_BOOL, "bool"));
        reserve( new Word(TokenCategory.TK_CHAR, "char"));
        reserve( new Word(TokenCategory.TK_READ, "read"));
        reserve( new Word(TokenCategory.TK_PRINT, "print"));
        reserve( new Word(TokenCategory.TK_LTVREAD, "lastValueRead"));
        reserve( new Word(TokenCategory.TK_TRUE, "true"));
        reserve( new Word(TokenCategory.TK_FALSE, "false"));
        reserve( new Word(TokenCategory.TK_NIL, "nil"));
    }

    private void reserve(Word t) { words.put(t.lexeme, t.category); }

    public Token nextToken() throws IOException {
        Token nextToken = new Token(TokenCategory.TK_EOF);

        while (this.file.available() > 0) {

            boolean found = false;
            if (!rollback) {
                current = current + String.valueOf((char) file.read());
            } else {
                rollback = false;
            }

            this.column++;

            switch (current) {
                case " ": case "\t":
                    current = "";
                    continue;
                case "\n":
                    current = "";
                    this.row++;
                    break;
                case "\r":
                    current = "";
                    this.row++;
                    this.column = -1;
                    continue;
                case ",":
                    nextToken = new Token(TokenCategory.TK_SPTOR, row, column, current);
                    current = "";
                    found = true;
                    break;
                case ":":
                    nextToken = new Token(TokenCategory.TK_DPTS, row, column, current);
                    current = "";
                    found = true;
                    break;
                case ";":
                    nextToken = new Token(TokenCategory.TK_PVGL, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "(":
                    nextToken = new Token(TokenCategory.TK_ABPAR, row, column, current);
                    current = "";
                    found = true;
                    break;
                case ")":
                    nextToken = new Token(TokenCategory.TK_FCPAR, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "[":
                    nextToken = new Token(TokenCategory.TK_ABCOL, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "]":
                    nextToken = new Token(TokenCategory.TK_FCCOL, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "+": case "-":
                    nextToken = new Token(TokenCategory.TK_OPA, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "*": case "/":
                    nextToken = new Token(TokenCategory.TK_OPM, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "=":
                    if ((char) file.read() == '=') {
                        nextToken = new Token(TokenCategory.TK_REL2, row, column, current);
                    } else {
                        nextToken = new Token(TokenCategory.TK_ATR, row, column, current);
                    }
                    current = "";
                    found = true;
                    break;
                case "!=":
                    nextToken = new Token(TokenCategory.TK_REL2, row, column, current);
                    current = "";
                    found = true;
                    break;
                case ">": case "<": case ">=": case "<=":
                    nextToken = new Token(TokenCategory.TK_REL, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "&&":
                    nextToken = new Token(TokenCategory.TK_AND, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "||":
                    nextToken = new Token(TokenCategory.TK_OR, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "~":
                    nextToken = new Token(TokenCategory.TK_NOT, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "$":
                    nextToken = new Token(TokenCategory.TK_CONCAT, row, column, current);
                    current = "";
                    found = true;
                    break;
                case "#":
                    while((char) file.read() != '\n');

                    this.row++;
                    this.column = -1;
                    current = "";
                    continue;
                case "\'":
                    char c = (char) file.read();
                    while (c != '\'') {
                        column++;
                        current = current + c;
                        c = (char) file.read();
                    }

                    if (current.matches("'(.?)'")) {
                        nextToken = new Token(TokenCategory.TK_CTECHAR, row, column, getChar(current));
                        current = "";
                        found = true;
                    }

                    break;
                case "\"":

                    char d = (char) file.read();
                    while (d != '\"') {
                        column++;
                        current = current + d;
                        d = (char) file.read();
                    }

                    current = current + d;

                    System.out.println(current);

                    if (current.matches("\"(.*)\"")) {
                        nextToken = new Token(TokenCategory.TK_CTESTR, row, column, getChar(current));
                        current = "";
                        found = true;
                    }
                    break;
            }
            if (found) {
                return nextToken;
            } else {
                if (current.matches("\\d+")) {
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

                if (current.matches("[a-zA-Z][\\d[a-z][A-Z]]*")) {
                    if (words.containsKey(current)) {
                        nextToken = new Token(words.get(current), row, column, current);
                        current = "";
                        return nextToken;
                    } else {
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
            }
        }
        return null;
    }


    public static void main(String[] args) {
        boolean DEBUG = true;
        Lexer lexer = new Lexer();

        if (!DEBUG) {
            if (args.length <= 0) {
                System.err.println("Uso: hapais <arquivo>.hs --fly <opcional>");
            }
            if (args.length > 1) {
                String opcao = args[1];
                if (opcao.equals("--fly")) {

                }
            }
            String nome = args[0];
        }

        try {
            String path = "/Users/dayvsonsales/cpl-bim1/examples/shell.hs";
            lexer.setFile(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while (lexer.getFile().available() > 0) {
                System.out.println(lexer.nextToken());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getChar(String c){
        return c.substring(1, c.length()-1);
    }

    public FileInputStream getFile() {
        return file;
    }

    public void setFile(FileInputStream file) {
        this.file = file;
    }
}
