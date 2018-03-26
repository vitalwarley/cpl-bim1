package br.ufal.ic.lexer;

public enum TokenCategory {

    TK_ID,

    TK_DEFMOD,
    TK_DEF,
    TK_DO,
    TK_END,
    TK_ENDMOD,

    TK_UNTIL,
    TK_REP,
    TK_WHEN,
    TK_RETURN,
    TK_OTRWISE,

    TK_VOID,
    TK_INT,
    TK_REAL,
    TK_STR,
    TK_BOOL,
    TK_CHAR,

    TK_CTEINT,
    TK_CTEREAL,
    TK_CTESTR,
    TK_CTECHAR,

    TK_SPTOR,
    TK_DPTS,
    TK_PVGL,
    TK_READ,
    TK_PRINT,
    TK_LTVREAD,

    TK_ABPAR,
    TK_FCPAR,
    TK_ABCOL,
    TK_FCCOL,
    TK_TRUE,
    TK_FALSE,
    TK_NIL,
    TK_OPA,
    TK_OPM,
    TK_ATR,
    TK_REL,
    TK_REL2,
    TK_AND,
    TK_OR,
    TK_NOT,

    TK_CONCAT,

    TK_EOF,

    TK_UKN

}

