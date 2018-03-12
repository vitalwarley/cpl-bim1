package br.ufal.ic.lexer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static List<Token> tokenList;

    public static void main(String[] args) {
        /* To show the files that are being read */
        boolean DEBUG = true;
        /* Lexical analyser  */
        Lexer lexer = new Lexer();
        /* List of tokens that have been identified */
        tokenList = new ArrayList<>();
        /* Path to language examples used to test the scanner */
        //String path = "/Users/dayvsonsales/cpl-bim1/examples/";
        String path = "/home/lativ/IdeaProjects/cpl-bim1/examples/";

        if (!DEBUG) {
            if (args.length <= 0) {
                System.err.println("Usage: hapais <file>.hs --fly <optional>");
            }
            if (args.length > 1) {
                String opcao = args[1];
                if (opcao.equals("--fly")) {

                }
            }
            // String nome = args[0];
        }

        if (DEBUG) {
            System.out.println("Start: hello.hs");
            readFiles(path.join("","hello.hs"), new Lexer());
            System.out.println("End: hello.hs");
            System.out.println("Start: fib.hs");
            readFiles(path.join("","fib.hs"), new Lexer());
            System.out.println("End: fib.hs");
            System.out.println("Start: shell.hs");
            readFiles(path.join("","shell.hs"), new Lexer());
            System.out.println("End: shell.hs");
            System.out.println("Start: test.hs");
            readFiles(path.join("","test.hs"), new Lexer());
            System.out.println("End: test.hs");
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
            /* Take next token while there is data on file to be read */
            while (lexer.getFile().available() > 0) {
                Token currentToken = lexer.nextToken();
                tokenList.add(currentToken);
                System.out.println(currentToken);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
