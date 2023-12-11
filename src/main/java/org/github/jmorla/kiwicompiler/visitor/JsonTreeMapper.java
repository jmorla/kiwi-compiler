package org.github.jmorla.kiwicompiler.visitor;

import org.github.jmorla.kiwicompiler.ast.*;

public class JsonTreeMapper implements VoidVisitor {

    private final StringBuilder json = new StringBuilder();


    public String getJsonString() {
        return json.toString();
    }

    @Override
    public void visit(SyntaxTree n) {
        json.append("""
                {"type":"SyntaxTree","isEmpty":%b""".formatted(n.isEmpty()));
        if (!n.isEmpty()) {
            json.append("""
                    , "segments":[""");

            for (int i = 0; i < n.segments().size(); i++) {
                if (i > 0) json.append(',');
                n.segments().get(i).accept(this);
            }
        } else {
            json.append("}");
        }
        json.append("]}");
    }

    @Override
    public void visit(Segment segment) {
    }

    @Override
    public void visit(Segment.TextSegment n) {
        json.append("""
                {"type":"%s","body":"%s"}""".formatted("TextSegment", n.text().replace("\n", "\\n")));
    }

    @Override
    public void visit(Segment.ImportDirective n) {
        json.append("""
                {"type": "%s"\s""".formatted("ImportDirective"));
        json.append("""
                ,"arguments":{ "arg0": "%s"\s""".formatted(n.arg0()));
        if (n.hasSingleArg()) {
            json.append("}}");
            return;
        }
        json.append("""
                ,"arg1":"%s"}}""".formatted(n.arg1()));

    }

    @Override
    public void visit(Segment.RenderDirective n) {
        json.append("""
                {"type": "%s", "element":""".formatted("RenderDirective"));
        n.element().accept(this);
        json.append('}');
    }

    @Override
    public void visit(KiwiElement n) {
        json.append("""
                { "name":"%s","attributes":[""".formatted(n.identifier()));
        if (n.hasAttributes()) {
            for (int i = 0; i < n.attributes().size(); i++) {
                if (i > 0) json.append(',');
                n.attributes().get(i).accept(this);
            }
        }
        json.append("]}");

    }

    @Override
    public void visit(Attribute n) {
        json.append('{')
                .append("\"name\": \"%s\",\"value\":\"%s\",\"type\":\"%s\"".formatted(n.identifier(), n.value(), n.type()))
                .append('}');
    }
}
