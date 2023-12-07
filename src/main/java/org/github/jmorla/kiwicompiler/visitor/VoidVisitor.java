package org.github.jmorla.kiwicompiler.visitor;

import org.github.jmorla.kiwicompiler.ast.Attribute;
import org.github.jmorla.kiwicompiler.ast.KiwiElement;
import org.github.jmorla.kiwicompiler.ast.Segment;
import org.github.jmorla.kiwicompiler.ast.SyntaxTree;

public interface VoidVisitor {

    void visit(SyntaxTree n);

    void visit(Segment segment);

    void visit(Segment.TextSegment n);

    void visit(Segment.ImportDirective n);

    void visit(Segment.RenderDirective n);

    void visit(KiwiElement n);

    void visit(Attribute n);
}
