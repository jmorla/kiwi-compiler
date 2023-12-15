package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.VoidVisitor;

public interface Visitable {

    void accept(VoidVisitor visitor);
}
