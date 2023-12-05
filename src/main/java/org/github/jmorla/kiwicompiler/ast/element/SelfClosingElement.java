package org.github.jmorla.kiwicompiler.ast.element;

import org.github.jmorla.kiwicompiler.KiwiToken;
import org.github.jmorla.kiwicompiler.visitor.GenericVisitor;

import java.util.List;

public class SelfClosingElement extends KiwiElement {

    private KiwiElement start;
    private List<Attribute> attributes;
    private KiwiToken end;

    @Override
    public <R> R accept(GenericVisitor<R> visitor) {
        return null;
    }
}
