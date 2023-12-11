package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.Visitor;
import org.github.jmorla.kiwicompiler.visitor.VoidVisitor;

public record Attribute(String identifier, String type, String value) implements Visitable {

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void accept(VoidVisitor visitor) {
        visitor.visit(this);
    }
}
