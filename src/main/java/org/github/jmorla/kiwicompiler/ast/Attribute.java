package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.VoidVisitor;

public record Attribute(String identifier, String type, String value) implements Visitable {

    @Override
    public void accept(VoidVisitor visitor) {
        visitor.visit(this);
    }
}
