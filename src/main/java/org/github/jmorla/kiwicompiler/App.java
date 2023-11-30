package org.github.jmorla.kiwicompiler;

import java.io.StringReader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var reader = new StringReader("@render((()");
        var kiwiScanner = new KiwiScanner(reader);
        var tokens = kiwiScanner.scanTokens();
        tokens.stream().map(e -> e.type() + ": " + e.lexeme())
                .forEach(System.out::println);
    }
}
