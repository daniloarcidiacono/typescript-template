package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.typescript.template.statement.TypescriptStatement;

/**
 * Base class for a TypeScript declaration.
 *
 * @author Danilo Arcidiacono
 */
public interface TypescriptDeclaration extends TypescriptStatement {
    /**
     * @return the identifier of the declaration.
     */
    String getIdentifier();

    /**
     * @return whether the declaration is empty
     */
    boolean isEmpty();
}
