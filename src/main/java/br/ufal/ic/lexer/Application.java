package br.ufal.ic.lexer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    private static List<Token> tokenList;

    public static void main(String[] args) {
        /* To show the files that are being read */
        boolean fromCli = false;
        /* Lexical analyser  */
        Lexer lexer = new Lexer();
        /* List of tokens that have been identified */
        tokenList = new ArrayList<>();
        /* Path to language examples used to test the scanner */
        //String path = "/Users/dayvsonsales/";
        String path = "/home/lativ/IdeaProjects/";

        if (fromCli) {
            if (args.length <= 0) {
                System.err.println("Usage: hapais <file>.hs");
            } else {
                String source = args[0];
                System.out.println("Start: " + source);
                readFiles(source, new Lexer());
                System.out.println("End: " + source);
            }
        } else {
            /*
             * Example codes.
             */
            System.out.println("Start: hello.hs");
            readFiles(String.join("", path, "cpl-bim1/examples/hello.hs"), new Lexer());
            System.out.println("End: hello.hs");
            System.out.println("Start: fib.hs");
            readFiles(String.join("", path, "cpl-bim1/examples/fib.hs"), new Lexer());
            System.out.println("End: fib.hs");
            System.out.println("Start: shell.hs");
            readFiles(String.join("", path, "cpl-bim1/examples/shell.hs"), new Lexer());
            System.out.println("End: shell.hs");
        }
    }

    private static void readFiles(String name, Lexer lexer) {
        try {
            String path = name;
            lexer.setFile(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            /* Take next token while there is data on the file to be read */
            Token currentToken = null;
            // new
            List<Token> inLineTks = new ArrayList<>();
            int actualRow = 1;

            while (lexer.getFile().available() > 0 || lexer.isRollback()) {
                currentToken = lexer.nextToken();
                tokenList.add(currentToken);
                /* Changes to comply with 2nd bimester work:
                 *
                 *   [001, 001] (0001,  TK_DEFMOD) {defmod}
                 *   [001, 008] (0000,      TK_ID) {Shellsort}
                 *   [001, 018] (0003,      TK_DO) {do}
                 *
                 *   would be now this
                 *
                 *   001  TK_DEFMOD TK_ID TK_DO
                 *
                 * */
                actualRow = checkRowToken(inLineTks, currentToken, actualRow);
           }
            if (currentToken.getTag() != TokenCategory.TK_EOF) {
                tokenList.add(new Token(TokenCategory.TK_EOF, currentToken.getRow(), currentToken.getColumn() + 1, ""));
                System.out.println(tokenList.get(tokenList.size() - 1));
            }
            /* Print last token: EOF */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int checkRowToken(List<Token> inLineTks, Token currentToken, int actualRow) {
        if (currentToken.getRow() != actualRow) {
            printTokensInLine(inLineTks);
            inLineTks.clear();
        }

        inLineTks.add(currentToken);

        return currentToken.getRow();
    }

    private static void printTokensInLine(List<Token> inLineTks) {

        List<String> tks = inLineTks
                .stream()
                .map(tk -> tk.getTag().toString() + " ")
                .collect(Collectors.toList());

        System.out.print(String.format("%04d  ", inLineTks.get(0).getRow()));
        tks.forEach(System.out::print);
        System.out.println();

    }


}
