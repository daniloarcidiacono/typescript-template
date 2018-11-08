package io.github.daniloarcidiacono.typescript.template;

import io.github.daniloarcidiacono.typescript.template.declaration.TypescriptDeclaration;
import io.github.daniloarcidiacono.typescript.template.statement.TypescriptStatement;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a TypeScript source file.
 * @author Danilo Arcidiacono
 */
public class TypescriptSource implements TypescriptRenderable {
    private List<TypescriptStatement> statements = new ArrayList<>();

    public TypescriptSource statement(final TypescriptStatement statement) {
        statement.setSource(this);
        statements.add(statement);
        return this;
    }

    public TypescriptSource statement(final TypescriptStatement ...statement) {
        for (TypescriptStatement typescriptStatement : statement) {
            statement(typescriptStatement);
        }

        return this;
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);

        for (TypescriptStatement statement : statements) {
            statement.accept(visitor);
        }
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        int index = 0;
        for (TypescriptStatement statement : statements) {
            statement.render(sb);

            if (index + 1 < statements.size()) {
                if (statement instanceof TypescriptDeclaration) {
                    sb.appendln();
                }
            }

            index++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypescriptSource that = (TypescriptSource) o;
        return Objects.equals(statements, that.statements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statements);
    }

    public List<TypescriptStatement> getStatements() {
        return statements;
    }

    public TypescriptSource setStatements(final List<TypescriptStatement> statements) {
        this.statements = statements;
        return this;
    }
}
