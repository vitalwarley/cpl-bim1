package br.ufal.ic.parser;

import br.ufal.ic.lexer.models.Token;
import br.ufal.ic.lexer.enums.TokenCategory;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    private Stack<String> stack = new Stack<>();
    private final Hashtable<Pair<String, String>, List<String>> parsingTable;

    public Parser(Hashtable<Pair<String, String>, List<String>> parsingTable) {
        this.parsingTable = parsingTable;
    }

    @SuppressWarnings("unchecked")
    public boolean predictiveParsing(List<Token> input, List<String> lines) throws EmptyStackException {

        if (input.get(input.size() - 1).getTag() != TokenCategory.TK_EOF) {
            return false;
        }

        int count = 0;

        Token next = input.get(count);

        if (next.isError()) {
            error(next.getMsg());
            return false;
        }

        stack.push("EOF");
        stack.push("S");

        String X = stack.peek();

        Pair<String, String> pair;

        int currentLine = 0;

        while (!X.equals("EOF")) {

            if (next.isError()) {
                error(next.getMsg());
                return false;
            }

            pair = new Pair(X, next.getTag().getValue());

            if (next.getRow() != currentLine) {
                System.out.println();
                System.out.print(String.format("%4d  ", next.getRow()));
                System.out.println(lines.get(next.getRow() - 1));
                currentLine = next.getRow();
                System.out.println();
            }

            if (X.equals(next.getTag().getValue())) {
                System.out.print(String.format("%14s", ""));
                System.out.println(next);
                count++;
                next = input.get(count);
                stack.pop();

            } else if (isTerminal(X)) {
                System.out.println(X);
                error("isTerminal");
                return false;
            } else if (!parsingTable.containsKey(pair)) {
                error("!parsingTable.containsKey");
                return false;
            } else { // M[X, a] contains production
                System.out.print(String.format("%10s", ""));

                System.out.println(X + " = " + parsingTable.get(pair)
                        .stream()
                        .map(s -> {
                            if (String.valueOf(s.charAt(0)).equals(String.valueOf(s.charAt(0)).toLowerCase()) && !s.equals(GrammarResources.getEpsilon())) {
                                return "'" + s + "'";
                            }
                            return s;
                        })
                        .collect(Collectors.joining(" ")));

                stack.pop();

                List<String> production = parsingTable.get(pair);

                if (!production.contains(GrammarResources.getEpsilon())) // epsilon don't go to the stack
                    stack.addAll(reverseProduction(production));

            }

            X = stack.peek();
        }

        System.out.println("Aceito!");
        return true;
    }

    private static boolean isTerminal(String symbol) {
        return GrammarResources.checkSymbolIsTerminal(symbol);
    }

    private static void error(String place) {
        System.out.println("[" + place + "] Aconteceu um erro durante a análise sintática");
    }

    private static List<String> reverseProduction(List<String> production) {
        int size = production.size();

        Stack<String> productionReversed = new Stack<>();

        for (int i = size - 1; i >= 0; i--)
            productionReversed.add(production.get(i));

        return productionReversed;
    }
}
