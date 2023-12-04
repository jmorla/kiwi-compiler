package org.github.jmorla.kiwicompiler;

import java.util.List;

/*
 * Father for all kiwi statements
*/
public interface Statement {

    void accept(Visitor visitor);

    /**
     * Represents import statements
     * 
    */
    static record ImportStatement(
            Token directive,
            Token leftParen,
            List<Token> args,
            Token rightParen) implements Statement {

        @Override
        public void accept(Visitor visitor) {}

    }

    /**
     * Represents render statements
     * 
    */
    static record RenderStatement(
            Token directive,
            Token leftParen,
            Expression.TagExpression tagExpression,
            Token rightParent) implements Statement {

        @Override
        public void accept(Visitor visitor) {}

    }

    static interface Visitor {}
}
