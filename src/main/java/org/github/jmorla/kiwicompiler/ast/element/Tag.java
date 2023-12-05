package org.github.jmorla.kiwicompiler.ast.element;

import org.github.jmorla.kiwicompiler.KiwiToken;
import org.github.jmorla.kiwicompiler.ast.Visitable;

import java.util.List;

public abstract class Tag implements Visitable {

    private KiwiToken start;
    private KiwiToken identifier;
    private List<Attribute> attributes;
    private KiwiToken end;
}
