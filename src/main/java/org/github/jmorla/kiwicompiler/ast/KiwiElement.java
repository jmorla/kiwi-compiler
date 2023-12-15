package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.VoidVisitor;

import java.util.List;

public record KiwiElement (String identifier, List<Attribute> attributes) implements Visitable {

    public KiwiElement(String identifier) {
        this(identifier, null);
    }
    @Override
    public void accept(VoidVisitor visitor) {
        visitor.visit(this);
    }

    public boolean hasAttributes() {
        return attributes != null;
    }

}
