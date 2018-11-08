package io.github.daniloarcidiacono.typescript.template.type;

import io.github.daniloarcidiacono.typescript.template.TypescriptRenderable;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * TypeScript inheritance (extends and/or implements).
 * @author Danilo Arcidiacono
 */
public class TypescriptInheritanceArguments implements TypescriptRenderable {
    private List<TypescriptType> extended = new ArrayList<>();
    private List<TypescriptType> implemented = new ArrayList<>();

    public TypescriptInheritanceArguments() {
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
        for (TypescriptType typescriptType : extended) {
            typescriptType.accept(visitor);
        }

        for (TypescriptType typescriptType : implemented) {
            typescriptType.accept(visitor);
        }
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (!extended.isEmpty()) {
            sb.append("extends ");
            renderList(extended, sb);
        }

        if (!implemented.isEmpty()) {
            sb.append("implements ");
            renderList(implemented, sb);
        }
    }

    public static void renderList(final Collection<? extends TypescriptRenderable> list, final TypescriptStringBuilder sb) {
        int index = 0;
        for (TypescriptRenderable renderable : list) {
            renderable.render(sb);
            if (index + 1 < list.size()) {
                sb.append(", ");
            }

            index++;
        }
    }

    public TypescriptInheritanceArguments implement(final TypescriptType argument) {
        implemented.add(argument);
        return this;
    }

    public TypescriptInheritanceArguments extend(final TypescriptType argument) {
        extended.add(argument);
        return this;
    }

    public TypescriptInheritanceArguments implement(final TypescriptType ...arguments) {
        Collections.addAll(implemented, arguments);
        return this;
    }

    public TypescriptInheritanceArguments extend(final TypescriptType ...arguments) {
        Collections.addAll(extended, arguments);
        return this;
    }

    public boolean isEmpty() {
        return implemented.isEmpty() && extended.isEmpty();
    }

    public List<TypescriptType> getImplemented() {
        return implemented;
    }

    public void setImplemented(List<TypescriptType> implemented) {
        this.implemented = implemented;
    }

    public List<TypescriptType> getExtended() {
        return extended;
    }

    public void setExtended(List<TypescriptType> extended) {
        this.extended = extended;
    }
}
