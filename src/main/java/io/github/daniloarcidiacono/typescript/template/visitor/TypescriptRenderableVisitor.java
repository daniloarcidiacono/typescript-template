package io.github.daniloarcidiacono.typescript.template.visitor;

import io.github.daniloarcidiacono.typescript.template.TypescriptRenderable;

/**
 * Visitor for a {@link TypescriptRenderable}.
 * @author Danilo Arcidiacono
 */
public interface TypescriptRenderableVisitor {
    /**
     * @param renderable
     */
    void visit(final TypescriptRenderable renderable);
}
