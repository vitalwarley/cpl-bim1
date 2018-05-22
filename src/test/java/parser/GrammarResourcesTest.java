package parser;

import br.ufal.ic.parser.GrammarResources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GrammarResourcesTest {

    @BeforeEach
    void setUp() throws IOException {
        GrammarResources.initGrammar("grammar_ll1.txt");
    }

    @Test
    void initGrammarThatPathDoesntExist(){
        assertThrows(IllegalArgumentException.class, () -> GrammarResources.initGrammar("notFoundPath"));
    }

    @Test
    void addProductionThatIsNotInProductionList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Method addProductionMethod = GrammarResources.class.getDeclaredMethod("addProduction", String.class);
        addProductionMethod.setAccessible(true);
        addProductionMethod.invoke(null, "Maria -> Aa");

        Field productions = GrammarResources.class.getDeclaredField("productions");
        productions.setAccessible(true);
        Map<String, List<String>> mapProductions = (Map<String, List<String>>) productions.get(null);

        assertEquals(55, mapProductions.size());
    }

    @Test
    void addProductionThatIsInProductionList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Method addProductionMethod = GrammarResources.class.getDeclaredMethod("addProduction", String.class);
        addProductionMethod.setAccessible(true);
        addProductionMethod.invoke(null, "S -> Aa");

        Field productions = GrammarResources.class.getDeclaredField("productions");
        productions.setAccessible(true);
        Map<String, List<String>> mapProductions = (Map<String, List<String>>) productions.get(null);

        assertEquals(55, mapProductions.size());
    }

    @Test
    void addProductionEpsilon() throws NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        Method addProductionMethod = GrammarResources.class.getDeclaredMethod("addProduction", String.class);
        addProductionMethod.setAccessible(true);
        addProductionMethod.invoke(null, "Maria -> ''");

        Field productions = GrammarResources.class.getDeclaredField("productions");
        productions.setAccessible(true);
        Map<String, List<String>> mapProductions = (Map<String, List<String>>) productions.get(null);

        assertEquals(55, mapProductions.size());
    }

    @Test
    void checkSymbolIsTerminalTrue(){
        assertTrue(GrammarResources.checkSymbolIsTerminal("id"));
    }

    @Test
    void checkSymbolIsTerminalFalse(){
        assertFalse(GrammarResources.checkSymbolIsTerminal("Comando"));
    }

    @Test
    void getEpsilon(){
        assertEquals("epsilon", GrammarResources.getEpsilon());
    }
}
