package br.ufal.ic.lexer;

public enum TokenCategory {
    TK_ID("id"),

    TK_DEFMOD("defmod"),
    TK_DEF("def"),
    TK_DO("do"),
    TK_END("end"),
    TK_ENDMOD("endmod"),

    TK_UNTIL("until"),
    TK_REP("rep"),
    TK_WHEN("when"),
    TK_RETURN("return"),
    TK_OTRWISE("otherwise"),

    TK_VOID("void"),
    TK_INT("int"),
    TK_REAL("real"),
    TK_STR("str"),
    TK_BOOL("bool"),
    TK_CHAR("char"),

    TK_CTEINT("cteI"),
    TK_CTEREAL("cteR"),
    TK_CTESTR("cteStr"),
    TK_CTECHAR("cteChar"),

    TK_SPTOR(","),
    TK_DPTS(":"),
    TK_PVGL(";"),
    TK_READ("id"),
    TK_PRINT("id"),
    TK_LTVREAD("id"),

    TK_ABPAR("("),
    TK_FCPAR(")"),
    TK_ABCOL("["),
    TK_FCCOL("]"),
    TK_TRUE("true"),
    TK_FALSE("false"),
    TK_NIL("nil"),

    TK_OPA("opa"),
    TK_OPM("opm"),
    TK_ATR("="),
    TK_REL("opr1"),
    TK_REL2("opr2"),
    TK_AND("&&"),
    TK_OR("||"),
    TK_NOT("~"),

    TK_CONCAT("$"),

    TK_EOF("EOF"),

    TK_UKN("UKN");

    private String value;

    TokenCategory(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}

