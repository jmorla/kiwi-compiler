package org.github.jmorla.kiwicompiler.ast;


import org.github.jmorla.kiwicompiler.ast.Segment.ImportDirective;
import org.github.jmorla.kiwicompiler.ast.Segment.RenderDirective;
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

    public boolean constainsDirective() {
        if (segments == null) {
            return false;
        }
        for (var segment : segments) {
            if (segment instanceof RenderDirective) {
                return true;
            }
            if (segment instanceof ImportDirective) {
                return true;
            }
        }
        return false;
    }


    public List<Segment> segments() {
        return this.segments;
    }

    @Override
    public void accept(SegmentVisitor visitor) {
        if (segments == null) {
            return;
        }
        for(var segment : segments) {
            segment.accept(visitor);
        }
    }
}
