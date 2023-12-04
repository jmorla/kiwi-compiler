package org.github.jmorla.kiwicompiler;

import java.util.List;

/*
 * Father for all kiwi expressions
*/
public interface Expression {

    void visit(Visitor visitor);

    /**
     * Represents all tag expressions
     * 
     * @see SelfClosingTagExpression and
     * @see PairedTagExpression
     */
    static interface TagExpression extends Expression {}


    /**
     * Expression Visitor
     * 
    */
    static interface Visitor {
    
    }

    /**
     * Represents self closing tags
     * < <identifier> <arguments>? />
     */
    static record SelfClosingTagExpression(
            Token lowerThan,
            Token identifer,
            List<ArgumentExpression> argumentExpressions,
            Token selfClosing) implements TagExpression {

        @Override
        public void visit(Visitor visitor) {
            throw new UnsupportedOperationException("Unimplemented method 'visit'");
        }

    }

    /**
     * Represents paired tags
     * < <identifier> <arguments>? > <tagExpressions>? </identifier>
     */
    static record PairedTagExpression(
            Token lowerThan,
            Token openIdentifier,
            List<ArgumentExpression> argumentExpressions,
            Token openGreaterThan,
            List<TagExpression> innertagExpressions,
            Token closing,
            Token closeIdentifier,
            Token closeGreaterThan) implements TagExpression {

        @Override
        public void visit(Visitor visitor) {
            throw new UnsupportedOperationException("Unimplemented method 'visit'");
        }

    }

    /*
     * Represents an Argument expression
     * usually defined as <identifier> or <identifier> = STRING
     */
    static record ArgumentExpression(
            Token identifier,
            Token assignation,
            Token stringLiteral
        ) implements Expression {

        public ArgumentExpression(Token identifer) {
            this(identifer,
                    new Token(TokenType.NONE, null, 0, 0),
                    new Token(TokenType.NONE, null, 0, 0));
        }

        @Override
        public void visit(Visitor visitor) {
            throw new UnsupportedOperationException("Unimplemented method 'visit'");
        }

        boolean isBoolean() {
            return assignation == null && stringLiteral == null;
        }
    }

}
