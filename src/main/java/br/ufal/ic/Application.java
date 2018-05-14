package br.ufal.ic;

import br.ufal.ic.lexer.Lexer;
import br.ufal.ic.lexer.Token;
import br.ufal.ic.lexer.TokenCategory;
import br.ufal.ic.parser.GrammarResources;
import br.ufal.ic.parser.Parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
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
        String path = "/Users/dayvsonsales/";
        //String path = "/home/lativ/IdeaProjects/";

        if (fromCli) {
            if (args.length <= 0) {
                System.err.println("Usage: hapais <file>.hs");
            } else {
                String source = args[0];
                System.out.println("Start: " + source);
                readFiles(source, lexer);
                System.out.println("End: " + source);
            }
        } else {
            /*
             * Example codes.
             */
            System.out.println("Start: ");
            doScanner(path, lexer);
            doParser("grammar_ll1.txt", path, lexer);
            System.out.println("End: ");
            /*System.out.println("Start: fib.hs");
            readFiles(String.join("", path, "cpl-bim1/examples/fib.hs"), new Lexer());
            System.out.println("End: fib.hs");
            System.out.println("Start: shell.hs");
            readFiles(String.join("", path, "cpl-bim1/examples/shell.hs"), new Lexer());
            System.out.println("End: shell.hs");*/
        }
    }

    private static void doScanner(String path, Lexer lexer) {
        readFiles(String.join("", path, "cpl-bim1/examples/shell.hs"), lexer);
    }

    private static void doParser(String grammar, String codePath, Lexer lexer) {
        GrammarResources.initGrammar(grammar);
        Parser.fillParsingTable();

        List<Token> input = tokenList;

        input.add(new Token(TokenCategory.TK_EOF));

        /*System.out.println();
        System.out.println("MOVES MADE for input < " + input.stream()
                .map(t -> {return t.getTag().getValue(); })
                .filter(item -> !item.equals("EOF"))
                .collect(Collectors.joining(" ")) + " >: ");*/

        try {
            Parser.predictiveParsing(input);
        } catch (EmptyStackException e) {
            System.err.println("erro: stack vazia");
        }

    }

    private static void readFiles(String path, Lexer lexer) {
        try {
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

            if (currentToken != null && (currentToken.getTag() != TokenCategory.TK_EOF)) {
                tokenList.add(new Token(TokenCategory.TK_EOF, currentToken.getRow(), currentToken.getColumn() + 1, ""));
                System.out.println(tokenList.get(tokenList.size() - 1));
            }
            /* Print last token: EOF */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Token> getAllTokens(String path, Lexer lexer) {
        List<Token> allTokens = null;

        try {
            lexer.setFile(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            System.err.println("erro na leitura do código fonte!");
        }

        try {
            Token currentToken;
            allTokens = new ArrayList<>();

            while (lexer.getFile().available() > 0 || lexer.isRollback()) {
                currentToken = lexer.nextToken();
                allTokens.add(currentToken);
            }
        } catch (IOException e) {
            System.err.println("erro na leitura do contéudo do código fonte");
        }

        return allTokens;
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
                .map(tk -> tk.getValue() + " ")
                .collect(Collectors.toList());

        System.out.print(String.format("%4d  ", inLineTks.get(0).getRow()));
        tks.forEach(System.out::print);
        System.out.println();

    }
}
