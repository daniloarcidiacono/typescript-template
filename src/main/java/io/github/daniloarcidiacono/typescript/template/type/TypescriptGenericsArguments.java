package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptRenderable;
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
    public void render(final TypescriptStringBuilder sb) {
        sb.append("<");
        int index = 0;
        for (TypescriptType generic : arguments) {
            generic.render(sb);
            if (index + 1 < arguments.size()) {
                sb.append(", ");
            }

            index++;
        }
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
