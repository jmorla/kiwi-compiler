package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.Visitor;
import org.github.jmorla.kiwicompiler.visitor.VoidVisitor;

import java.util.List;

public class KiwiElement implements Visitable {

    private final String identifier;
    private final List<Attribute> attributes;

    public KiwiElement(String identifier) {
        this.identifier = identifier;
        attributes = null;
    }

    public KiwiElement(String identifier, List<Attribute> attributes) {
        this.identifier = identifier;
        this.attributes = attributes;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void accept(VoidVisitor visitor) {
        visitor.visit(this);
    }

    public String identifier() {
        return identifier;
    }

    public boolean hasAttributes() {
        return attributes != null;
    }

    public List<Attribute> attributes() {
        return attributes;
    }

}
