package org.github.jmorla.kiwicompiler.visitor;

import org.github.jmorla.kiwicompiler.ast.Attribute;
import org.github.jmorla.kiwicompiler.ast.KiwiElement;
import org.github.jmorla.kiwicompiler.ast.Segment;
import org.github.jmorla.kiwicompiler.ast.SyntaxTree;

public class TestVisitor implements VoidVisitor {

    @Override
    public void visit(SyntaxTree n) {
        System.out.println("Hello world");
    }
    @Override
    public void visit(Segment.TextSegment n) {
        System.out.println("Segment text");
    }

    @Override
    public void visit(Segment.ImportDirective n) {
        System.out.println("Segment import");
    }

    @Override
    public void visit(Segment.RenderDirective n) {
        System.out.println("Segment render");
    }

    @Override
    public void visit(KiwiElement n) {
        System.out.println("KiwiElement");
    }

    @Override
    public void visit(Attribute n) {

    }
}
