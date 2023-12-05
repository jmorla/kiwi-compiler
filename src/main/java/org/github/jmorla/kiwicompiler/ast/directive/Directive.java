package org.github.jmorla.kiwicompiler.ast.directive;

import org.github.jmorla.kiwicompiler.KiwiToken;
import org.github.jmorla.kiwicompiler.ast.Visitable;

public abstract class Directive implements Visitable {
    private KiwiToken startText;
    private final KiwiToken directive;
    private final KiwiToken leftParenthesis;
    private final KiwiToken rightParenthesis;
    private KiwiToken endText;


    public Directive(KiwiToken directive, KiwiToken leftParenthesis, KiwiToken rightParenthesis) {
        this.directive = directive;
        this.leftParenthesis = leftParenthesis;
        this.rightParenthesis = rightParenthesis;
    }

    public Directive(KiwiToken startText, KiwiToken directive, KiwiToken leftParenthesis, KiwiToken rightParenthesis, KiwiToken endText) {
        this.startText = startText;
        this.directive = directive;
        this.leftParenthesis = leftParenthesis;
        this.rightParenthesis = rightParenthesis;
        this.endText = endText;
    }

    public KiwiToken startText() {
        return startText;
    }

    public KiwiToken directive() {
        return directive;
    }

    public KiwiToken leftParenthesis() {
        return leftParenthesis;
    }

    public KiwiToken rightParenthesis() {
        return rightParenthesis;
    }

    public KiwiToken endText() {
        return endText;
    }
}
