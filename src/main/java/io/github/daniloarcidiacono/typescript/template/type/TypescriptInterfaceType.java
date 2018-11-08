package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * An interface type, with optional generics arguments.1
 * @author Danilo Arcidiacono
 */
public class TypescriptInterfaceType implements TypescriptType {
    private String identifier;
    private TypescriptGenericsArguments genericsArguments;

    public TypescriptInterfaceType(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Shortcut for adding a generic argument
     * @param argument the argument to add
     * @return the class instance for chaining further calls.
     */
    public TypescriptInterfaceType argument(final TypescriptType argument) {
        if (genericsArguments == null) {
            genericsArguments = new TypescriptGenericsArguments();
        }

        genericsArguments.argument(argument);
        return this;
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
        if (genericsArguments != null) {
            genericsArguments.accept(visitor);
        }
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        sb.append(identifier);
        if (genericsArguments != null) {
            genericsArguments.render(sb);
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public TypescriptInterfaceType setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public TypescriptGenericsArguments getGenericsArguments() {
        return genericsArguments;
    }

    public TypescriptInterfaceType setGenericsArguments(TypescriptGenericsArguments genericsArguments) {
        this.genericsArguments = genericsArguments;
        return this;
    }
}
