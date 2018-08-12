package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * The TypeScript void type.
 * @author Danilo Arcidiacono
 */
public class TypescriptVoidType implements TypescriptType {
    public static final TypescriptVoidType INSTANCE = new TypescriptVoidType();

    // Don't instance, use the INSTANCE field instead
    private TypescriptVoidType() {
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        sb.append("void");
    }
}
