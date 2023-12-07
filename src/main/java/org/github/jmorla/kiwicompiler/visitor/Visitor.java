package org.github.jmorla.kiwicompiler.visitor;

import org.github.jmorla.kiwicompiler.ast.Attribute;
import org.github.jmorla.kiwicompiler.ast.SyntaxTree;
import org.github.jmorla.kiwicompiler.ast.KiwiElement;
import org.github.jmorla.kiwicompiler.ast.Segment;

public interface Visitor<R> {

    R visit(SyntaxTree n);

    R visit(Segment segment);

    R visit(Segment.TextSegment n);

    R visit(Segment.ImportDirective n);

    R visit(Segment.RenderDirective n);

    R visit(KiwiElement n);

    R visit(Attribute n);

}
