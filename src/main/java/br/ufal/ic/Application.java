package br.ufal.ic;

import br.ufal.ic.lexer.Lexer;
import br.ufal.ic.lexer.Token;
import br.ufal.ic.lexer.TokenCategory;
import br.ufal.ic.parser.GrammarResources;
import br.ufal.ic.parser.Parser;
import br.ufal.ic.parser.ParsingTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static List<Token> tokenList;
    private static List<String> lines;

    public static void main(String[] args) {
        boolean fromCli = true;
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
                doScanner(source, lexer);
                doParser("grammar_ll1.txt", source, lexer);
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
        readFiles(String.join("", path, ""), lexer);
        printLinesFormatted();
    }

    private static void printLinesFormatted(){
        int countLine = 1;

        for(String s : lines){
            System.out.print(String.format("%4d  ", countLine++));
            System.out.println(s);
        }

    }

    private static void doParser(String grammar, String codePath, Lexer lexer) {
        try {
            GrammarResources.initGrammar(grammar);
        } catch (IOException e) {
            logger.error("Path inválido", e.getMessage());
        }

        Parser parser = new Parser(ParsingTable.getParsingTable());

        List<Token> input = tokenList;

        input.add(new Token(TokenCategory.TK_EOF));

        try {
            parser.predictiveParsing(input, lines);
        } catch (EmptyStackException e) {
            logger.debug("error: stack vazia", e.getMessage());
        }
    }

    private static void readFiles(String path, Lexer lexer) {
        try {
            lexer.setFile(new FileInputStream(path));
            lines = getLineByLine(path);
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
            logger.error("Erro ao ler arquivo", e.getMessage());
        }
    }

    private static List<String> getLineByLine(String path){
        List<String> lines = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Não foi possível ler o arquivo que contém a gramática.", e.getMessage());
        }

        return lines;
    }

    private static List<Token> getAllTokens(String path, Lexer lexer) {
        List<Token> allTokens = null;

        try {
            lexer.setFile(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            logger.error("erro na leitura do código fonte!", e.getMessage());
        }

        try {
            Token currentToken;
            allTokens = new ArrayList<>();

            while (lexer.getFile().available() > 0 || lexer.isRollback()) {
                currentToken = lexer.nextToken();
                allTokens.add(currentToken);
            }
        } catch (IOException e) {
            logger.error("erro na leitura do contéudo do código fonte", e.getMessage());
        }

        return allTokens;
    }

    private static int checkRowToken(List<Token> inLineTks, Token currentToken, int actualRow) {
        if (currentToken.getRow() != actualRow) {
            //printTokensInLine(inLineTks);
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
