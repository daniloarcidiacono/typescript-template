package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * The TypeScript number type.
 * @author Danilo Arcidiacono
 */
public class TypescriptNumberType implements TypescriptType {
    public static final TypescriptNumberType INSTANCE = new TypescriptNumberType();

    // Don't instance, use the INSTANCE field instead
    private TypescriptNumberType() {
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

        sb.append("number");
    }
}
