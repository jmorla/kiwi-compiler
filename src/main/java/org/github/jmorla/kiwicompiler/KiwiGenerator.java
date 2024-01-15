package org.github.jmorla.kiwicompiler;

import org.github.jmorla.kiwicompiler.ast.Attribute;
import org.github.jmorla.kiwicompiler.ast.Segment;
import org.github.jmorla.kiwicompiler.ast.SyntaxTree;
import org.github.jmorla.kiwicompiler.visitor.SegmentVisitor;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class KiwiGenerator {

    private final Configuration config;

    private KiwiGenerator(Configuration config) {
        this.config = config;
    }

    public record Configuration(
            boolean useDefaultImports,
            String baseImportPath,
            boolean includeReactImports
        ) {
    }

    public static KiwiGenerator withDefaults() {
        return new KiwiGenerator(new Configuration(true, "./", true));
    }

    public static KiwiGeneratorBuilder with() {
        return new KiwiGeneratorBuilder();
    }

    
    public boolean constainsDirectives(Reader source) {
        SyntaxTree tree = buildSyntaxTree(source);
        return tree.constainsDirective();
    }

    private SyntaxTree buildSyntaxTree(Reader source) {
        var tokens = new KiwiScanner(source).scanTokens();
        return new KiwiParser(tokens).parse();
    }


    public void generate(Reader source, Writer tOut, Writer jsOut) {
        var syntaxTree = buildSyntaxTree(source);
        compileHtml(syntaxTree, tOut);
        compileJs(syntaxTree, jsOut);
    }

    public String generateJs(String source) {
        var writer = new StringWriter();
        generateJs(new StringReader(source), writer);
        return writer.toString();
    }

    public void generateJs(Reader source, Writer out) {
        var tokens = new KiwiScanner(source).scanTokens();
        var syntaxTree = new KiwiParser(tokens).parse();
        compileJs(syntaxTree, out);
    }

    public String generateHtml(String source) {
        var writer = new StringWriter();
        generateHtml(new StringReader(source), writer);
        return writer.toString();
    }

    public void generateHtml(Reader source, Writer out) {
        SyntaxTree syntaxTree = buildSyntaxTree(source);
        compileHtml(syntaxTree, out);
    }

    private void compileJs(SyntaxTree tree, Writer jsOut) {
        tree.accept(new JsGenerator(config, jsOut));
    }

    private void compileHtml(SyntaxTree tree, Writer htmlOut) {
        tree.accept(new HtmlGenerator(htmlOut));
    }

    public static class KiwiGeneratorBuilder {
        private boolean useDefaultImports;
        private String baseImportPath;
        private boolean includeReactImports;

        public KiwiGeneratorBuilder defaultImports(boolean value) {
            this.useDefaultImports = value;
            return this;
        }

        public KiwiGeneratorBuilder baseImportPath(String path) {
            this.baseImportPath = path;
            return this;
        }

        public KiwiGeneratorBuilder includeReactImports(boolean value) {
            this.includeReactImports = value;
            return this;
        }

        public KiwiGenerator build() {
            var config = new Configuration(useDefaultImports, baseImportPath, includeReactImports);
            return new KiwiGenerator(config);
        }
    }

    private static class JsGenerator implements SegmentVisitor, AutoCloseable {
        private final PrintWriter out;
        private final Set<String> ids = new HashSet<>();
        private final Configuration config;

        public JsGenerator(Configuration config, Writer writer) {
            this.config = config;
            out = new PrintWriter(writer);
            printGeneratedNote();
            if (config.includeReactImports) {
                printReactImports();
            }
        }

        @Override
        public void visit(Segment.TextSegment n) {
        }

        @Override
        public void visit(Segment.ImportDirective n) {
            out.print("import ");
            if (config.useDefaultImports()) {
                out.print(n.arg0());
            } else {
                out.print("{ ");
                out.print(n.arg0());
                out.print(" }");
            }
            out.print(" from '");
            out.print(n.hasSingleArg() ? config.baseImportPath : Path.of(config.baseImportPath).resolve(n.arg1()).normalize());
            out.println("';");
        }

        @Override
        public void visit(Segment.RenderDirective n) {
            var element = n.element();
            var baseIdentifier = (element.identifier() + Math.abs(element.hashCode()));
            if (ids.contains(baseIdentifier)) {
                return;
            }
            ids.add(baseIdentifier);
            var elementsVarName = baseIdentifier.toLowerCase() + "_elements";
            out.print("\n");
            out.print("const ");
            out.print(elementsVarName);
            out.print(" = ");
            out.print("document.querySelectorAll('[data-kiwi-id=\"");
            out.print(baseIdentifier);
            out.println("\"]');");

            out.print("for(let element of ");
            out.print(elementsVarName);
            out.println(") {");
            if (element.hasAttributes()) {
                printProps(element.attributes());
            }
            out.println("\tconst root = ReactDOM.createRoot(element);");
            out.print("\troot.render(");
            out.print("<");
            out.print(element.identifier());
            if (element.hasAttributes()) {
                out.print(" {...props} ");
            }
            out.println("/>);");
            out.println("}");
        }

        private void printProps(List<Attribute> attributes) {
            out.println("\tconst props = {");
            for (var attribute : attributes) {
                out.print("\t\t");
                out.print(attribute.identifier());
                out.print(": ");
                if (attribute.type().equals(Attribute.AttributeType.BOOLEAN)) {
                    out.print("'true' == ");
                }
                if (attribute.type().equals(Attribute.AttributeType.NUMERIC)) {
                    out.print("Number");
                }
                out.print("(element.getAttribute('data-prop-");
                out.print(attribute.identifier());
                out.println("')),");
            }
            out.println("\t};");
        }

        private void printGeneratedNote() {
            out.print("""
                    /*
                        WARNING: AUTO-GENERATED CODE BY KIWI-GENERATOR
                        Modifying this code may result in unintended behavior.
                        Please make changes in the source template or consult
                        the kiwi-react-generator documentation for customization options.
                    */
                    """);
        }

        private void printReactImports() {
            out.println("import React from 'react';");
            out.println("import ReactDOM from 'react-dom/client';");
        }

        @Override
        public void close() {
            out.close();
        }
    }

    private static class HtmlGenerator implements SegmentVisitor, AutoCloseable {
        private final PrintWriter out;

        public HtmlGenerator(Writer writer) {
            out = new PrintWriter(writer);
        }

        @Override
        public void visit(Segment.TextSegment n) {
            if (n.text().isBlank()) {
                return;
            }
            out.println(n.text().trim());
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
            if (element.hasAttributes()) {
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
}
