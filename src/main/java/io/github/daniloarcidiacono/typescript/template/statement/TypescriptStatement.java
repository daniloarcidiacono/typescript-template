package io.github.daniloarcidiacono.typescript.template.statement;

import io.github.daniloarcidiacono.typescript.template.TypescriptRenderable;
import io.github.daniloarcidiacono.typescript.template.TypescriptSource;

public interface TypescriptStatement extends TypescriptRenderable {
    /**
     * @return the source to which this statement belongs, or null.
     */
    TypescriptSource getSource();

    /**
     * Sets the source to which the statement belongs.
     * @param source
     */
    TypescriptStatement setSource(final TypescriptSource source);
}
