package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.Visitor;
import org.github.jmorla.kiwicompiler.visitor.VoidVisitor;

public interface Visitable {

    <R> R accept(Visitor<R> visitor);

    void accept(VoidVisitor visitor);
}
