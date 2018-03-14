package br.ufal.ic.lexer.test;

import br.ufal.ic.lexer.Lexer;
import br.ufal.ic.lexer.Token;
import br.ufal.ic.lexer.TokenCategory;
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
    /* TODO: colocar path num application properties? */
    //String path = "/Users/dayvsonsales/";
    String path = "/home/lativ/IdeaProjects/";

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

        assertThat(actual, is(expected));
    }


    @Test
    void charComDoisCaractere(){
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/chardoiscarac.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(
                TokenCategory.TK_CTECHAR, 1, 1, "a", true, MessageBR.CTECHAR_ERR));
        expected.add(new Token(
                TokenCategory.TK_ID, 1, 3, "a")); /* Read second 'a' normally as an id */
        expected.add(new Token(
                TokenCategory.TK_CTECHAR, 1, 4, "", true, MessageBR.CTECHAR_ERR));

        assertThat(actual, is(expected));
    }

    @Test
    void testUnaryOp() {
        /* Check some service so that we don't have to instantiate every time */
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles(String.join("", path,
                "/cpl-bim1/examples/test/unaryOp.hs"), lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(
                TokenCategory.TK_OPU, 1, 1, "-"
        ));
        expected.add(new Token(
                TokenCategory.TK_CTEREAL, 1, 2, "10.5"
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
            while (lexer.getFile().available() > 0) {
                Token currentToken = lexer.nextToken();
                tokenList.add(currentToken);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokenList;
    }

}