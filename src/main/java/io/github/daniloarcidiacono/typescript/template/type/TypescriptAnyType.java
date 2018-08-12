package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * The TypeScript any type.
 * @author Danilo Arcidiacono
 */
public class TypescriptAnyType implements TypescriptType {
    public static final TypescriptAnyType INSTANCE = new TypescriptAnyType();

    // Don't instance, use the INSTANCE field instead
    private TypescriptAnyType() {
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        sb.append("any");
    }
}
