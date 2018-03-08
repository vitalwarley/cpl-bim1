package br.ufal.ic.lexer;

import java.util.Hashtable;

/* Classe principal, o analisador léxico. */
public class Lexer {

    public int line;

    private char lookAhead = ' '; // guardar o caractere na entrada, com espaço como default
    private Hashtable words = new Hashtable(); // guardar as palavras reservadas e identificadores lidos

    public Lexer() {
        // set reserved words
        reserve(new Word(TokenCategory.TK_TRUE, "true")); // exemplo
    }

    void reserve(Word t) { words.put(t.lexeme, t); }

    public Token scan() {
        // skip white space and count lines
        // handle numbers
        // handle reserved words and ids
        // Token t = new Token(TokenCategory(lookAhead)); // como inicializar TokenCategory com char da entrada?
        // acho que precisamos de um HashMap para, de acordo com a entrada (nao o lookAhead, pq é apenas um char),
        // buscar seu TokenCategory
        // lookAhead = ' '; // initialization
        return null;
    }

    public static void main(String[] args) {
        // ...
    }
}
