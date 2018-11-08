package io.github.daniloarcidiacono.typescript.template;

import io.github.daniloarcidiacono.typescript.template.statement.TypescriptStatement;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Typescript comment block (single or multi-line).
 * @author Danilo Arcidiacono
 */
public class TypescriptComments implements TypescriptStatement {
    // The source
    private TypescriptSource source;

    // Comment text
    private List<String> lines = new ArrayList<>();

    public TypescriptComments() {
    }

    public TypescriptComments(final String ...lines) {
        this(Arrays.asList(lines));
    }

    public TypescriptComments(final Collection<String> lines) {
        this.lines.addAll(lines);
    }

    /**
     * Adds a new comment line.
     * @param line the comment line text
     * @return the object instance for chaining further calls
     */
    public TypescriptComments comment(final String line) {
        lines.add(line);
        return this;
    }

    public TypescriptComments comment(final String ...lines) {
        for (String line : lines) {
            comment(line);
        }

        return this;
    }

    /**
     * @return whether there are no comment lines.
     */
    public boolean isEmpty() {
        return lines.isEmpty();
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        if (lines.size() == 1) {
            sb.appendln("// " + lines.get(0));
        }

        if (lines.size() > 1) {
            sb.appendln("/**");
            for (String line : lines) {
                sb.appendln(" * " + line);
            }

            sb.appendln(" */");
        }
    }

    @Override
    public TypescriptSource getSource() {
        return source;
    }

    @Override
    public TypescriptComments setSource(final TypescriptSource source) {
        this.source = source;
        return this;
    }
}
