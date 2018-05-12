package br.ufal.ic.parser;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class Parser {

    private static Stack<String> stack = new Stack<>(); // see Deque and ArrayDeque instead
    private static Hashtable<Pair<String, String>, List<String>> parsingTable = new Hashtable<>();

    public static void fillParsingTable() {
        addToParsingTable("S", "defmod", Arrays.asList("defmod", "id", "do", "Global", "RFuncoes", "endmod"));
        addToParsingTable("Global", "endmod", Arrays.asList("RDeclVar"));
        addToParsingTable("Global", "def", Arrays.asList("RDeclVar"));
        addToParsingTable("Global", "int", Arrays.asList("RDeclVar"));
        addToParsingTable("Global", "void", Arrays.asList("RDeclVar"));
        addToParsingTable("Global", "str", Arrays.asList("RDeclVar"));
        addToParsingTable("Global", "real", Arrays.asList("RDeclVar"));
        addToParsingTable("Global", "bool", Arrays.asList("RDeclVar"));
        addToParsingTable("Global", "char", Arrays.asList("RDeclVar"));
        addToParsingTable("Global", "$", Arrays.asList("RDeclVar"));
        addToParsingTable("Global", "EOF", Arrays.asList("RDeclVar"));
        addToParsingTable("RFuncoes", "endmod", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RFuncoes", "def", Arrays.asList("Funcoes", "RFuncoes"));
        addToParsingTable("Funcoes", "def", Arrays.asList("def", "TipoFuncao", "id", "(", "Parametro", ")", "do", "Instrucao", "end"));
        addToParsingTable("Return", "return", Arrays.asList("return", "Exp", ";"));
        addToParsingTable("TipoFuncao", "int", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFuncao", "void", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFuncao", "str", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFuncao", "real", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFuncao", "bool", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFuncao", "char", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("FArr", "id", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "endmod", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "def", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "[", Arrays.asList("[", "]"));
        addToParsingTable("FArr", "int", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "void", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "str", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "real", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "bool", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "char", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", ",", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "$", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("FArr", "EOF", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("Tipo", "int", Arrays.asList("int"));
        addToParsingTable("Tipo", "void", Arrays.asList("void"));
        addToParsingTable("Tipo", "str", Arrays.asList("str"));
        addToParsingTable("Tipo", "real", Arrays.asList("real"));
        addToParsingTable("Tipo", "bool", Arrays.asList("bool"));
        addToParsingTable("Tipo", "char", Arrays.asList("char"));
        addToParsingTable("TipoFixo", "int", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFixo", "void", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFixo", "str", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFixo", "real", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFixo", "bool", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("TipoFixo", "char", Arrays.asList("Tipo", "FArr"));
        addToParsingTable("Parametro", "id", Arrays.asList("id", ":", "TipoFixo", "Parametro"));
        addToParsingTable("Parametro", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("Parametro", ",", Arrays.asList(",", "id", ":", "TipoFixo"));
        addToParsingTable("Instrucao", "id", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "do", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "end", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "return", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "int", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "void", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "str", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "real", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "bool", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "char", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "when", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "otherwise", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "until", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "till", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("Instrucao", "rep", Arrays.asList("Comando", "RInstrucao"));
        addToParsingTable("RInstrucao", "end", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RInstrucao", "otherwise", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RInstrucao", "till", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("Comando", "id", Arrays.asList("id", "RArr", "ComandoX", "Comando"));
        addToParsingTable("Comando", "do", Arrays.asList("DoUntil", "Comando"));
        addToParsingTable("Comando", "end", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("Comando", "return", Arrays.asList("Return"));
        addToParsingTable("Comando", "int", Arrays.asList("TipoFixo", "id", "ComandoA", ";", "Comando"));
        addToParsingTable("Comando", "void", Arrays.asList("TipoFixo", "id", "ComandoA", ";", "Comando"));
        addToParsingTable("Comando", "str", Arrays.asList("TipoFixo", "id", "ComandoA", ";", "Comando"));
        addToParsingTable("Comando", "real", Arrays.asList("TipoFixo", "id", "ComandoA", ";", "Comando"));
        addToParsingTable("Comando", "bool", Arrays.asList("TipoFixo", "id", "ComandoA", ";", "Comando"));
        addToParsingTable("Comando", "char", Arrays.asList("TipoFixo", "id", "ComandoA", ";", "Comando"));
        addToParsingTable("Comando", "when", Arrays.asList("When", "Comando"));
        addToParsingTable("Comando", "otherwise", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("Comando", "until", Arrays.asList("Until", "Comando"));
        addToParsingTable("Comando", "till", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("Comando", "rep", Arrays.asList("Rep", "Comando"));
        addToParsingTable("ComandoX", "id", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "do", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "(", Arrays.asList("(", "ParFunc", ")", ";"));
        addToParsingTable("ComandoX", "end", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "return", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "int", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "void", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "str", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "real", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "bool", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "char", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "=", Arrays.asList("=", "Exp", ";"));
        addToParsingTable("ComandoX", "when", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "otherwise", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "until", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "till", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoX", "rep", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoA", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ComandoA", "=", Arrays.asList("=", "ComandoAX"));
        addToParsingTable("ComandoAX", "id", Arrays.asList("Exp"));
        addToParsingTable("ComandoAX", "(", Arrays.asList("Exp"));
        addToParsingTable("ComandoAX", "~", Arrays.asList("Exp"));
        addToParsingTable("ComandoAX", "-", Arrays.asList("Exp"));
        addToParsingTable("ComandoAX", "cteI", Arrays.asList("Exp"));
        addToParsingTable("ComandoAX", "cteR", Arrays.asList("Exp"));
        addToParsingTable("ComandoAX", "cteStr", Arrays.asList("Exp"));
        addToParsingTable("ComandoAX", "cteChar", Arrays.asList("Exp"));
        addToParsingTable("ComandoAX", "cteBool", Arrays.asList("Exp"));
        addToParsingTable("Atrib", "id", Arrays.asList("Id", "=", "Exp", ";"));
        addToParsingTable("Atrib", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("Id", "id", Arrays.asList("id", "Arr"));
        addToParsingTable("RDeclVar", "endmod", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RDeclVar", "def", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RDeclVar", "int", Arrays.asList("DeclVar", "RDeclVar"));
        addToParsingTable("RDeclVar", "void", Arrays.asList("DeclVar", "RDeclVar"));
        addToParsingTable("RDeclVar", "str", Arrays.asList("DeclVar", "RDeclVar"));
        addToParsingTable("RDeclVar", "real", Arrays.asList("DeclVar", "RDeclVar"));
        addToParsingTable("RDeclVar", "bool", Arrays.asList("DeclVar", "RDeclVar"));
        addToParsingTable("RDeclVar", "char", Arrays.asList("DeclVar", "RDeclVar"));
        addToParsingTable("RDeclVar", "$", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RDeclVar", "EOF", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("DeclVar", "int", Arrays.asList("TipoFixo", "Atrib", ";"));
        addToParsingTable("DeclVar", "void", Arrays.asList("TipoFixo", "Atrib", ";"));
        addToParsingTable("DeclVar", "str", Arrays.asList("TipoFixo", "Atrib", ";"));
        addToParsingTable("DeclVar", "real", Arrays.asList("TipoFixo", "Atrib", ";"));
        addToParsingTable("DeclVar", "bool", Arrays.asList("TipoFixo", "Atrib", ";"));
        addToParsingTable("DeclVar", "char", Arrays.asList("TipoFixo", "Atrib", ";"));
        addToParsingTable("Arr", "[", Arrays.asList("[", "Exp", "]"));
        addToParsingTable("Arr", "=", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "id", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "do", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "(", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "end", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "return", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "[", Arrays.asList("[", "LRArr", "]"));
        addToParsingTable("RArr", "]", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "int", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "void", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "str", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "real", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "bool", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "char", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", ",", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "=", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "when", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "otherwise", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "until", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "till", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "rep", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "||", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "&&", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "opr2", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "opr1", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "$", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "opa", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "opm", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RArr", "EOF", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("LRArr", "id", Arrays.asList("Exp"));
        addToParsingTable("LRArr", "(", Arrays.asList("Exp"));
        addToParsingTable("LRArr", "]", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("LRArr", "~", Arrays.asList("Exp"));
        addToParsingTable("LRArr", "-", Arrays.asList("Exp"));
        addToParsingTable("LRArr", "cteI", Arrays.asList("Exp"));
        addToParsingTable("LRArr", "cteR", Arrays.asList("Exp"));
        addToParsingTable("LRArr", "cteStr", Arrays.asList("Exp"));
        addToParsingTable("LRArr", "cteChar", Arrays.asList("Exp"));
        addToParsingTable("LRArr", "cteBool", Arrays.asList("Exp"));
        addToParsingTable("When", "when", Arrays.asList("when", "(", "Exp", ")", "do", "Otherwise", "end"));
        addToParsingTable("Otherwise", "id", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "do", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "end", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "return", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "int", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "void", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "str", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "real", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "bool", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "char", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "when", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "otherwise", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "until", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("Otherwise", "rep", Arrays.asList("Instrucao", "OtherwiseR"));
        addToParsingTable("OtherwiseR", "end", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("OtherwiseR", "otherwise", Arrays.asList("otherwise", "Instrucao"));
        addToParsingTable("Until", "until", Arrays.asList("until", "(", "Exp", ")", "do", "Instrucao", "end"));
        addToParsingTable("DoUntil", "do", Arrays.asList("do", "Instrucao", "till", "(", "Exp", ")", "end"));
        addToParsingTable("Rep", "rep", Arrays.asList("rep", "(", "VarControl", ",", "Exp", ",", "Id", "=", "Exp", ")",
                "do", "Instrucao", "end"));
        addToParsingTable("VarControl", "id", Arrays.asList("id", ":", "Tipo", "=", "Exp"));
        addToParsingTable("ParFunc", "id", Arrays.asList("RParFunc"));
        addToParsingTable("ParFunc", "(", Arrays.asList("RParFunc"));
        addToParsingTable("ParFunc", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ParFunc", "~", Arrays.asList("RParFunc"));
        addToParsingTable("ParFunc", "-", Arrays.asList("RParFunc"));
        addToParsingTable("ParFunc", "cteI", Arrays.asList("RParFunc"));
        addToParsingTable("ParFunc", "cteR", Arrays.asList("RParFunc"));
        addToParsingTable("ParFunc", "cteStr", Arrays.asList("RParFunc"));
        addToParsingTable("ParFunc", "cteChar", Arrays.asList("RParFunc"));
        addToParsingTable("ParFunc", "cteBool", Arrays.asList("RParFunc"));
        addToParsingTable("RParFunc", "id", Arrays.asList("Exp", "TRParFunc"));
        addToParsingTable("RParFunc", "(", Arrays.asList("Exp", "TRParFunc"));
        addToParsingTable("RParFunc", "~", Arrays.asList("Exp", "TRParFunc"));
        addToParsingTable("RParFunc", "-", Arrays.asList("Exp", "TRParFunc"));
        addToParsingTable("RParFunc", "cteI", Arrays.asList("Exp", "TRParFunc"));
        addToParsingTable("RParFunc", "cteR", Arrays.asList("Exp", "TRParFunc"));
        addToParsingTable("RParFunc", "cteStr", Arrays.asList("Exp", "TRParFunc"));
        addToParsingTable("RParFunc", "cteChar", Arrays.asList("Exp", "TRParFunc"));
        addToParsingTable("RParFunc", "cteBool", Arrays.asList("Exp", "TRParFunc"));
        addToParsingTable("TRParFunc", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("TRParFunc", ",", Arrays.asList(",", "Exp", "TParFunc"));
        addToParsingTable("TParFunc", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("Exp", "id", Arrays.asList("ExpBoolAnd", "RExp"));
        addToParsingTable("Exp", "(", Arrays.asList("ExpBoolAnd", "RExp"));
        addToParsingTable("Exp", "~", Arrays.asList("ExpBoolAnd", "RExp"));
        addToParsingTable("Exp", "-", Arrays.asList("ExpBoolAnd", "RExp"));
        addToParsingTable("Exp", "cteI", Arrays.asList("ExpBoolAnd", "RExp"));
        addToParsingTable("Exp", "cteR", Arrays.asList("ExpBoolAnd", "RExp"));
        addToParsingTable("Exp", "cteStr", Arrays.asList("ExpBoolAnd", "RExp"));
        addToParsingTable("Exp", "cteChar", Arrays.asList("ExpBoolAnd", "RExp"));
        addToParsingTable("Exp", "cteBool", Arrays.asList("ExpBoolAnd", "RExp"));
        addToParsingTable("RExp", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExp", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExp", "]", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExp", ",", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExp", "||", Arrays.asList("||", "ExpBoolAnd", "RExp"));
        addToParsingTable("ExpBoolAnd", "id", Arrays.asList("ExpRDois", "RExpBoolAnd"));
        addToParsingTable("ExpBoolAnd", "(", Arrays.asList("ExpRDois", "RExpBoolAnd"));
        addToParsingTable("ExpBoolAnd", "~", Arrays.asList("ExpRDois", "RExpBoolAnd"));
        addToParsingTable("ExpBoolAnd", "-", Arrays.asList("ExpRDois", "RExpBoolAnd"));
        addToParsingTable("ExpBoolAnd", "cteI", Arrays.asList("ExpRDois", "RExpBoolAnd"));
        addToParsingTable("ExpBoolAnd", "cteR", Arrays.asList("ExpRDois", "RExpBoolAnd"));
        addToParsingTable("ExpBoolAnd", "cteStr", Arrays.asList("ExpRDois", "RExpBoolAnd"));
        addToParsingTable("ExpBoolAnd", "cteChar", Arrays.asList("ExpRDois", "RExpBoolAnd"));
        addToParsingTable("ExpBoolAnd", "cteBool", Arrays.asList("ExpRDois", "RExpBoolAnd"));
        addToParsingTable("RExpBoolAnd", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpBoolAnd", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpBoolAnd", "]", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpBoolAnd", ",", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpBoolAnd", "||", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpBoolAnd", "&&", Arrays.asList("&&", "ExpRDois", "RExpBoolAnd"));
        addToParsingTable("ExpRDois", "id", Arrays.asList("ExpRUm", "RExpRDois"));
        addToParsingTable("ExpRDois", "(", Arrays.asList("ExpRUm", "RExpRDois"));
        addToParsingTable("ExpRDois", "~", Arrays.asList("ExpRUm", "RExpRDois"));
        addToParsingTable("ExpRDois", "-", Arrays.asList("ExpRUm", "RExpRDois"));
        addToParsingTable("ExpRDois", "cteI", Arrays.asList("ExpRUm", "RExpRDois"));
        addToParsingTable("ExpRDois", "cteR", Arrays.asList("ExpRUm", "RExpRDois"));
        addToParsingTable("ExpRDois", "cteStr", Arrays.asList("ExpRUm", "RExpRDois"));
        addToParsingTable("ExpRDois", "cteChar", Arrays.asList("ExpRUm", "RExpRDois"));
        addToParsingTable("ExpRDois", "cteBool", Arrays.asList("ExpRUm", "RExpRDois"));
        addToParsingTable("RExpRDois", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRDois", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRDois", "]", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRDois", ",", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRDois", "||", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRDois", "&&", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRDois", "opr2", Arrays.asList("opr2", "ExpRUm"));
        addToParsingTable("ExpRUm", "id", Arrays.asList("ExpConcat", "RExpRUm"));
        addToParsingTable("ExpRUm", "(", Arrays.asList("ExpConcat", "RExpRUm"));
        addToParsingTable("ExpRUm", "~", Arrays.asList("ExpConcat", "RExpRUm"));
        addToParsingTable("ExpRUm", "-", Arrays.asList("ExpConcat", "RExpRUm"));
        addToParsingTable("ExpRUm", "cteI", Arrays.asList("ExpConcat", "RExpRUm"));
        addToParsingTable("ExpRUm", "cteR", Arrays.asList("ExpConcat", "RExpRUm"));
        addToParsingTable("ExpRUm", "cteStr", Arrays.asList("ExpConcat", "RExpRUm"));
        addToParsingTable("ExpRUm", "cteChar", Arrays.asList("ExpConcat", "RExpRUm"));
        addToParsingTable("ExpRUm", "cteBool", Arrays.asList("ExpConcat", "RExpRUm"));
        addToParsingTable("RExpRUm", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRUm", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRUm", "]", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRUm", ",", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRUm", "||", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRUm", "&&", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRUm", "opr2", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpRUm", "opr1", Arrays.asList("opr1", "ExpConcat", "RExpRUm"));
        addToParsingTable("ExpConcat", "id", Arrays.asList("Expa", "RExpConcat"));
        addToParsingTable("ExpConcat", "(", Arrays.asList("Expa", "RExpConcat"));
        addToParsingTable("ExpConcat", "~", Arrays.asList("Expa", "RExpConcat"));
        addToParsingTable("ExpConcat", "-", Arrays.asList("Expa", "RExpConcat"));
        addToParsingTable("ExpConcat", "cteI", Arrays.asList("Expa", "RExpConcat"));
        addToParsingTable("ExpConcat", "cteR", Arrays.asList("Expa", "RExpConcat"));
        addToParsingTable("ExpConcat", "cteStr", Arrays.asList("Expa", "RExpConcat"));
        addToParsingTable("ExpConcat", "cteChar", Arrays.asList("Expa", "RExpConcat"));
        addToParsingTable("ExpConcat", "cteBool", Arrays.asList("Expa", "RExpConcat"));
        addToParsingTable("RExpConcat", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpConcat", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpConcat", "]", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpConcat", ",", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpConcat", "||", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpConcat", "&&", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpConcat", "opr2", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpConcat", "opr1", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpConcat", "$", Arrays.asList("$", "Expa", "RExpConcat"));
        addToParsingTable("RExpConcat", "EOF", Arrays.asList("$", "Expa", "RExpConcat"));
        addToParsingTable("Expa", "id", Arrays.asList("Expm", "RExpa"));
        addToParsingTable("Expa", "(", Arrays.asList("Expm", "RExpa"));
        addToParsingTable("Expa", "~", Arrays.asList("Expm", "RExpa"));
        addToParsingTable("Expa", "-", Arrays.asList("Expm", "RExpa"));
        addToParsingTable("Expa", "cteI", Arrays.asList("Expm", "RExpa"));
        addToParsingTable("Expa", "cteR", Arrays.asList("Expm", "RExpa"));
        addToParsingTable("Expa", "cteStr", Arrays.asList("Expm", "RExpa"));
        addToParsingTable("Expa", "cteChar", Arrays.asList("Expm", "RExpa"));
        addToParsingTable("Expa", "cteBool", Arrays.asList("Expm", "RExpa"));
        addToParsingTable("RExpa", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpa", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpa", "]", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpa", ",", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpa", "||", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpa", "&&", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpa", "opr2", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpa", "opr1", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpa", "$", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpa", "opa", Arrays.asList("opa", "Expm", "RExpa"));
        addToParsingTable("RExpa", "EOF", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("Expm", "id", Arrays.asList("ExpU", "RExpm"));
        addToParsingTable("Expm", "(", Arrays.asList("ExpU", "RExpm"));
        addToParsingTable("Expm", "~", Arrays.asList("ExpU", "RExpm"));
        addToParsingTable("Expm", "-", Arrays.asList("ExpU", "RExpm"));
        addToParsingTable("Expm", "cteI", Arrays.asList("ExpU", "RExpm"));
        addToParsingTable("Expm", "cteR", Arrays.asList("ExpU", "RExpm"));
        addToParsingTable("Expm", "cteStr", Arrays.asList("ExpU", "RExpm"));
        addToParsingTable("Expm", "cteChar", Arrays.asList("ExpU", "RExpm"));
        addToParsingTable("Expm", "cteBool", Arrays.asList("ExpU", "RExpm"));
        addToParsingTable("RExpm", ")", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", ";", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", "]", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", ",", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", "||", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", "&&", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", "opr2", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", "opr1", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", "$", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", "opa", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("RExpm", "opm", Arrays.asList("opm", "ExpU", "RExpm"));
        addToParsingTable("RExpm", "EOF", Arrays.asList(GrammarResources.getEpsilon()));
        addToParsingTable("ExpU", "id", Arrays.asList("Fa"));
        addToParsingTable("ExpU", "(", Arrays.asList("Fa"));
        addToParsingTable("ExpU", "~", Arrays.asList("Unario", "Fa"));
        addToParsingTable("ExpU", "-", Arrays.asList("Unario", "Fa"));
        addToParsingTable("ExpU", "cteI", Arrays.asList("Fa"));
        addToParsingTable("ExpU", "cteR", Arrays.asList("Fa"));
        addToParsingTable("ExpU", "cteStr", Arrays.asList("Fa"));
        addToParsingTable("ExpU", "cteChar", Arrays.asList("Fa"));
        addToParsingTable("ExpU", "cteBool", Arrays.asList("Fa"));
        addToParsingTable("Unario", "~", Arrays.asList("~"));
        addToParsingTable("Unario", "-", Arrays.asList("-"));
        addToParsingTable("Fa", "id", Arrays.asList("id", "RFa"));
        addToParsingTable("Fa", "(", Arrays.asList("(", "Exp", ")"));
        addToParsingTable("Fa", "cteI", Arrays.asList("Cte"));
        addToParsingTable("Fa", "cteR", Arrays.asList("Cte"));
        addToParsingTable("Fa", "cteStr", Arrays.asList("Cte"));
        addToParsingTable("Fa", "cteChar", Arrays.asList("Cte"));
        addToParsingTable("Fa", "cteBool", Arrays.asList("Cte"));
        addToParsingTable("RFa", "(", Arrays.asList("(", "ParFunc", ")"));
        addToParsingTable("RFa", ")", Arrays.asList("RArr"));
        addToParsingTable("RFa", ";", Arrays.asList("RArr"));
        addToParsingTable("RFa", "[", Arrays.asList("RArr"));
        addToParsingTable("RFa", "]", Arrays.asList("RArr"));
        addToParsingTable("RFa", ",", Arrays.asList("RArr"));
        addToParsingTable("RFa", "||", Arrays.asList("RArr"));
        addToParsingTable("RFa", "&&", Arrays.asList("RArr"));
        addToParsingTable("RFa", "opr2", Arrays.asList("RArr"));
        addToParsingTable("RFa", "opr1", Arrays.asList("RArr"));
        addToParsingTable("RFa", "$", Arrays.asList("RArr"));
        addToParsingTable("RFa", "opa", Arrays.asList("RArr"));
        addToParsingTable("RFa", "opm", Arrays.asList("RArr"));
        addToParsingTable("RFa", "EOF", Arrays.asList("RArr"));
        addToParsingTable("Cte", "cteI", Arrays.asList("cteI"));
        addToParsingTable("Cte", "cteR", Arrays.asList("cteR"));
        addToParsingTable("Cte", "cteStr", Arrays.asList("cteStr"));
        addToParsingTable("Cte", "cteChar", Arrays.asList("cteChar"));
        addToParsingTable("Cte", "cteBool", Arrays.asList("cteBool"));
    }

    public static void initParsingTable() {

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

        if (actualSymbol.equals(GrammarResources.getEpsilon())) {
            GrammarResources.
                    getFollows().
                    get(nonTerminal).
                    forEach(
                            term -> addToParsingTable(nonTerminal,
                                        term,
                                        Arrays.asList(GrammarResources.getEpsilon()))

                    );
        } else if (isTerminal(actualSymbol)) {
            addToParsingTable(nonTerminal, actualSymbol, symbolsInProduction);
        } else {
            GrammarResources.
                    getFirsts().
                    get(actualSymbol).
                    forEach(
                            first -> {
                                if (first.equals(GrammarResources.getEpsilon())) {
                                    GrammarResources.getFollows().
                                            get(actualSymbol).
                                            forEach(
                                                    follow ->
                                                            addToParsingTable(nonTerminal,
                                                                    follow,
                                                                    symbolsInProduction
                                                            )

                                            );
                                } else {
                                    addToParsingTable(nonTerminal, first, symbolsInProduction);
                                }
                            }
                    );
        }

    }

    @SuppressWarnings("unchecked")
    private static void addToParsingTable(String nonTerminal, String terminal, List<String> production) {
        Pair pair = new Pair(nonTerminal, terminal);
        parsingTable.put(pair, production);
    }

    private static void showParsingTable() {
        System.out.println("PARSING TABLE (size: " + parsingTable.size() + " rows): ");
        parsingTable
                .keySet()
                .forEach(k -> {
                    System.out.print("(" + k.getKey() + ", " + k.getValue() + ") : ");
                    System.out.println(parsingTable.get(k));
                });
    }

    @SuppressWarnings("unchecked")
    public static void predictiveParsing(List<String> input) throws EmptyStackException {
        int count = 0;
        String next = input.get(count);
        stack.push("EOF");
        stack.push("S");
        String X = stack.peek();

        Pair<String, String> pair;

        while (!X.equals("EOF")) {
            pair = new Pair(X, next);
            if (X.equals(next)) {
                System.out.println("match " + X);
                count++;
                next = input.get(count);
                stack.pop();
            } else if (isTerminal(X)) {
                System.out.println(X);
                error("isTerminal");
            } else if (!parsingTable.containsKey(pair)) {
                error("!parsingTable.containsKey");
            } else { // M[X, a] contains production

                System.out.println(X + " -> " + parsingTable.get(pair)
                        .stream()
                        .collect(Collectors.joining(" ")));
                stack.pop();

                List<String> production = parsingTable.get(pair);

                if (!production.contains(GrammarResources.getEpsilon())) // epsilon don't go to the stack
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
