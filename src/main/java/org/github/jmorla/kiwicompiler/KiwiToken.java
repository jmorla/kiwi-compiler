package org.github.jmorla.kiwicompiler;

public record KiwiToken(TokenType type, String lexeme, int line, int column) {

    public enum TokenType {
        //punctuations
        LEFT_PAREN("("),
        RIGHT_PAREN(")"),
        COMMA(","),
        GREATER_THAN(">"),
        LOWER_THAN("<"),
        EQUAL("="),
        COLON(":"),
        TYPE("type"),
        CLOSING("</"),
        SELF_CLOSING("/>"),
        TEXT("text"),
        IDENTIFIER("identifier"),
        LITERAL("literal"),
        //keywords
        IMPORT("@import"),
        RENDER("@render");
        public final String name;
        TokenType(String name) {
            this.name = name;
        }
    }
}
