package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

import java.util.*;

/**
 * Represents a union of one of more types.
 * @author Danilo Arcidiacono
 */
public class TypescriptUnionType implements TypescriptType {
    private List<TypescriptType> types = new ArrayList<>();

    public TypescriptUnionType(final TypescriptType ...types) {
        this(Arrays.asList(types));
    }

    public TypescriptUnionType(final Collection<TypescriptType> types) {
        this.types.addAll(types);
    }

    public TypescriptUnionType type(final TypescriptType type) {
        types.add(type);
        return this;
    }

    public TypescriptUnionType type(final TypescriptType ...type) {
        Collections.addAll(types, type);
        return this;
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);

        for (TypescriptType type : types) {
            type.accept(visitor);
        }
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        String separator = "";

        for (TypescriptType type : types) {
            sb.append(separator);
            sb.append(type.render());
            separator =  " | ";
        }
    }

    public List<TypescriptType> getTypes() {
        return types;
    }

    public void setTypes(List<TypescriptType> types) {
        this.types = types;
    }
}
