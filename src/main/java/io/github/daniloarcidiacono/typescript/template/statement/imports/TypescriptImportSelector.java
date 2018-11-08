package io.github.daniloarcidiacono.typescript.template.statement.imports;

import io.github.daniloarcidiacono.typescript.template.TypescriptRenderable;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * Import selector with optional alias.
 * @author Danilo Arcidiacono
 */
public class TypescriptImportSelector implements TypescriptRenderable {
    private String identifier;
    private String alias;

    public TypescriptImportSelector() {
    }

    public TypescriptImportSelector(final String identifier) {
        this.identifier = identifier;
    }

    public TypescriptImportSelector(final String identifier, final String alias) {
        this.identifier = identifier;
        this.alias = alias;
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        sb.append(identifier);
        if (alias != null) {
            sb.append(" as ");
            sb.append(alias);
        }
    }

    public String getIdentifier() {
        return identifier;
    }

    public TypescriptImportSelector setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public TypescriptImportSelector setAlias(String alias) {
        this.alias = alias;
        return this;
    }
}
