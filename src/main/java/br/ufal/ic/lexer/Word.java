package br.ufal.ic.lexer;

/* Classe usada para palavras reservadas e identificadores. */
public class Word extends Token {
    public final String lexeme;
    public final TokenCategory category;

    public Word(TokenCategory t, String lexeme) {
        super(t);
        this.category = t;
        this.lexeme = lexeme;
    }
}
