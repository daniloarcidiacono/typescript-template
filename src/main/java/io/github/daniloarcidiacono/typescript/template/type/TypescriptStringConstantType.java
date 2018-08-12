package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * Represents a string literal constant.
 * @author Danilo Arcidiacono
 */
public class TypescriptStringConstantType implements TypescriptType {
    private String constant;

    public TypescriptStringConstantType(String constant) {
        this.constant = constant;
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        sb.append("'" + constant + "'");
    }
}
