package org.github.jmorla.kiwicompiler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KiwiScannerTest extends ScannerTestBase {

    @ParameterizedTest
    @ValueSource(strings = {"Hello world!", "<div>Content</div>", "10.44", "false", "//", "@", "@@", "@@@",
            "@@@@", "@%", "%%@", "44@23", "@novalid"})
    public void shouldScanTextToken(String text) {
        var tokens = scan(text);
        assertSize(tokens, 1);
        assertTextToken(tokens.get(0), text);
    }

    @Test
    public void shouldScanImportKeyword() {
        var tokens = scan("@import");
        assertSize(tokens, 1);
        assertImportToken(tokens.get(0));
    }

    @Test
    public void shouldScanRenderKeyword() {
        var tokens = scan("@render");
        assertSize(tokens, 1);
        assertRenderToken(tokens.get(0));
    }

    @ParameterizedTest
    @ValueSource(strings = { "(", "((", "(((", "()", "))", ")", "(()" })
    public void shouldScanParenthesisWhenInExpression(String lexemes) {
        var tokens = scan("@render" + lexemes);
        assertSize(tokens, lexemes.length() + 1);
    }

    @Test
    public void shouldScanTextAfterRightParenthesis() {
        var tokens = scan("@render)<span>Hello world</span>");
        var lastToken = tokens.get(tokens.size() - 1);

        assertSize(tokens, 3);
        assertTextToken(lastToken, "<span>Hello world</span>");
    }

    @Test
    public void shouldScanTextBeforeKeyword() {
        var tokens = scan("Hello @render");

        assertSize(tokens, 2);
        assertTextToken(tokens.get(0), "Hello ");
    }

    @ParameterizedTest
    @ValueSource(strings = {"value", "user", "limit", "__init", "value1",
            "identifer4343With00909Numbers2343", "longest_identifier_with_44324423_whatever__", "Component", "Component43" })
    public void shouldScanIdentifiers(String lexeme) {
        var tokens = scan("@render "+ lexeme);
        assertSize(tokens, 2);
        assertIdentifierToken(tokens.get(1), lexeme);
    }

    @Test
    public void shouldScanAttributeType() {
        var tokens = scan("@render bar:bool");
        for(var token: tokens) {
            System.out.println(token);
        }
    }
}
