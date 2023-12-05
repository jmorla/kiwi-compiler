package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.GenericVisitor;

public class RenderDirective extends Directive {

    @Override
    public <R> R accept(GenericVisitor<R> visitor) {
        return null;
    }
}
