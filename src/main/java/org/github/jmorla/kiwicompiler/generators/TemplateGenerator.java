package org.github.jmorla.kiwicompiler.generators;

import org.github.jmorla.kiwicompiler.ast.Segment;
import org.github.jmorla.kiwicompiler.visitor.SegmentVisitor;

import java.io.PrintWriter;
import java.io.Writer;


public class TemplateGenerator implements SegmentVisitor, AutoCloseable {
    private final PrintWriter out;

    public TemplateGenerator(Writer writer) {
        out = new PrintWriter(writer);
    }

    @Override
    public void visit(Segment.TextSegment n) {
        if(n.text().isBlank()) {
            return;
        }
        out.println(n.text());
    }

    @Override
    public void visit(Segment.ImportDirective n) {
    }

    @Override
    public void visit(Segment.RenderDirective n) {
        var element = n.element();
        out.print("<div data-kiwi-id=\"");
        out.print(element.identifier());
        out.print(Math.abs(element.hashCode()));
        out.print('"');
        if(element.hasAttributes()) {
            for (var attributes : element.attributes()) {
                out.print(" data-prop-");
                out.print(attributes.identifier());
                out.print("=");
                out.print('"');
                out.print(attributes.value());
                out.print('"');
            }
        }
        out.print("></div>\n");
    }

    @Override
    public void close() {
        out.close();
    }
}
