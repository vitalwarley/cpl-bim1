package br.ufal.ic.lexer.models;

import br.ufal.ic.lexer.enums.TokenCategory;

/* Class used to reserved words and identifiers. */
public class Word extends Token {
    private final String lexeme;
    private final TokenCategory category;

    public Word(TokenCategory tag, String lexeme) {
        super(tag);
        this.category = tag;
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public TokenCategory getCategory() {
        return category;
    }
}
