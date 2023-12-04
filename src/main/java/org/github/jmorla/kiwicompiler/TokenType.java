package org.github.jmorla.kiwicompiler;

public enum TokenType {
    //punctuations
    LEFT_PAREN, RIGHT_PAREN, COMMA, MINUS, GREATER_THAN, LOWER_THAN, EQUAL,

    CLOSING,
    SELF_CLOSING,
    //literals
    TEXT, IDENTIFIER, STRING,

    //keywords
    IMPORT, RENDER,
    // end of file
    EOF,
    NONE
}
