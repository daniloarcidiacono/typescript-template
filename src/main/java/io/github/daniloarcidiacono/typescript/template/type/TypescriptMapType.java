package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * Represents a map type.
 * @author Danilo Arcidiacono
 */
public class TypescriptMapType implements TypescriptType {
    private TypescriptType keyType;
    private TypescriptType valueType;

    public TypescriptMapType() {
    }

    public TypescriptMapType(TypescriptType keyType, TypescriptType valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
        keyType.accept(visitor);
        valueType.accept(visitor);
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (keyType.equals(TypescriptStringType.INSTANCE) || keyType.equals(TypescriptNumberType.INSTANCE)) {
            sb.append("{ [ index: ");
            keyType.render(sb);
            sb.append(" ]: ");
            valueType.render(sb);
            sb.append(" }");
        } else {
            sb.append("Map<");
            keyType.render(sb);
            sb.append(", ");
            valueType.render(sb);
            sb.append(">");
        }
    }

    public TypescriptType getKeyType() {
        return keyType;
    }

    public void setKeyType(TypescriptType keyType) {
        this.keyType = keyType;
    }

    public TypescriptType getValueType() {
        return valueType;
    }

    public void setValueType(TypescriptType valueType) {
        this.valueType = valueType;
    }
}
