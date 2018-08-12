package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.typescript.template.TypescriptRenderable;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;
import io.github.daniloarcidiacono.typescript.template.type.TypescriptType;

/**
 * A type parameter declaration, that can extend another type.
 * @see TypescriptTypeParameters
 * @author Danilo Arcidiacono
 */
public class TypescriptTypeParameter implements TypescriptRenderable {
    private String identifier;
    private TypescriptType extendedType;

    public TypescriptTypeParameter(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public TypescriptTypeParameter setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public TypescriptType getExtendedType() {
        return extendedType;
    }

    public TypescriptTypeParameter setExtendedType(TypescriptType extendedType) {
        this.extendedType = extendedType;
        return this;
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        sb.append(identifier);
        if (extendedType != null) {
            sb.append(" extends ");
            extendedType.render(sb);
        }
    }
}
