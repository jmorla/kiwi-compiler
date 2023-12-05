package org.github.jmorla.kiwicompiler.ast.directive;

import org.github.jmorla.kiwicompiler.KiwiToken;
import org.github.jmorla.kiwicompiler.ast.element.KiwiElement;
import org.github.jmorla.kiwicompiler.visitor.GenericVisitor;

public class RenderDirective extends Directive {
    private final KiwiElement element;

    public RenderDirective(KiwiToken directive,
                           KiwiToken leftParenthesis,
                           KiwiElement element,
                           KiwiToken rightParenthesis) {
        super(directive, leftParenthesis, rightParenthesis);
        this.element = element;
    }

    public KiwiElement element() {
        return element;
    }

    @Override
    public <R> R accept(GenericVisitor<R> visitor) {
        return visitor.visitRenderDirective(this);
    }
}
