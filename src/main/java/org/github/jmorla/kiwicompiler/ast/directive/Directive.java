package org.github.jmorla.kiwicompiler.ast;

import org.github.jmorla.kiwicompiler.KiwiToken;

public abstract class Directive implements Visitable {
    private KiwiToken startText;
    private KiwiToken directive;
    private KiwiToken leftParenthesis;
    private KiwiToken rightParenthesis;
    private KiwiToken endText;
}
