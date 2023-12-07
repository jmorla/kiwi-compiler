package org.github.jmorla.kiwicompiler;

public record KiwiToken(TokenType type, String lexeme, int line, int column) {
    public enum TokenType {
        //punctuations
        LEFT_PAREN("("),
        RIGHT_PAREN(")"),
        COMMA(","),
        MINUS("-"),
        GREATER_THAN(">"),
        LOWER_THAN("<"),
        EQUAL("="),
        CLOSING("</"),
        SELF_CLOSING("/>"),
        //literals
        TEXT("text"), IDENTIFIER("identifier"), STRING("literal string"),
        //keywords
        IMPORT("@import"), RENDER("@render");
        // end of file
        public final String name;
        TokenType(String name) {
            this.name = name;
        }
    }
}
