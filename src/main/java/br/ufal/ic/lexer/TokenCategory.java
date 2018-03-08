package br.ufal.ic.lexer;

/* Em inglês ou português? */
public enum TokenCategory {
    // ... lista de categorias, como na especificação
    TK_ID(1),

    TK_TRUE(35);

    private final int val;

    TokenCategory(int val) {
        this.val = val;
    }

}
