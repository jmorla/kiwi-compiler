package org.github.jmorla.kiwicompiler.ast;


import org.github.jmorla.kiwicompiler.visitor.SegmentVisitor;
import org.github.jmorla.kiwicompiler.visitor.Visitable;

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


    public List<Segment> segments() {
        return this.segments;
    }

    @Override
    public void accept(SegmentVisitor visitor) {
        for(var segment : segments) {
            segment.accept(visitor);
        }
    }
}
