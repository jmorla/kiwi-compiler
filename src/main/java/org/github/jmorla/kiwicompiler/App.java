package org.github.jmorla.kiwicompiler;

import org.github.jmorla.kiwicompiler.generators.JsGenerator;
import org.github.jmorla.kiwicompiler.generators.TemplateGenerator;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var reader = new StringReader("""
            @import('Info')
            @render(<Info />)
            """);

        var scanner = new KiwiScanner(reader);
        var parser = new KiwiParser(scanner.scanTokens());
        var jsOutput = new StringWriter();
        var templateOutput = new StringWriter();
        var templateGenerator = new TemplateGenerator(templateOutput);
        var jsGenerator = new JsGenerator(jsOutput);
        var tree = parser.parse();
        tree.accept(templateGenerator);
        tree.accept(jsGenerator);
        System.out.println(jsOutput);
        System.out.println("----------------------------");
        System.out.println(templateOutput);
    }
}
