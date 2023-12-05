package org.github.jmorla.kiwicompiler;

import org.github.jmorla.kiwicompiler.ast.CompilationUnit;
import org.github.jmorla.kiwicompiler.ast.directive.ImportDirective;
import org.github.jmorla.kiwicompiler.ast.directive.RenderDirective;

import static org.github.jmorla.kiwicompiler.KiwiToken.TokenType.*;

import java.util.List;

/**
 * Default Kiwi Recursive decent parser
 * */
public class KiwiParser {

    private final List<KiwiToken> tokens;

    private int index;

    private KiwiParser(List<KiwiToken> tokens) {
        this.tokens = tokens;
    }


    public CompilationUnit parse() {
        return null;
    }

    private ImportDirective parseImportDirective() {
        return null;
    }

    private RenderDirective parseRenderDirective() {
        return null;
    }

    private KiwiToken peek() {
        return tokens.get(index);
    }

    private KiwiToken next() {
        return tokens.get(index ++);
    }

    private boolean matchType(KiwiToken.TokenType... types) {
        for(var type : types) {
            if(isType(type)) {
                return true;
            }
        }
        return false;
    }

    private boolean isType(KiwiToken.TokenType type) {
        var current = peek();
        return current.type().equals(type);
    }
}
