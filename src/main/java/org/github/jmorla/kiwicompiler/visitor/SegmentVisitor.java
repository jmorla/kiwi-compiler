package org.github.jmorla.kiwicompiler.visitor;

import org.github.jmorla.kiwicompiler.ast.Segment;

public interface SegmentVisitor {

    void visit(Segment.TextSegment n);

    void visit(Segment.ImportDirective n);

    void visit(Segment.RenderDirective n);
}
