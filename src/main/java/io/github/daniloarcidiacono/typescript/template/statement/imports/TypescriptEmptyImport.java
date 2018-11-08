package io.github.daniloarcidiacono.typescript.template.statement.imports;

import io.github.daniloarcidiacono.commons.lang.StringCommons;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

/**
 * Default import syntax.
 * @author Danilo Arcidiacono
 */
public class TypescriptEmptyImport extends TypescriptImport {
    public TypescriptEmptyImport() {
    }

    public TypescriptEmptyImport(final String pathName) {
        super(pathName);
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        sb.append("import ");
        sb.append(StringCommons.singleQuote(pathName));
        sb.appendln(";");
    }
}
