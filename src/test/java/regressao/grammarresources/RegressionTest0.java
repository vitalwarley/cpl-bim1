package regressao.grammarresources;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test1() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test1");
        try {
            br.ufal.ic.parser.GrammarResources.initGrammar("hi!");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Path inválido");
        } catch (java.lang.IllegalArgumentException e) {
        }
    }

    @Test
    public void test2() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test2");
        java.lang.String str0 = br.ufal.ic.parser.GrammarResources.getEpsilon();
        org.junit.Assert.assertTrue("'" + str0 + "' != '" + "epsilon" + "'", str0.equals("epsilon"));
    }

    @Test
    public void test3() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test3");
        try {
            br.ufal.ic.parser.GrammarResources.initGrammar("");
            org.junit.Assert.fail("Expected exception of type java.io.UncheckedIOException; message: java.io.IOException: Is a directory");
        } catch (java.io.UncheckedIOException e) {
        }
    }

    @Test
    public void test4() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test4");
        try {
            br.ufal.ic.parser.GrammarResources.initGrammar("epsilon");
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalArgumentException; message: Path inválido");
        } catch (java.lang.IllegalArgumentException e) {
        }
    }

    @Test
    public void test5() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test5");
        br.ufal.ic.parser.GrammarResources grammarResources0 = new br.ufal.ic.parser.GrammarResources();
        java.lang.Class<?> wildcardClass1 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass2 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass3 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass4 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass5 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass6 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass7 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass8 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass9 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass10 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass11 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass12 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass13 = grammarResources0.getClass();
        java.lang.Class<?> wildcardClass14 = grammarResources0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(wildcardClass4);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertNotNull(wildcardClass6);
        org.junit.Assert.assertNotNull(wildcardClass7);
        org.junit.Assert.assertNotNull(wildcardClass8);
        org.junit.Assert.assertNotNull(wildcardClass9);
        org.junit.Assert.assertNotNull(wildcardClass10);
        org.junit.Assert.assertNotNull(wildcardClass11);
        org.junit.Assert.assertNotNull(wildcardClass12);
        org.junit.Assert.assertNotNull(wildcardClass13);
        org.junit.Assert.assertNotNull(wildcardClass14);
    }

    @Test
    public void test6() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test6");
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        java.lang.Class<?> wildcardClass2 = obj0.getClass();
        java.lang.Class<?> wildcardClass3 = obj0.getClass();
        java.lang.Class<?> wildcardClass4 = obj0.getClass();
        java.lang.Class<?> wildcardClass5 = obj0.getClass();
        java.lang.Class<?> wildcardClass6 = obj0.getClass();
        java.lang.Class<?> wildcardClass7 = obj0.getClass();
        java.lang.Class<?> wildcardClass8 = obj0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
        org.junit.Assert.assertNotNull(wildcardClass2);
        org.junit.Assert.assertNotNull(wildcardClass3);
        org.junit.Assert.assertNotNull(wildcardClass4);
        org.junit.Assert.assertNotNull(wildcardClass5);
        org.junit.Assert.assertNotNull(wildcardClass6);
        org.junit.Assert.assertNotNull(wildcardClass7);
        org.junit.Assert.assertNotNull(wildcardClass8);
    }
}

