package br.ufal.ic.lexer;

/* Class used to reserved words and identifiers. */
public class Word extends Token {
    public final String lexeme;
    public final TokenCategory category;

    public Word(TokenCategory tag, String lexeme) {
        super(tag);
        this.category = tag;
        this.lexeme = lexeme;
    }
}
