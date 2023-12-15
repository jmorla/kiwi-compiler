package org.github.jmorla.kiwicompiler.ast;

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
    public void accept(VoidVisitor visitor) {
        visitor.visit(this);
        for(var segment : segments) {
            switch (segment) {
                case Segment.ImportDirective i -> visitor.visit(i);
                case Segment.TextSegment t -> visitor.visit(t);
                case Segment.RenderDirective r -> visitor.visit(r);
            }
        }
    }

    public List<Segment> segments() {
        return this.segments;
    }
}
