package org.github.jmorla.kiwicompiler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.StringReader;

public class KiwiScannerTest {
    @ParameterizedTest
    @ValueSource(strings = {"Hello world!", "<div>Content</div>", "10.44", "false", "//", "@", "@@", "@@@",
            "@@@@", "@%", "%%@", "44@23", "@novalid"})
    public void shouldScanTextToken(String text) {
        var template = reader(text);
        var scanner = new KiwiScanner(template);
        var tokens = scanner.scanTokens();
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals(TokenType.TEXT, tokens.get(0).type());
        Assertions.assertEquals(text, tokens.get(0).lexeme());
    }


    private StringReader reader(String template) {
        return new StringReader(template);
    }
}
