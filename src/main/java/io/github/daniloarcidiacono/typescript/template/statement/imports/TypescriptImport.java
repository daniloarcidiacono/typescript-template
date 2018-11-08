package io.github.daniloarcidiacono.typescript.template.statement.imports;

import io.github.daniloarcidiacono.typescript.template.TypescriptSource;
import io.github.daniloarcidiacono.typescript.template.statement.TypescriptStatement;

/**
 * Represents a TypeScript import statement.
 * @author Danilo Arcidiacono
 */
public abstract class TypescriptImport implements TypescriptStatement {
    protected TypescriptSource source;
    protected String pathName;

    public TypescriptImport() {
    }

    public TypescriptImport(final String pathName) {
        this.pathName = pathName;
    }

    public String getPathName() {
        return pathName;
    }

    public TypescriptImport setPathName(String pathName) {
        this.pathName = pathName;
        return this;
    }

    @Override
    public TypescriptSource getSource() {
        return source;
    }

    @Override
    public TypescriptImport setSource(final TypescriptSource source) {
        this.source = source;
        return this;
    }
}
