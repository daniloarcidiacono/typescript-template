package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptRenderable;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * TypeScript generics arguments.
 * @author Danilo Arcidiacono
 */
public class TypescriptGenericsArguments implements TypescriptRenderable {
    // The arguments
    private List<TypescriptType> arguments = new ArrayList<>();

    public TypescriptGenericsArguments() {
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);

        for (TypescriptType argument : arguments) {
            argument.accept(visitor);
        }
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        sb.append("<");
        TypescriptInheritanceArguments.renderList(arguments, sb);
        sb.append(">");
    }

    public TypescriptGenericsArguments argument(final TypescriptType argument) {
        arguments.add(argument);
        return this;
    }

    public List<TypescriptType> getArguments() {
        return arguments;
    }

    public void setArguments(List<TypescriptType> arguments) {
        this.arguments = arguments;
    }
}
