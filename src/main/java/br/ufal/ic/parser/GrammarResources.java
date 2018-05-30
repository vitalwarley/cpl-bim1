package br.ufal.ic.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarResources {

    private static final Logger logger = LoggerFactory.getLogger(GrammarResources.class);

    private static final String epsilon = "epsilon";

    private static List<String> grammar;
    private static Set<String> nonTerminals;
    private static Map<String, List<String>> productions = new HashMap<>();

    public static void initGrammar(String grammarPath) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(grammarPath))) {
            grammar = stream.collect(Collectors.toList());

            nonTerminals = grammar.stream().
                    map(s -> {
                        addProduction(s);
                        return s.split(" -> ")[0];
                    }).
                    collect(Collectors.toSet());

        } catch (IOException e) {
            logger.error("Não foi possível ler o arquivo que contém a gramática.", e.getMessage());
            throw new IllegalArgumentException("Path inválido");
        }
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

    public static boolean checkSymbolIsTerminal(String symbol) {
        return !nonTerminals.contains(symbol);
    }

    public static String getEpsilon() {
        return epsilon;
    }

}
