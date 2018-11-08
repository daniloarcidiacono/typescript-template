package io.github.daniloarcidiacono.typescript.template.statement.imports;

import io.github.daniloarcidiacono.commons.lang.StringCommons;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Namespace import syntax.
 * @author Danilo Arcidiacono
 */
public class TypescriptNamespaceImport extends TypescriptImport {
    private List<TypescriptImportSelector> selectors = new ArrayList<>();

    public TypescriptNamespaceImport() {
    }

    public TypescriptNamespaceImport(final String pathName) {
        super(pathName);
    }

    public TypescriptNamespaceImport selector(final TypescriptImportSelector selector) {
        selectors.add(selector);
        return this;
    }

    public TypescriptNamespaceImport selector(final TypescriptImportSelector ...selector) {
        Collections.addAll(selectors, selector);
        return this;
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
        for (TypescriptImportSelector selector : selectors) {
            selector.accept(visitor);
        }
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        sb.append("import ");
        sb.append("{ ");
        int index = 0;
        for (TypescriptImportSelector selector : selectors) {
            selector.render(sb);
            if (index + 1< selectors.size()) {
                sb.append(", ");
            }
            index++;
        }

        sb.append(" }");
        sb.append(" from ");
        sb.append(StringCommons.singleQuote(pathName));
        sb.appendln(";");
    }

    public List<TypescriptImportSelector> getSelectors() {
        return selectors;
    }

    public TypescriptNamespaceImport setSelectors(List<TypescriptImportSelector> selectors) {
        this.selectors = selectors;
        return this;
    }
}
