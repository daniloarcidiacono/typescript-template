package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.typescript.template.TypescriptRenderable;

/**
 * Base class for a TypeScript declaration.
 *
 * @author Danilo Arcidiacono
 */
public interface TypescriptDeclaration extends TypescriptRenderable {
    /**
     * @return the identifier of the declaration.
     */
    String getIdentifier();
}
