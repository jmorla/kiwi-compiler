package org.github.jmorla.kiwicompiler;

import org.github.jmorla.kiwicompiler.ast.Attribute;
import org.github.jmorla.kiwicompiler.ast.SyntaxTree;
import org.github.jmorla.kiwicompiler.ast.KiwiElement;
import org.github.jmorla.kiwicompiler.ast.Segment;


import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.github.jmorla.kiwicompiler.KiwiToken.TokenType.*;

/**
 * Default Kiwi Recursive decent parser
 * */
public class KiwiParser {

    private final List<KiwiToken> tokens;

    private int index = 0;

    public KiwiParser(Reader reader) {
        var scanner = new KiwiScanner(reader);
        this.tokens = scanner.scanTokens();
    }

    public KiwiParser(String source) {
        StringReader reader = new StringReader(source);
        var scanner = new KiwiScanner(reader);
        this.tokens = scanner.scanTokens();
    }


    public SyntaxTree parse() {
        List<Segment> segments = new ArrayList<>();
        while(hasNext()) {
            segments.add(parseSegment());
        }
        if(segments.isEmpty()) {
            return new SyntaxTree();
        }
        return new SyntaxTree(segments);
    }

    private Segment parseSegment () {
        var token = next();
        return switch (token.type()) {
            case IMPORT -> parseImportDirective();
            case RENDER -> parseRenderDirective();
            case TEXT -> new Segment.TextSegment(token.lexeme());
            default -> throw new ParseException("unexpected token at line: %d received: %s"
                    .formatted(token.line(), token.lexeme()));
        };
    }

    private Segment.ImportDirective parseImportDirective() {
        expect(LEFT_PAREN);
        var arg0 = expect(LITERAL);
        if(check(RIGHT_PAREN)) {
            skip();
            return new Segment.ImportDirective(arg0);
        }
        var comma = expect(COMMA);
        var arg1 = expect(LITERAL);
        expect(RIGHT_PAREN);
        return new Segment.ImportDirective(arg0, comma, arg1);
    }

    private Segment.RenderDirective parseRenderDirective() {
        expect(LEFT_PAREN);
        var element = parseKiwiElement();
        expect(RIGHT_PAREN);

        return new Segment.RenderDirective(element);
    }

    private KiwiElement parseKiwiElement() {
        expect(LOWER_THAN);
        var identifier = expect(IDENTIFIER);
        var attributes = parseAttributes();
        expect(SELF_CLOSING);
        if(attributes.isEmpty()) {
            return new KiwiElement(identifier);
        }
        return new KiwiElement(identifier, attributes);
    }

    private List<Attribute> parseAttributes() {
        List<Attribute> attributes = new ArrayList<>();
        while(!match(GREATER_THAN, SELF_CLOSING)) {
            attributes.add(parseAttribute());
        }
        return attributes;
    }

    private Attribute parseAttribute() {
        var identifier = expect(IDENTIFIER);
        String type = "string";
        if(check(COLON)) {
            skip();
            type = expect(TYPE);
        }
        expect(EQUAL);
        var literal = expect(LITERAL);
        return new Attribute(identifier, type, literal);
    }

    private boolean hasNext() {
        return tokens.size() > index;
    }

    private KiwiToken next() {
        return tokens.get(index ++);
    }

    private KiwiToken peek() {
        return tokens.get(index);
    }

    private boolean match(KiwiToken.TokenType... types) {
        for(KiwiToken.TokenType type : types) {
            if(check(type)) {
                return true;
            }
        }
        return false;
    }

    private boolean check(KiwiToken.TokenType type) {
        return type.equals(peek().type());
    }

    private void skip() {
        index ++;
    }

    private String expect(KiwiToken.TokenType type) {
        if(!hasNext()) {
            throw new ParseException("Unexpected token: EOF");
        }
        var token = next();
        if(!type.equals(token.type())) {
            throw new ParseException("Unexpected token: \"%s\" at line %d expected \"%s\""
                    .formatted(token.lexeme(),token.line(), type.name));
        }
        return token.lexeme();
    }
}
