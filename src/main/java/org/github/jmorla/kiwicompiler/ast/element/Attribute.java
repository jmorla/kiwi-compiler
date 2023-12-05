package org.github.jmorla.kiwicompiler.ast.element;

import org.github.jmorla.kiwicompiler.KiwiToken;
import org.github.jmorla.kiwicompiler.ast.Visitable;

public abstract class Attribute implements Visitable {
    private KiwiToken identifier;
}
