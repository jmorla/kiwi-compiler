package org.github.jmorla.kiwicompiler.ast.element;

import org.github.jmorla.kiwicompiler.visitor.GenericVisitor;
import java.util.List;

public class ContainerElement extends KiwiElement {

    private StartTag startTag;
    private List<KiwiElement> children;
    private EndTag endTag;


    @Override
    public <R> R accept(GenericVisitor<R> visitor) {
        return null;
    }
}
