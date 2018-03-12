package br.ufal.ic.lexer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static List<Token> tokenList;

    public static void main(String[] args) {
        boolean DEBUG = true;
        Lexer lexer = new Lexer();
        tokenList = new ArrayList<>();

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

        if (DEBUG) {
            System.out.println("Inicio hello.hs");
            readFiles("/Users/dayvsonsales/cpl-bim1/examples/hello.hs", new Lexer());
            System.out.println("Fim hello.hs");
            System.out.println("Inicio fib.hs");
            readFiles("/Users/dayvsonsales/cpl-bim1/examples/fib.hs", new Lexer());
            System.out.println("Fim fib.hs");
            System.out.println("Inicio shell.hs");
            readFiles("/Users/dayvsonsales/cpl-bim1/examples/shell.hs", new Lexer());
            System.out.println("Fim shell.hs");
            System.out.println("Inicio test.hs");
            readFiles("/Users/dayvsonsales/cpl-bim1/examples/test.hs", new Lexer());
            System.out.println("Fim test.hs");
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
