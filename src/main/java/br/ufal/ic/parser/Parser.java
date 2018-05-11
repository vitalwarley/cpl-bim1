package br.ufal.ic.parser;

import br.ufal.ic.lexer.Application;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    private static Stack<String> stack = new Stack<>();
    private static Hashtable<Pair<String, String>, List<String>> parsingTable = new Hashtable<>();
    private Application application;

    public static void main(String[] args) {
        GrammarResources.initGrammar("grammar_428_aho.txt");
        GrammarResources.showProductions();
        GrammarResources.initFirstsAndFollows();
        GrammarResources.showFirstsAndFollows();
        initParsingTable();
        showParsingTable();

        System.out.println("MOVES MADE: ");
        String[] input = {"id", "+", "id", "*", "id", "$"};
        try {
            predictiveParsing(input);
        } catch (EmptyStackException e) {
            System.err.println("erro: stack vazia");
        }

    }

    private static void initParsingTable() {

        /*  For each production A -> alpha of the grammar, do the following:
         *
         * 1. For each terminal a in FIRST(alpha), add A -> alpha to M[A, a].
         * 2. If epsilon is in FIRST(alpha), then for each terminal b in FOLLOW(A), add A -> alpha
         *    to M[A, b]. If epsilon is in FIRST(alpha) and $ is in FOLLOW(A), add A -> alpha
         *    to M[A, $] as well.
         */
        GrammarResources.getProductions()
                .keySet()
                .forEach(nT ->
                        GrammarResources.
                                getProductions().
                                get(nT).
                                forEach(p -> doFirstAndFollow(nT, Arrays.asList(p.split(" ")))
                                )
                );

    }

    private static void doFirstAndFollow(String nonTerminal, List<String> symbolsInProduction) {
        String actualSymbol = symbolsInProduction.get(0);

        if (actualSymbol  == GrammarResources.getEpsilon())
            GrammarResources.
                    getFollows().
                    get(nonTerminal).
                    forEach(
                            term ->
                                    addToParsingTable(nonTerminal, term, Arrays.asList(GrammarResources.getEpsilon()))

                    );
        else if (isTerminal(actualSymbol))
            addToParsingTable(nonTerminal, actualSymbol, symbolsInProduction);
        else
            GrammarResources.
                    getFirsts().
                    get(actualSymbol).
                    forEach(
                            first -> {
                                if (first == GrammarResources.getEpsilon())
                                    GrammarResources.getFollows().
                                            get(actualSymbol).
                                            forEach(
                                                    follow ->
                                                        addToParsingTable(nonTerminal,
                                                                follow,
                                                                symbolsInProduction
                                                        )

                                            );
                                else
                                    addToParsingTable(nonTerminal, first, symbolsInProduction);
                            }
                    );

    }

    @SuppressWarnings("unchecked")
    private static void addToParsingTable(String nonTerminal, String terminal, List<String> production) {
        Pair pair = new Pair(nonTerminal, terminal);
        parsingTable.put(pair, production);
    }

    private static void showParsingTable() {
        System.out.println("PARSING TABLE: ");
        parsingTable
                .keySet()
                .forEach(k -> {
                    System.out.print("(" + k.getKey() + ", " + k.getValue() + ") : ");
                    System.out.println(parsingTable.get(k));
                });
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
                System.out.println(X);
                error("isTerminal");
            } else if (!parsingTable.containsKey(pair)) {
                error("!parsingTable.containsKey");
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
        return GrammarResources.checkSymbolIsTerminal(symbol);
    }

    private static void error(String place) {
        System.out.println("[" + place + "] deu erro carai");
        System.exit(1);
    }

    private static List<String> reverseProduction(List<String> production) {
        int size = production.size();
        Stack<String> productionReversed = new Stack<>();
        for (int i = size - 1; i >= 0; i--)
            productionReversed.add(production.get(i));

        return productionReversed;
    }
}
