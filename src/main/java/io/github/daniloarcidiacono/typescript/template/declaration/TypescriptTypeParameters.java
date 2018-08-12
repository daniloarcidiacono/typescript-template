package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.typescript.template.TypescriptRenderable;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;
import io.github.daniloarcidiacono.typescript.template.type.TypescriptType;

import java.util.ArrayList;
import java.util.List;

/**
 * TypeScript generics type parameters that can be attached to an interface declaration or to a type alias.
 * @author Danilo Arcidiacono
 */
public class TypescriptTypeParameters implements TypescriptRenderable {
    // The parameters
    private List<TypescriptTypeParameter> parameters = new ArrayList<>();

    public TypescriptTypeParameters() {
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        sb.append("<");
        int index = 0;
        for (TypescriptTypeParameter generic : parameters) {
            generic.render(sb);
            if (index + 1 < parameters.size()) {
                sb.append(", ");
            }

            index++;
        }
        sb.append(">");
    }

    public TypescriptTypeParameters parameter(final String identifier) {
        parameters.add(new TypescriptTypeParameter(identifier));
        return this;
    }

    public TypescriptTypeParameters parameter(final String identifier, final TypescriptType extendedType) {
        parameters.add(new TypescriptTypeParameter(identifier).setExtendedType(extendedType));
        return this;
    }

    public TypescriptTypeParameters parameter(final TypescriptTypeParameter generic) {
        parameters.add(generic);
        return this;
    }

    public List<TypescriptTypeParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<TypescriptTypeParameter> parameters) {
        this.parameters = parameters;
    }
}
