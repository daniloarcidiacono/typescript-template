package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.typescript.template.TypescriptComments;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;
import io.github.daniloarcidiacono.typescript.template.TypescriptSource;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;
import io.github.daniloarcidiacono.typescript.template.type.TypescriptType;

/**
 * TypeScript type alias declaration.
 * Comments to the declaration can be optionally provided.
 * @author Danilo Arcidiacono
 */
public class TypescriptAlias implements TypescriptDeclaration {
    // The source
    private TypescriptSource source;

    // The comments that will be rendered before the enum declaration
    private TypescriptComments comments = new TypescriptComments();

    // The interface TypeScript identifier
    private String identifier;

    // The optional type parameters attached to the identifier
    private TypescriptTypeParameters typeParameters;

    // The alias type
    private TypescriptType alias;

    public TypescriptAlias() {
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
        comments.accept(visitor);
        typeParameters.accept(visitor);
        alias.accept(visitor);
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        comments.render(sb);
        sb.append("export type ");
        sb.append(identifier);
        if (typeParameters != null) {
            typeParameters.render(sb);
        }
        sb.append(" = ");
        alias.render(sb);
        sb.appendln(";");
    }

    /**
     * Shortcut for adding a type parameter.
     * @param identifier the identifier of the type parameter to add.
     * @return the class instance for chaining further calls.
     */
    public TypescriptAlias parameter(final String identifier) {
        if (typeParameters == null) {
            typeParameters = new TypescriptTypeParameters();
        }
        typeParameters.parameter(identifier);
        return this;
    }

    /**
     * Shortcut for adding a type parameter.
     * @param identifier the identifier of the type parameter to add.
     * @param extendedType the type that the identifier must extend.
     * @return the class instance for chaining further calls.
     */
    public TypescriptAlias parameter(final String identifier, final TypescriptType extendedType) {
        if (typeParameters == null) {
            typeParameters = new TypescriptTypeParameters();
        }
        typeParameters.parameter(identifier, extendedType);
        return this;
    }

    /**
     * Shortcut for adding a type parameter.
     * @param parameter the parameter to add
     * @return the class instance for chaining further calls.
     */
    public TypescriptAlias parameter(final TypescriptTypeParameter parameter) {
        if (typeParameters == null) {
            typeParameters = new TypescriptTypeParameters();
        }
        typeParameters.parameter(parameter);
        return this;
    }

    public TypescriptTypeParameters getTypeParameters() {
        return typeParameters;
    }

    public TypescriptAlias setTypeParameters(TypescriptTypeParameters typeParameters) {
        this.typeParameters = typeParameters;
        return this;
    }

    public TypescriptComments getComments() {
        return comments;
    }

    public TypescriptAlias setComments(TypescriptComments comments) {
        this.comments = comments;
        return this;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    public TypescriptAlias setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public TypescriptType getAlias() {
        return alias;
    }

    public TypescriptAlias setAlias(TypescriptType alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public TypescriptSource getSource() {
        return source;
    }

    @Override
    public TypescriptAlias setSource(final TypescriptSource source) {
        this.source = source;
        return this;
    }
}
