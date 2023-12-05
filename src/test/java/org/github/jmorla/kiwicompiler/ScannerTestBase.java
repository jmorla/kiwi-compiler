package org.github.jmorla.kiwicompiler;

import org.junit.jupiter.api.Assertions;

import java.io.StringReader;
import java.util.List;

import static org.github.jmorla.kiwicompiler.KiwiToken.TokenType.*;

public class ScannerTestBase {

    protected static List<KiwiToken> scan(String template) {
        var reader = reader(template);
        var scanner = new KiwiScanner(reader);
        return scanner.scanTokens();
    }

    protected static void assertSize(List<KiwiToken> tokens, int size) {
        Assertions.assertEquals(size, tokens.size());
    }

    protected static void assertIdentifierToken(KiwiToken token, String lexeme){
        assertToken(token, IDENTIFIER, lexeme);
    }

    protected static void assertTextToken(KiwiToken token, String lexeme) {
        assertToken(token, TEXT, lexeme);
    }

    protected static void assertRenderToken(KiwiToken token) {
        assertToken(token, RENDER, "render");
    }

    protected static void assertImportToken(KiwiToken token) {
        assertToken(token, IMPORT, "import");
    }

    protected static void assertToken(KiwiToken token, KiwiToken.TokenType type, String lexeme) {
        Assertions.assertEquals(lexeme, token.lexeme());
        Assertions.assertEquals(type, token.type());
    }

    protected static StringReader reader(String template) {
        return new StringReader(template);
    }
}
