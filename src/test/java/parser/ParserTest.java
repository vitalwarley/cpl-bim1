package parser;

import br.ufal.ic.lexer.models.Token;
import br.ufal.ic.lexer.enums.TokenCategory;
import br.ufal.ic.parser.GrammarResources;
import br.ufal.ic.parser.Parser;
import br.ufal.ic.parser.ParsingTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private ByteArrayOutputStream out;
    private Parser parser;

    @BeforeEach
    void setUp() throws IOException {
        GrammarResources.initGrammar("grammar_ll1.txt");
        parser = new Parser(ParsingTable.getParsingTable());
    }

    @BeforeEach
    void setUpStream(){
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    void predictiveParsingAcceptedTokens(){
        /* defmod Teste do
            def void main() do
                int a = 1;
            end
           endmod
         */

        List<Token> input = new ArrayList<Token>(){{
            add(new Token(TokenCategory.TK_DEFMOD));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_DO));
            add(new Token(TokenCategory.TK_DEF));
            add(new Token(TokenCategory.TK_VOID));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_ABPAR));
            add(new Token(TokenCategory.TK_FCPAR));
            add(new Token(TokenCategory.TK_DO));
            add(new Token(TokenCategory.TK_INT));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_ATR));
            add(new Token(TokenCategory.TK_CTEINT));
            add(new Token(TokenCategory.TK_PVGL));
            add(new Token(TokenCategory.TK_END));
            add(new Token(TokenCategory.TK_ENDMOD));
            add(new Token(TokenCategory.TK_EOF));
        }};

        List<String> lines = new ArrayList<String>(){{
            add("defmod Teste do");
            add("def void main() do");
            add("int a = 1;");
            add("end");
            add("endmod");
        }};

        assertTrue(parser.predictiveParsing(input, lines));
    }

    @Test
    void predictiveParsingWithoutEOFToken(){
        /* defmod Teste do
            def void main() do
                int a = 1;
            end
           endmod
         */

        List<Token> input = new ArrayList<Token>(){{
            add(new Token(TokenCategory.TK_DEFMOD));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_DO));
            add(new Token(TokenCategory.TK_DEF));
            add(new Token(TokenCategory.TK_VOID));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_ABPAR));
            add(new Token(TokenCategory.TK_FCPAR));
            add(new Token(TokenCategory.TK_DO));
            add(new Token(TokenCategory.TK_INT));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_ATR));
            add(new Token(TokenCategory.TK_CTEINT));
            add(new Token(TokenCategory.TK_PVGL));
            add(new Token(TokenCategory.TK_END));
            add(new Token(TokenCategory.TK_ENDMOD));
        }};

        List<String> lines = new ArrayList<String>(){{
            add("defmod Teste do");
            add("def void main() do");
            add("int a = 1;");
            add("end");
            add("endmod");
        }};

        assertFalse(parser.predictiveParsing(input, lines));
    }

    @Test
    void predictiveParsingMissingIdAtr(){
        /* defmod Teste do
            def void main() do
                int = 1;
            end
           endmod
         */

        List<Token> input = new ArrayList<Token>(){{
            add(new Token(TokenCategory.TK_DEFMOD));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_DO));
            add(new Token(TokenCategory.TK_DEF));
            add(new Token(TokenCategory.TK_VOID));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_ABPAR));
            add(new Token(TokenCategory.TK_FCPAR));
            add(new Token(TokenCategory.TK_DO));
            add(new Token(TokenCategory.TK_INT));
            add(new Token(TokenCategory.TK_ATR));
            add(new Token(TokenCategory.TK_CTEINT));
            add(new Token(TokenCategory.TK_PVGL));
            add(new Token(TokenCategory.TK_END));
            add(new Token(TokenCategory.TK_ENDMOD));
            add(new Token(TokenCategory.TK_EOF));
        }};

        List<String> lines = new ArrayList<String>(){{
            add("defmod Teste do");
            add("def void main() do");
            add("int = 1;");
            add("end");
            add("endmod");
        }};

        assertFalse(parser.predictiveParsing(input, lines));
    }

    @Test
    void predictiveParsingFirstTokenError(){

        List<Token> input = new ArrayList<Token>(){{
            add(new Token(TokenCategory.TK_UKN, 0, 0, "", true, "Não identificado"));
            add(new Token(TokenCategory.TK_EOF));
        }};

        List<String> lines = new ArrayList<String>(){{
            add("BLBLBLBLBLB");
        }};

        assertFalse(parser.predictiveParsing(input, lines));
    }

    @Test
    void predictiveParsingTokenError(){

        List<Token> input = new ArrayList<Token>(){{
            add(new Token(TokenCategory.TK_DEFMOD));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_DO));
            add(new Token(TokenCategory.TK_UKN, 0, 0, "", true, "Não identificado"));
            add(new Token(TokenCategory.TK_EOF));
        }};

        List<String> lines = new ArrayList<String>(){{
            add("defmod Teste do");
            add("blalllbll");
        }};

        assertFalse(parser.predictiveParsing(input, lines));
    }

    @Test
    void predictiveParsingErrorTwoTerminals(){
        /* defmod Teste do
            def void main() do
                int a = = 1;
            end
           endmod
         */

        List<Token> input = new ArrayList<Token>(){{
            add(new Token(TokenCategory.TK_DEFMOD));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_DO));
            add(new Token(TokenCategory.TK_DEF));
            add(new Token(TokenCategory.TK_VOID));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_FCPAR));
            add(new Token(TokenCategory.TK_FCPAR));
            add(new Token(TokenCategory.TK_DO));
            add(new Token(TokenCategory.TK_INT));
            add(new Token(TokenCategory.TK_ID));
            add(new Token(TokenCategory.TK_PVGL));
            add(new Token(TokenCategory.TK_ATR));
            add(new Token(TokenCategory.TK_CTEINT));
            add(new Token(TokenCategory.TK_PVGL));
            add(new Token(TokenCategory.TK_END));
            add(new Token(TokenCategory.TK_END));
            add(new Token(TokenCategory.TK_EOF));
        }};

        List<String> lines = new ArrayList<String>(){{
            add("defmod Teste do");
            add("def void main() do");
            add("int = 1;");
            add("end");
            add("endmod");
        }};

        assertFalse(parser.predictiveParsing(input, lines));
    }

    @Test
    void reverseProduction() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method isTerminalMethod = Parser.class.getDeclaredMethod("reverseProduction", List.class);
        isTerminalMethod.setAccessible(true);
        List<String> reversedProductions = (List<String>) isTerminalMethod.invoke(parser, Arrays.asList("Um", "Dois"));

        assertEquals(Arrays.asList("Dois", "Um"), reversedProductions);
    }

    @Test
    void isTerminalTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method isTerminalMethod = Parser.class.getDeclaredMethod("isTerminal", String.class);
        isTerminalMethod.setAccessible(true);
        boolean isTerminalBool = (boolean) isTerminalMethod.invoke(parser, "id");

        assertTrue(isTerminalBool);
    }

    @Test
    void error() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method errorMethod = Parser.class.getDeclaredMethod("error", String.class);
        errorMethod.setAccessible(true);
        errorMethod.invoke(parser, "Erro teste");

        assertEquals("[Erro teste] Aconteceu um erro durante a análise sintática\n", out.toString());
    }

}
