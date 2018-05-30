package regressao.lexer;

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
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        java.lang.Class<?> wildcardClass2 = obj0.getClass();
        java.lang.Class<?> wildcardClass3 = obj0.getClass();
        java.lang.Class<?> wildcardClass4 = obj0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(wildcardClass4);
    }

    @Test
    public void test02() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test02");
        br.ufal.ic.lexer.Lexer lexer0 = new br.ufal.ic.lexer.Lexer();
        java.io.FileInputStream fileInputStream1 = null;
        lexer0.setFile(fileInputStream1);
        java.io.FileInputStream fileInputStream3 = lexer0.getFile();
        try {
            Token token4 = lexer0.nextToken();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNull(fileInputStream3);
    }

    @Test
    public void test03() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test03");
        br.ufal.ic.lexer.Lexer lexer0 = new br.ufal.ic.lexer.Lexer();
        java.io.FileInputStream fileInputStream1 = null;
        lexer0.setFile(fileInputStream1);
        try {
            Token token3 = lexer0.nextToken();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
    }

    @Test
    public void test04() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test04");
        br.ufal.ic.lexer.Lexer lexer0 = new br.ufal.ic.lexer.Lexer();
        java.io.FileInputStream fileInputStream1 = lexer0.getFile();
        java.io.FileInputStream fileInputStream2 = null;
        lexer0.setFile(fileInputStream2);
        try {
            Token token4 = lexer0.nextToken();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: null");
        } catch (java.lang.NullPointerException e) {
        }
        org.junit.Assert.assertNull(fileInputStream1);
    }

    @Test
    public void test05() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test05");
        br.ufal.ic.lexer.Lexer lexer0 = new br.ufal.ic.lexer.Lexer();
        java.io.FileInputStream fileInputStream1 = null;
        lexer0.setFile(fileInputStream1);
        java.lang.Class<?> wildcardClass3 = lexer0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass3);
    }

    @Test
    public void test06() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test06");
        br.ufal.ic.lexer.Lexer lexer0 = new br.ufal.ic.lexer.Lexer();
        java.lang.Class<?> wildcardClass1 = lexer0.getClass();
        java.io.FileInputStream fileInputStream2 = null;
        lexer0.setFile(fileInputStream2);
        java.io.FileInputStream fileInputStream4 = lexer0.getFile();
        boolean boolean5 = lexer0.isRollback();
        boolean boolean6 = lexer0.isRollback();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNull(fileInputStream4);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test07() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test07");
        br.ufal.ic.lexer.Lexer lexer0 = new br.ufal.ic.lexer.Lexer();
        java.lang.Class<?> wildcardClass1 = lexer0.getClass();
        java.io.FileInputStream fileInputStream2 = null;
        lexer0.setFile(fileInputStream2);
        java.io.FileInputStream fileInputStream4 = lexer0.getFile();
        java.io.FileInputStream fileInputStream5 = null;
        lexer0.setFile(fileInputStream5);
        java.io.FileInputStream fileInputStream7 = null;
        lexer0.setFile(fileInputStream7);
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNull(fileInputStream4);
    }

    @Test
    public void test08() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test08");
        br.ufal.ic.lexer.Lexer lexer0 = new br.ufal.ic.lexer.Lexer();
        java.io.FileInputStream fileInputStream1 = lexer0.getFile();
        boolean boolean2 = lexer0.isRollback();
        org.junit.Assert.assertNull(fileInputStream1);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test09() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test09");
        br.ufal.ic.lexer.Lexer lexer0 = new br.ufal.ic.lexer.Lexer();
        boolean boolean1 = lexer0.isRollback();
        java.io.FileInputStream fileInputStream2 = lexer0.getFile();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertNull(fileInputStream2);
    }

    @Test
    public void test10() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test10");
        br.ufal.ic.lexer.Lexer lexer0 = new br.ufal.ic.lexer.Lexer();
        boolean boolean1 = lexer0.isRollback();
        java.io.FileInputStream fileInputStream2 = null;
        lexer0.setFile(fileInputStream2);
        boolean boolean4 = lexer0.isRollback();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
    }
}

