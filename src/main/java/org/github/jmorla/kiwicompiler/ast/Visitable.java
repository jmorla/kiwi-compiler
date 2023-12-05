package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.GenericVisitor;

public interface Visitable {

    public abstract <R> R accept(GenericVisitor<R> visitor);
}
