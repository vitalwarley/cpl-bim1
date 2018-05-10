package br.ufal.ic.parser;

import br.ufal.ic.lexer.Application;
import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

    private Application application;
    private static Stack<String> stack = new Stack<>();
    private static Hashtable<Pair<String, String>, List<String>> parsingTable = new Hashtable<>();

    //    private static List<String> nonTerminals = Arrays.asList("E", "E'", "T", "T'", "F");
    private static Set<String> nonTerminals;
    private static Map<String, List<String>> productions = new HashMap<>();
    private static List<String> grammar;
    private static HashMap<String, List<String>> first;
    private static HashMap<String, List<String>> followt;


    public static void main(String[] args) {
        /*initParsingTableTest();
        String[] input = {"id", "+", "id", "*", "id", "$"};
        try {
            predictiveParsing(input);
        } catch (EmptyStackException e) {
            System.err.println("erro: stack vazia");
        }
        */
        initGrammar("grammar_ll1.txt");
    }

    private static void initGrammar(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            grammar = stream.collect(Collectors.toList());

            nonTerminals = grammar.stream().
                    map(s -> {
                        addProduction(s);
                        return s.split(" -> ")[0];
                    }).
                    collect(Collectors.toSet());

        } catch (IOException e) {
            System.err.println("Não foi possível ler o arquivo que contém a gramática.");
        }
    }

    @SuppressWarnings("unchecked")
    private static void initParsingTableTest() {
        String epsilon = "\uD835\uDEDC";
        parsingTable.put(new Pair("E", "id"), Arrays.asList("T", "E'"));
        parsingTable.put(new Pair("E", "("), Arrays.asList("T", "E'"));
        parsingTable.put(new Pair("E'", "+"), Arrays.asList("+", "T", "E'"));
        parsingTable.put(new Pair("E'", ")"), Arrays.asList(epsilon));
        parsingTable.put(new Pair("E'", "$"), Arrays.asList(epsilon));
        parsingTable.put(new Pair("T", "id"), Arrays.asList("F", "T'"));
        parsingTable.put(new Pair("T", "("), Arrays.asList("F", "T'"));
        parsingTable.put(new Pair("T'", "+"), Arrays.asList(epsilon));
        parsingTable.put(new Pair("T'", "*"), Arrays.asList("*", "F", "T'"));
        parsingTable.put(new Pair("T'", ")"), Arrays.asList(epsilon));
        parsingTable.put(new Pair("T'", "$"), Arrays.asList(epsilon));
        parsingTable.put(new Pair("F", "id"), Arrays.asList("id"));
        parsingTable.put(new Pair("F", "("), Arrays.asList("(", "E", ")"));
    }


    @SuppressWarnings("unchecked")
    public static void predictiveParsing(String[] input) throws EmptyStackException {
        int count = 0;
        String next = input[count];
        stack.add("$");
        stack.add("E");
        String X = stack.peek();

        Pair<String, String> pair;

        while (!X.equals("$")) {
            pair = new Pair(X, next);
            if (X.equals(next)) {
                System.out.println("match " + X);
                count++;
                next = input[count];
                stack.pop();
            } else if (isTerminal(X)) {
                error();
            } else if (!parsingTable.containsKey(pair)) {
                error();
            } else { // M[X, a] contains production

                System.out.println(X + " -> " + parsingTable.get(pair)
                        .stream()
                        .collect(Collectors.joining()));
                stack.pop();

                List<String> production = parsingTable.get(pair);

                if (!production.contains("\uD835\uDEDC")) // epsilon don't go to the stack
                    stack.addAll(reverseProduction(production));

            }
            X = stack.peek();
        }

        System.out.println("Accepted!");
    }

    private static boolean isTerminal(String symbol) {
        return !nonTerminals.contains(symbol);

    }

    private static void error() {
        System.out.println("deu erro carai");
        System.exit(1);
    }

    private static List<String> reverseProduction(List<String> production) {
        int size = production.size();
        Stack<String> productionReversed = new Stack<>();
        for (int i = size - 1; i >= 0; i--)
            productionReversed.add(production.get(i));

        return productionReversed;
    }


    private static void addProduction(String rule) {
        String[] parts = rule.split(" -> ");
        if (productions.get(parts[0]) == null)
            productions.put(parts[0], new ArrayList<>(Arrays.asList(parts[1])));
        else
            productions.get(parts[0]).add(parts[1]);
    }
}
