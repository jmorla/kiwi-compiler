package org.github.jmorla.kiwicompiler.ast.element;

import org.github.jmorla.kiwicompiler.visitor.GenericVisitor;

public class BooleanAttribute extends Attribute {
    @Override
    public <R> R accept(GenericVisitor<R> visitor) {
        return null;
    }
}
