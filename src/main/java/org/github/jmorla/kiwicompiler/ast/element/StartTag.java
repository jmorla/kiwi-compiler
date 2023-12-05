package org.github.jmorla.kiwicompiler.ast.element;

import org.github.jmorla.kiwicompiler.visitor.GenericVisitor;

public class StartTag extends Tag {

    @Override
    public <R> R accept(GenericVisitor<R> visitor) {
        return null;
    }
}
