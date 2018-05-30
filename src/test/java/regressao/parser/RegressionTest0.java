package regressao.parser;

import br.ufal.ic.lexer.models.Token;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test01() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test01");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        Token[] tokenArray2 = new Token[] {};
        java.util.ArrayList<Token> tokenList3 = new java.util.ArrayList<Token>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList3, tokenArray2);
        java.lang.String[] strArray6 = new java.lang.String[] { "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        try {
            boolean boolean9 = parser1.predictiveParsing((java.util.List<Token>) tokenList3, (java.util.List<java.lang.String>) strList7);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: -1");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(tokenArray2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        Token[] tokenArray2 = new Token[] {};
        java.util.ArrayList<Token> tokenList3 = new java.util.ArrayList<Token>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList3, tokenArray2);
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        try {
            boolean boolean9 = parser1.predictiveParsing((java.util.List<Token>) tokenList3, (java.util.List<java.lang.String>) strList7);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(tokenArray2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        java.lang.Class<?> wildcardClass3 = parser1.getClass();
        Token[] tokenArray4 = new Token[] {};
        java.util.ArrayList<Token> tokenList5 = new java.util.ArrayList<Token>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList5, tokenArray4);
        java.lang.String[] strArray8 = new java.lang.String[] { "" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        try {
            boolean boolean11 = parser1.predictiveParsing((java.util.List<Token>) tokenList5, (java.util.List<java.lang.String>) strList9);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(tokenArray4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        java.util.List<Token> tokenList3 = null;
        java.lang.String[] strArray6 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList7 = new java.util.ArrayList<java.lang.String>();
        boolean boolean8 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList7, strArray6);
        try {
            boolean boolean9 = parser1.predictiveParsing(tokenList3, (java.util.List<java.lang.String>) strList7);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(strArray6);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        Token[] tokenArray2 = new Token[] {};
        java.util.ArrayList<Token> tokenList3 = new java.util.ArrayList<Token>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList3, tokenArray2);
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        try {
            boolean boolean10 = parser1.predictiveParsing((java.util.List<Token>) tokenList3, (java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(tokenArray2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        Token[] tokenArray3 = new Token[] {};
        java.util.ArrayList<Token> tokenList4 = new java.util.ArrayList<Token>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList4, tokenArray3);
        java.lang.String[] strArray7 = new java.lang.String[] { "" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        try {
            boolean boolean10 = parser1.predictiveParsing((java.util.List<Token>) tokenList4, (java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(tokenArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        java.lang.Class<?> wildcardClass3 = parser1.getClass();
        Token[] tokenArray4 = new Token[] {};
        java.util.ArrayList<Token> tokenList5 = new java.util.ArrayList<Token>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList5, tokenArray4);
        java.lang.String[] strArray8 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        try {
            boolean boolean11 = parser1.predictiveParsing((java.util.List<Token>) tokenList5, (java.util.List<java.lang.String>) strList9);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(tokenArray4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        Token[] tokenArray2 = new Token[] {};
        java.util.ArrayList<Token> tokenList3 = new java.util.ArrayList<Token>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList3, tokenArray2);
        java.util.List<java.lang.String> strList5 = null;
        try {
            boolean boolean6 = parser1.predictiveParsing((java.util.List<Token>) tokenList3, strList5);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(tokenArray2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        java.lang.Class<?> wildcardClass3 = parser1.getClass();
        Token[] tokenArray4 = new Token[] {};
        java.util.ArrayList<Token> tokenList5 = new java.util.ArrayList<Token>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList5, tokenArray4);
        java.lang.String[] strArray9 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList10 = new java.util.ArrayList<java.lang.String>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList10, strArray9);
        try {
            boolean boolean12 = parser1.predictiveParsing((java.util.List<Token>) tokenList5, (java.util.List<java.lang.String>) strList10);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(tokenArray4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        Token[] tokenArray2 = new Token[] {};
        java.util.ArrayList<Token> tokenList3 = new java.util.ArrayList<Token>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList3, tokenArray2);
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        try {
            boolean boolean10 = parser1.predictiveParsing((java.util.List<Token>) tokenList3, (java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(tokenArray2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test11() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test11");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        Token[] tokenArray2 = new Token[] {};
        java.util.ArrayList<Token> tokenList3 = new java.util.ArrayList<Token>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList3, tokenArray2);
        java.lang.String[] strArray7 = new java.lang.String[] { "", "" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        try {
            boolean boolean10 = parser1.predictiveParsing((java.util.List<Token>) tokenList3, (java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(tokenArray2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test12() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test12");
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        java.lang.Class<?> wildcardClass2 = obj0.getClass();
        java.lang.Class<?> wildcardClass3 = obj0.getClass();
        java.lang.Class<?> wildcardClass4 = obj0.getClass();
        java.lang.Class<?> wildcardClass5 = obj0.getClass();
        java.lang.Class<?> wildcardClass6 = obj0.getClass();
        java.lang.Class<?> wildcardClass7 = obj0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(wildcardClass4);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertNotNull(wildcardClass6);
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test13() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test13");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        Token[] tokenArray3 = new Token[] {};
        java.util.ArrayList<Token> tokenList4 = new java.util.ArrayList<Token>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList4, tokenArray3);
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        try {
            boolean boolean10 = parser1.predictiveParsing((java.util.List<Token>) tokenList4, (java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(tokenArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test14() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test14");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        java.lang.Class<?> wildcardClass3 = parser1.getClass();
        java.lang.Class<?> wildcardClass4 = parser1.getClass();
        Token[] tokenArray5 = new Token[] {};
        java.util.ArrayList<Token> tokenList6 = new java.util.ArrayList<Token>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList6, tokenArray5);
        java.lang.String[] strArray10 = new java.lang.String[] { "hi!", "" };
        java.util.ArrayList<java.lang.String> strList11 = new java.util.ArrayList<java.lang.String>();
        boolean boolean12 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList11, strArray10);
        try {
            boolean boolean13 = parser1.predictiveParsing((java.util.List<Token>) tokenList6, (java.util.List<java.lang.String>) strList11);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(wildcardClass4);
        org.junit.Assert.assertNotNull(tokenArray5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(strArray10);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
    }

    @Test
    public void test15() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test15");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        Token[] tokenArray2 = new Token[] {};
        java.util.ArrayList<Token> tokenList3 = new java.util.ArrayList<Token>();
        boolean boolean4 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList3, tokenArray2);
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!" };
        java.util.ArrayList<java.lang.String> strList8 = new java.util.ArrayList<java.lang.String>();
        boolean boolean9 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList8, strArray7);
        try {
            boolean boolean10 = parser1.predictiveParsing((java.util.List<Token>) tokenList3, (java.util.List<java.lang.String>) strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(tokenArray2);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertNotNull(strArray7);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
    }

    @Test
    public void test16() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test16");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        java.lang.Class<?> wildcardClass3 = parser1.getClass();
        Token[] tokenArray4 = new Token[] {};
        java.util.ArrayList<Token> tokenList5 = new java.util.ArrayList<Token>();
        boolean boolean6 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList5, tokenArray4);
        java.lang.String[] strArray9 = new java.lang.String[] { "hi!", "" };
        java.util.ArrayList<java.lang.String> strList10 = new java.util.ArrayList<java.lang.String>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList10, strArray9);
        try {
            boolean boolean12 = parser1.predictiveParsing((java.util.List<Token>) tokenList5, (java.util.List<java.lang.String>) strList10);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(tokenArray4);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }

    @Test
    public void test17() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test17");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        java.lang.Class<?> wildcardClass3 = parser1.getClass();
        java.lang.Class<?> wildcardClass4 = parser1.getClass();
        Token[] tokenArray5 = new Token[] {};
        java.util.ArrayList<Token> tokenList6 = new java.util.ArrayList<Token>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList6, tokenArray5);
        java.util.List<java.lang.String> strList8 = null;
        try {
            boolean boolean9 = parser1.predictiveParsing((java.util.List<Token>) tokenList6, strList8);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(wildcardClass4);
        org.junit.Assert.assertNotNull(tokenArray5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
    }

    @Test
    public void test18() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test18");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        java.lang.Class<?> wildcardClass3 = parser1.getClass();
        java.lang.Class<?> wildcardClass4 = parser1.getClass();
        java.util.List<Token> tokenList5 = null;
        java.lang.String[] strArray8 = new java.lang.String[] { "", "hi!" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        try {
            boolean boolean11 = parser1.predictiveParsing(tokenList5, (java.util.List<java.lang.String>) strList9);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(wildcardClass4);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test19() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test19");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        Token[] tokenArray3 = new Token[] {};
        java.util.ArrayList<Token> tokenList4 = new java.util.ArrayList<Token>();
        boolean boolean5 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList4, tokenArray3);
        java.lang.String[] strArray8 = new java.lang.String[] { "hi!", "hi!" };
        java.util.ArrayList<java.lang.String> strList9 = new java.util.ArrayList<java.lang.String>();
        boolean boolean10 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList9, strArray8);
        try {
            boolean boolean11 = parser1.predictiveParsing((java.util.List<Token>) tokenList4, (java.util.List<java.lang.String>) strList9);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(tokenArray3);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(strArray8);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
    }

    @Test
    public void test20() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test20");
        java.util.Hashtable<javafx.util.Pair<java.lang.String, java.lang.String>, java.util.List<java.lang.String>> strPairMap0 = null;
        br.ufal.ic.parser.Parser parser1 = new br.ufal.ic.parser.Parser(strPairMap0);
        java.lang.Class<?> wildcardClass2 = parser1.getClass();
        java.lang.Class<?> wildcardClass3 = parser1.getClass();
        java.lang.Class<?> wildcardClass4 = parser1.getClass();
        Token[] tokenArray5 = new Token[] {};
        java.util.ArrayList<Token> tokenList6 = new java.util.ArrayList<Token>();
        boolean boolean7 = java.util.Collections.addAll((java.util.Collection<Token>) tokenList6, tokenArray5);
        java.lang.String[] strArray9 = new java.lang.String[] { "" };
        java.util.ArrayList<java.lang.String> strList10 = new java.util.ArrayList<java.lang.String>();
        boolean boolean11 = java.util.Collections.addAll((java.util.Collection<java.lang.String>) strList10, strArray9);
        try {
            boolean boolean12 = parser1.predictiveParsing((java.util.List<Token>) tokenList6, (java.util.List<java.lang.String>) strList10);
            org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException; message: null");
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {
        }
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(wildcardClass4);
        org.junit.Assert.assertNotNull(tokenArray5);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(strArray9);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
    }
}

