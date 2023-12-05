package org.github.jmorla.kiwicompiler.ast.directive;

import java.util.List;
import java.util.Optional;

import org.github.jmorla.kiwicompiler.KiwiToken;
import org.github.jmorla.kiwicompiler.visitor.GenericVisitor;

public class ImportDirective extends Directive {
    private final KiwiToken arg0;
    private final KiwiToken arg1;
    public ImportDirective(KiwiToken directive, KiwiToken leftParenthesis, KiwiToken arg0, KiwiToken rightParenthesis) {
        this(directive, leftParenthesis, arg0, null, rightParenthesis);
    }

    public ImportDirective(KiwiToken directive, KiwiToken leftParenthesis,
                           KiwiToken arg0, KiwiToken arg1, KiwiToken rightParenthesis) {
        super(directive, leftParenthesis, rightParenthesis);
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    public KiwiToken arg0() {
        return arg0;
    }

    public Optional<KiwiToken> arg1() {
        return Optional.ofNullable(arg1);
    }


    @Override
    public <R> R accept(GenericVisitor<R> visitor) {
        return visitor.visitImportDirective(this);
    }
}
