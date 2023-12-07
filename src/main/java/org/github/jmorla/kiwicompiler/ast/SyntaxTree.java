package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.visitor.Visitor;
import org.github.jmorla.kiwicompiler.visitor.VoidVisitor;

import java.util.List;

public class SyntaxTree implements Visitable {
    private List<Segment> segments;
    private boolean empty;

    public SyntaxTree(List<Segment> segments) {
        this.segments = segments;
    }

    public SyntaxTree() {
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
    @Override
    public void accept(VoidVisitor visitor) {
        visitor.visit(this);
    }

    public List<Segment> segments() {
        return this.segments;
    }
}
