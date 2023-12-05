package org.github.jmorla.kiwicompiler.visitor;

import org.github.jmorla.kiwicompiler.ast.directive.ImportDirective;
import org.github.jmorla.kiwicompiler.ast.directive.RenderDirective;

public interface GenericVisitor<R> {

    R visitImportDirective(ImportDirective directive);

    R visitRenderDirective(RenderDirective directive);
}
