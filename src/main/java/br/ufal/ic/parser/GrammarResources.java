package br.ufal.ic.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarResources {

    private static Set<String> nonTerminals;
    private static Map<String, List<String>> productions = new HashMap<>();

    private static List<String> grammar;
    private static HashMap<String, List<String>> first;
    private static HashMap<String, List<String>> follow;

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

    private static void addProduction(String rule) {
        String[] parts = rule.split(" -> ");
        if (productions.get(parts[0]) == null)
            productions.put(parts[0], new ArrayList<>(Arrays.asList(parts[1])));
        else
            productions.get(parts[0]).add(parts[1]);
    }

    public static boolean checkSymbolIsTerminal(String symbol) {
        return !nonTerminals.contains(symbol);
    }
}
