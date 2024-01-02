package org.github.jmorla.kiwicompiler;

import org.github.jmorla.kiwicompiler.ast.SyntaxTree;
import org.github.jmorla.kiwicompiler.generators.JsGenerator;
import org.github.jmorla.kiwicompiler.generators.HtmlGenerator;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public final class KiwiCompiler {

    public void compile(Reader source, Writer tOut, Writer jsOut) {
        var tokens = new KiwiScanner(source).scanTokens();
        var syntaxTree = new KiwiParser(tokens).parse();
        compileHtml(syntaxTree, tOut);
        compileJs(syntaxTree, jsOut);
    }

    public String compileJs(String source) {
        var writer = new StringWriter();
        compileJs(new StringReader(source), writer);
        return writer.toString();
    }

    public void compileJs(Reader source, Writer out) {
        var tokens = new KiwiScanner(source).scanTokens();
        var syntaxTree = new KiwiParser(tokens).parse();
        compileJs(syntaxTree, out);
    }

    public String compileHtml(String source) {
        var writer = new StringWriter();
        compileHtml(new StringReader(source), writer);
        return writer.toString();
    }

    public void compileHtml(Reader source, Writer out) {
        var tokens = new KiwiScanner(source).scanTokens();
        var syntaxTree = new KiwiParser(tokens).parse();
        compileHtml(syntaxTree, out);
    }

    private void compileJs(SyntaxTree tree, Writer jsOut) {
        tree.accept(new JsGenerator(jsOut));
    }

    private void compileHtml(SyntaxTree tree, Writer htmlOut) {
        tree.accept(new HtmlGenerator(htmlOut));
    }
}
