package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

public class TypescriptArrayType implements TypescriptType {
    private TypescriptType elementType;

    public TypescriptArrayType(TypescriptType elementType) {
        this.elementType = elementType;
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
        elementType.accept(visitor);
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        sb.append(elementType.render() + "[]");
    }

    public TypescriptType getElementType() {
        return elementType;
    }

    public void setElementType(TypescriptType elementType) {
        this.elementType = elementType;
    }
}
