package br.ufal.ic.lexer;

import java.util.Objects;

public class Token {

    private TokenCategory tag;
    private int row;
    private int column;
    private String value;
    private boolean error;
    private String msg;

    public Token(TokenCategory tag) {
        this.tag = tag;
    }

    public Token(TokenCategory tag, int row, int column, String value) {
        this.tag = tag;
        this.row = row;
        this.column = column;
        this.value = value;
        this.error = false;
        this.msg = "";
    }

    public Token(TokenCategory tag, int row, int column, String value, boolean error, String msg) {
        this.tag = tag;
        this.row = row;
        this.column = column;
        this.value = value;
        this.error = error;
        this.msg = msg;
    }

    public TokenCategory getTag() {
        return tag;
    }

    public void setTag(TokenCategory tag) {
        this.tag = tag;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return String.format("[%03d, %03d] (%04d, %10s) {%s}", row, column, tag.ordinal(), tag, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return row == token.row &&
                column == token.column &&
                tag == token.tag &&
                Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tag, row, column, value);
    }
}
