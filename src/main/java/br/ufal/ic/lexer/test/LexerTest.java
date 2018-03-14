package br.ufal.ic.lexer.test;

import br.ufal.ic.lexer.Lexer;
import br.ufal.ic.lexer.Token;
import br.ufal.ic.lexer.TokenCategory;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class LexerTest {

    /*
     * @test Verificando se consegue trabalhar com constante REAL
     * @expected Lista de tokens, com quatro tokens TK_CTEINT e um TK_OPA
     * @passed
     * */
    @Test
    void cteInt(){
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles("/Users/dayvsonsales/cpl-bim1/examples/test/cteInt.hs", lexer);

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
    void cteStr(){

        Lexer lexer = new Lexer();

        List<Token> actual = readFiles("/Users/dayvsonsales/cpl-bim1/examples/test/cteStr.hs", lexer);

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
    void cteReal(){
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles("/Users/dayvsonsales/cpl-bim1/examples/test/cteReal.hs", lexer);

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
    void cteChar(){
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles("/Users/dayvsonsales/cpl-bim1/examples/test/cteChar.hs", lexer);

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
    void nenhumTokenValido(){
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles("/Users/dayvsonsales/cpl-bim1/examples/test/novalid.hs", lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_UNKNOW, 1, 1, "\\n !!!!!Î©\n"));

        assertThat(actual, is(expected));
    }

    /*
     * @test Verificando se consegue trabalhar com arquivos apenas com espaco
     * @expected Uma única lista com apenas TK_EOF
     * @passed
     * */
    @Test
    void tudoEspaco(){
        Lexer lexer = new Lexer();

        List<Token> actual = readFiles("/Users/dayvsonsales/cpl-bim1/examples/test/whitespace.hs", lexer);

        List<Token> expected = new ArrayList<>();

        expected.add(new Token(TokenCategory.TK_EOF, 2, 1, ""));

        assertThat(actual, is(expected));
    }

    private static List<Token> readFiles(String name, Lexer lexer){

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