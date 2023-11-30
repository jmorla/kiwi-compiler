package org.github.jmorla.kiwicompiler;

public record Token(TokenType type, String lexeme, int line, int column) {

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", lexeme='" + lexeme + '\'' +
                ", line=" + line +
                '}';
    }
}
