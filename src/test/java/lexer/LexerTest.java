package lexer;

import br.ufal.ic.lexer.Lexer;
import br.ufal.ic.lexer.models.Token;
import br.ufal.ic.lexer.enums.TokenCategory;
import br.ufal.ic.lexer.i18n.MessageBR;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class LexerTest {
    String path = "/Users/dayvsonsales/";
    //String path = "/home/lativ/IdeaProjects/";

    /*
     * @test Verificando se consegue trabalhar com constante REAL
     * @expected Lista de tokens, com quatro tokens TK_CTEINT e um TK_OPA
     * @passed
     * */
    @Test
    void cteInt() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "/cpl-bim1/examples/test/cteInt.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_CTEINT, 1, 1, "10"));
        expected.add(new Token(TokenCategory.TK_CTEINT, 1, 4, "20"));
        expected.add(new Token(TokenCategory.TK_CTEINT, 1, 7, "30"));
        expected.add(new Token(TokenCategory.TK_OPA, 1, 10, "-"));
        expected.add(new Token(TokenCategory.TK_CTEINT, 1, 11, "40"));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    /*
     * @test Verificando se consegue trabalhar com constante STR
     * @expected Lista de tokens, com quatro tokens TK_CTESTR e um TK_EOF
     * @passed
     * */
    @Test
    void cteStr() {

        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "cpl-bim1/examples/test/cteStr.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_CTESTR, 1, 1, "10"));
        expected.add(new Token(TokenCategory.TK_CTESTR, 1, 6, "20"));
        expected.add(new Token(TokenCategory.TK_CTESTR, 1, 10, "30"));
        expected.add(new Token(TokenCategory.TK_CTESTR, 1, 14, "-40"));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));

    }

    /*
     * @test Verificando se consegue trabalhar com constante REAL
     * @expected Lista de tokens, com quatro tokens TK_CTEREAL e um TK_OPA
     * @passed
     * */
    @Test
    void cteReal() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "/cpl-bim1/examples/test/cteReal.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_CTEREAL, 1, 1, "10.0"));
        expected.add(new Token(TokenCategory.TK_CTEREAL, 1, 6, "20.0"));
        expected.add(new Token(TokenCategory.TK_CTEREAL, 1, 11, "30.0"));
        expected.add(new Token(TokenCategory.TK_OPA, 1, 16, "-"));
        expected.add(new Token(TokenCategory.TK_CTEREAL, 1, 17, "40.0"));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    /*
     * @test Verificando se consegue trabalhar com constante CHAR
     * @expected Lista de tokens, com cinco tokens TK_CTECHAR e um TK_EOF
     * @passed
     * */
    @Test
    void cteChar() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "/cpl-bim1/examples/test/cteChar.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_CTECHAR, 1, 1, "a"));
        expected.add(new Token(TokenCategory.TK_CTECHAR, 1, 5, "b"));
        expected.add(new Token(TokenCategory.TK_CTECHAR, 1, 9, "b"));
        expected.add(new Token(TokenCategory.TK_CTECHAR, 1, 13, "e"));
        expected.add(new Token(TokenCategory.TK_CTECHAR, 1, 17, "1"));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void printComOperacao() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "/cpl-bim1/examples/test/printOp.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_PRINT, 1, 1, "print"));
        expected.add(new Token(TokenCategory.TK_ABPAR, 1, 6, "("));
        expected.add(new Token(TokenCategory.TK_CTEREAL, 1, 7, "42.42"));
        expected.add(new Token(TokenCategory.TK_OPA, 1, 12, "-"));
        expected.add(new Token(TokenCategory.TK_CTEREAL, 1, 13, "0.42"));
        expected.add(new Token(TokenCategory.TK_FCPAR, 1, 17, ")"));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    /*
     * @test Verificando se consegue trabalhar com arquivos sem nnehum token valido
     * @expected Uma lista com um único token TK_UNKNOW
     * @passed
     * */
    @Test
    void nenhumTokenValido() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "/cpl-bim1/examples/test/novalid.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_UKN, 1, 1, "\\n !!!!!Î©\n"));

        assertThat(actual, is(expected));
    }

    /*
     * @test Verificando se consegue trabalhar com arquivos apenas com espaco
     * @expected Uma única lista com apenas TK_EOF
     * @passed
     * */
    @Test
    void tudoEspaco() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "/cpl-bim1/examples/test/whitespace.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void strComQuebradeLinhaErro() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "/cpl-bim1/examples/test/strquebra.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_CTESTR, 1, 1, "mari", true, MessageBR.CTESTR_ERR));
        expected.add(new Token(TokenCategory.TK_CTESTR, 2, 1, ""));
        expected.add(new Token(TokenCategory.TK_EOF, 3, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void strComEscapeAspasDuplas() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "/cpl-bim1/examples/test/strescape.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_CTESTR, 1, 1, "\"mari\""));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void strNaUltimaPosicaoComErro() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path, "/cpl-bim1/examples/test/strultimo.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_ID, 1, 1, "a"));
        expected.add(new Token(TokenCategory.TK_ID, 1, 3, "b"));
        expected.add(new Token(TokenCategory.TK_ID, 1, 5, "c"));
        expected.add(new Token(TokenCategory.TK_CTESTR, 1, 7, "", true, MessageBR.CTESTR_ERR));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void charComDoisCaractere() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/chardoiscarac.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(
                TokenCategory.TK_CTECHAR, 1, 1, "a", true, MessageBR.CTECHAR_ERR));
        expected.add(new Token(
                TokenCategory.TK_ID, 1, 3, "a"));
        expected.add(new Token(
                TokenCategory.TK_CTECHAR, 1, 4, "", true, MessageBR.CTECHAR_ERR));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void atribuicao() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/atribuicao.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(
                TokenCategory.TK_ATR, 1, 1, "="));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void igual() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/igual.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(
                TokenCategory.TK_REL2, 1, 1, "=="));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void comentarios() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/comentarios.hs"), lexer);

        List<Token> expected = new ArrayList<>();
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void escapeCaso1() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/escape.hs"), lexer);

        List<Token> expected = new ArrayList<>();
        expected.add(new Token(TokenCategory.TK_CTECHAR, 1, 2, "\""));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void escapeCaso2() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/escapeFinal.hs"), lexer);

        List<Token> expected = new ArrayList<>();
        expected.add(new Token(TokenCategory.TK_CTECHAR, 1, 2, "\\"));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void maiorIgual() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/maiorIgual.hs"), lexer);

        List<Token> expected = new ArrayList<>();
        expected.add(new Token(TokenCategory.TK_REL, 1, 1, ">="));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void menorIgual() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/menorIgual.hs"), lexer);

        List<Token> expected = new ArrayList<>();
        expected.add(new Token(TokenCategory.TK_REL, 1, 1, "<="));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void maior() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/maior.hs"), lexer);

        List<Token> expected = new ArrayList<>();
        expected.add(new Token(TokenCategory.TK_REL, 1, 1, ">"));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void menor() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/menor.hs"), lexer);

        List<Token> expected = new ArrayList<>();
        expected.add(new Token(TokenCategory.TK_REL, 1, 1, "<"));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void naoReal() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/naoReal.hs"), lexer);

        List<Token> expected = new ArrayList<>();
        expected.add(new Token(TokenCategory.TK_CTEREAL, 1, 1, "3.", true, MessageBR.CTEREAL_ERR));
        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    @Test
    void aspasJuntas() {
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/aspasJuntas.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(
                TokenCategory.TK_PRINT, 1, 1, "print"
        ));
        expected.add(new Token(
                TokenCategory.TK_ABPAR, 1, 6, "("
        ));
        expected.add(new Token(
                TokenCategory.TK_CTECHAR, 1, 7, "\"", true, MessageBR.CTECHAR_ERR
        ));
        expected.add(new Token(
                TokenCategory.TK_ID, 1, 9, "teste"
        ));
        expected.add(new Token(
                TokenCategory.TK_CTESTR, 1, 14, ")", true, MessageBR.CTESTR_ERR
        ));
        expected.add(new Token(
                TokenCategory.TK_EOF, 2, 1, ""
        ));

        assertThat(actual, is(expected));

    }

    private static List<Token> readFiles(String name, Lexer lexer) {

        List<Token> tokenList = new ArrayList<>();

        try {
            String path = name;
            lexer.setFile(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while (lexer.getFile().available() > 0 || lexer.isRollback()) {
                Token currentToken = lexer.nextToken();
                tokenList.add(currentToken);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokenList;
    }

}