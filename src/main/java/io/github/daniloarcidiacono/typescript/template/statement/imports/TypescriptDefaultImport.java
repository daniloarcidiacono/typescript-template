package io.github.daniloarcidiacono.typescript.template.statement.imports;

import io.github.daniloarcidiacono.commons.lang.StringCommons;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * Namespace import syntax.
 * @author Danilo Arcidiacono
 */
public class TypescriptDefaultImport extends TypescriptImport {
    private String importIdentifier;

    public TypescriptDefaultImport() {
    }

    public TypescriptDefaultImport(final String pathName) {
        super(pathName);
    }

    public TypescriptDefaultImport(final String pathName, final String importIdentifier) {
        super(pathName);
        this.importIdentifier = importIdentifier;
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
    }


    @Override
    public void render(final TypescriptStringBuilder sb) {
        sb.append("import ");
        sb.append(importIdentifier);
        sb.append(" from ");
        sb.append(StringCommons.singleQuote(pathName));
        sb.appendln(";");
    }

    public String getImportIdentifier() {
        return importIdentifier;
    }

    public TypescriptDefaultImport setImportIdentifier(String importIdentifier) {
        this.importIdentifier = importIdentifier;
        return this;
    }
}
