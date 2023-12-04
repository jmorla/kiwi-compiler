package org.github.jmorla.kiwicompiler;

public record Token(TokenType type, String lexeme, int line, int column) {}
