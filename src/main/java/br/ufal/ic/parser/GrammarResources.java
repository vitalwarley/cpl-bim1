package br.ufal.ic.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarResources {

    private static final String epsilon = "𝛜";

    private static List<String> grammar;
    private static Set<String> nonTerminals;
    private static Map<String, List<String>> productions = new HashMap<>();

    private static Map<String, List<String>> firsts;
    private static Map<String, List<String>> follows;

    public static void initGrammar(String path) {

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

    public static void initFirstsAndFollows() {
        firsts = new HashMap<>();
        follows = new HashMap<>();

        firsts.put("F", Arrays.asList("(", "id"));
        firsts.put("T", Arrays.asList("(", "id"));
        firsts.put("E", Arrays.asList("(", "id"));
        firsts.put("E'", Arrays.asList("+", epsilon));
        firsts.put("T'", Arrays.asList("*", epsilon));

        follows.put("E", Arrays.asList(")", "$"));
        follows.put("E'", Arrays.asList(")", "$"));
        follows.put("T", Arrays.asList("+", ")", "$"));
        follows.put("T'", Arrays.asList("+", ")", "$"));
        follows.put("F", Arrays.asList("+", "*", ")", "$"));
    }

    public static void showFirstsOrFollows(Map<String, List<String>> set, String setName) {
        System.out.println(setName.toUpperCase() + ": ");

        set. // first or follow
                keySet().
                forEach(k -> {
                    System.out.print(k + ": { ");
                    set
                            .get(k)
                            .forEach(f -> System.out.print(f + " "));
                    System.out.println("}");
                });
    }

    private static void addProduction(String rule) {
        String[] parts = rule.split(" -> ");

        if (parts[1].equals("''"))
            parts[1] = epsilon;

        if (productions.get(parts[0]) == null) {
            productions.put(parts[0], new ArrayList<>(Arrays.asList(parts[1])));
        } else {
            productions.get(parts[0]).add(parts[1]);
        }
    }

    public static void showProductions() {
        productions
                .keySet()
                .forEach(k -> productions
                        .get(k)
                        .forEach(p -> System.out.println(k + " -> " + p)));
    }

    public static boolean checkSymbolIsTerminal(String symbol) {
        return !nonTerminals.contains(symbol);
    }

    public static String getEpsilon() {
        return epsilon;
    }

    public static Map<String, List<String>> getProductions() {
        return productions;
    }

    public static Map<String, List<String>> getFirsts() {
        return firsts;
    }

    public static Map<String, List<String>> getFollows() {
        return follows;
    }

}
