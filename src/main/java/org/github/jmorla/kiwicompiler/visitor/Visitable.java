package org.github.jmorla.kiwicompiler.visitor;

public interface Visitable {

    void accept(SegmentVisitor visitor);
}
