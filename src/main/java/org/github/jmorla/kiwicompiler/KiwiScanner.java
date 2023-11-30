package org.github.jmorla.kiwicompiler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.github.jmorla.kiwicompiler.TokenType.*;

/**
 * Kiwi default Scanner
 *
 * @author Jorge L. Morla
 * */
public class KiwiScanner {
    private final Reader source;
    private final List<Token> tokens = new ArrayList<>();
    private int column = 0;
    private int line = 1;
    private boolean expression = false;
    private final StringBuilder text = new StringBuilder();

    private static final Map<String, TokenType> keywords;

    static {
        keywords = new HashMap<>();
        keywords.put("import", IMPORT);
        keywords.put("render", RENDER);
    }

    public KiwiScanner(Reader source) {
        if (source.markSupported()) {
            this.source = source;
        } else {
            this.source = new BufferedReader(source);
        }
    }

    public List<Token> scanTokens() {
        int c;
        while (!isEOF(c = readNext())) {
            if(c == '@') {
                expression = true;
            }
            if(expression) {
                scanExpressionToken((char) c);
            } else {
                text.append((char) c);
            }
        }
        if(!text.isEmpty()) {
            addToken(TokenType.TEXT, text.toString());
        }

        return tokens;
    }

    private void scanExpressionToken(char c) {
        switch (c) {
            case '@':
                addKeywordToken(c);
                break;
            case '(':
                addToken(TokenType.LEFT_PAREN, String.valueOf(c));
                break;
            case ')':
                addToken(TokenType.RIGHT_PAREN, String.valueOf(c));
                text.setLength(0);
                expression = false;
                break;
            case ',':
                addToken(TokenType.COMMA, String.valueOf(c));
                break;
            case '-':
                addToken(TokenType.MINUS, String.valueOf(c));
                break;
            case '>':
                addToken(TokenType.GREATER_THAN, String.valueOf(c));
                break;
            case '<':
                var next = (char) readNext();
                if(next == '/') {
                    addToken(CLOSING, "</");
                    break;
                } else {
                    reset();
                }
                addToken(TokenType.LOWER_THAN, String.valueOf(c));
                break;
            case '=':
                addToken(TokenType.EQUAL, String.valueOf(c));
                break;
            case '/':
                next = (char) readNext();
                if (next == '>') {
                    addToken(SELF_CLOSING, "/>");
                    break;
                } else {
                    throw new ScannerException("Expected character", String.valueOf('>'), line);
                }
            case '"':
                addStringToken('"');
                break;
            case '\'':
                addStringToken('\'');
            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n':
                line++;
                column = 0;
                break;
            default:
                if (isAlphabet(c)) {
                    addIdentifierToken(c);
                    break;
                }
                throw new ScannerException("Unexpected character", String.valueOf(c), line);
        }
    }

    private boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z') ||
                c == '_';
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlphabet(c) || isDigit(c);
    }

    private void addKeywordToken(char c) {
        StringBuilder sb = new StringBuilder();

        while (isAlphaNumeric((char) peek())) {
            sb.append((char) readNext());
        }
        String value = sb.toString();
        if(keywords.containsKey(value)) {
            if(!text.isEmpty()) {
                addToken(TokenType.TEXT, text.toString());
                text.setLength(0);
            }
            addToken(keywords.get(value), value);
        } else {
            text.append(c);
            text.append(sb);
            expression = false;
        }

    }

    private void addIdentifierToken(char c) {
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        int v;
        while (!isEOF(v = readNext()) && isAlphaNumeric((char) v)) {
            sb.append((char) v);
        }

        reset();

        addToken(IDENTIFIER, sb.toString());

    }

    private void addStringToken(char delimiter) {
        StringBuilder sb = new StringBuilder();
        int c;
        while (!isEOF(c = readNext()) && c != delimiter) {
            if (c == '\n') line++;
            sb.append((char) c);
        }

        if (isEOF(c)) {
            throw new ScannerException("Undermined string");
        }

        addToken(STRING, sb.toString());
    }

    private void addToken(TokenType type, String lexeme) {
        tokens.add(new Token(type, lexeme, line, column));
    }

    private int readNext() {
        try {
            column++;
            source.mark(1);
            return source.read();
        } catch (IOException ex) {
            throw new ScannerException(ex, line);
        }
    }

    private int peek() {
        var c = readNext();
        reset();
        return c;
    }

    private void reset() {
        try {
            column --;
            source.reset();
        } catch (IOException ex) {
            throw new ScannerException(ex, line);
        }
    }

    private boolean isEOF(int c) {
        return c == -1;
    }
}
