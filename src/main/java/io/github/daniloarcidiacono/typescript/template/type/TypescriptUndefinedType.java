package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * The TypeScript undefined type.
 * @author Danilo Arcidiacono
 */
public class TypescriptUndefinedType implements TypescriptType {
    public static final TypescriptUndefinedType INSTANCE = new TypescriptUndefinedType();

    // Don't instance, use the INSTANCE field instead
    private TypescriptUndefinedType() {
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
    }


    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        sb.append("undefined");
    }
}
