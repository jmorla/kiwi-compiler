package org.github.jmorla.kiwicompiler;

import java.io.StringReader;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        var reader = new StringReader("""
                   @render(<VideoCard variant='primary'>
                                <Item value="Hello world"/>
                           </VideoCard>) pupupupupu jefrey klk
                """);
        var kiwiScanner = new KiwiScanner(reader);
        var tokens = kiwiScanner.scanTokens();
        tokens.stream().map(e -> e.type() + ": " + e.lexeme())
                .forEach(System.out::println);
    }
}
