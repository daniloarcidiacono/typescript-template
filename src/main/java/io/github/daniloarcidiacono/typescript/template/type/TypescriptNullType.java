package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * The TypeScript null type.
 * @author Danilo Arcidiacono
 */
public class TypescriptNullType implements TypescriptType {
    public static final TypescriptNullType INSTANCE = new TypescriptNullType();

    // Don't instance, use the INSTANCE field instead
    private TypescriptNullType() {
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        sb.append("null");
    }
}
