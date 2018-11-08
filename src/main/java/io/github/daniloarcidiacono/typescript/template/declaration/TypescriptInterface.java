package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.typescript.template.*;
import io.github.daniloarcidiacono.typescript.template.type.TypescriptInheritanceArguments;
import io.github.daniloarcidiacono.typescript.template.type.TypescriptInterfaceType;
import io.github.daniloarcidiacono.typescript.template.type.TypescriptType;
import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * TypeScript interface declaration, with an optional superinterface and type parameters.
 * Comments to the declaration can be optionally provided.
 * @author Danilo Arcidiacono
 */
public class TypescriptInterface implements TypescriptDeclaration {
    // The source
    private TypescriptSource source;

    // The comments that will be rendered before the enum declaration
    private TypescriptComments comments = new TypescriptComments();

    // The interface TypeScript identifier
    private String identifier;

    // The inheritance data
    private TypescriptInheritanceArguments inheritance = new TypescriptInheritanceArguments();

    // Type parameters
    private TypescriptTypeParameters typeParameters = new TypescriptTypeParameters();

    // Fields of the interface
    private List<TypescriptField> fields = new ArrayList<>();

    // Creates an interface that does not extend another interface
    public TypescriptInterface(final String identifier) {
        this.identifier = identifier;
    }

    // Creates an interface that extends another one
    public TypescriptInterface(final String identifier, final TypescriptInterfaceType...extendedInterfaces) {
        this(identifier);
        this.inheritance.extend(extendedInterfaces);
    }

    public TypescriptInterface fields(final TypescriptField ...fields) {
        for (TypescriptField field : fields) {
            field(field);
        }

        return this;
    }

    public TypescriptInterface field(final TypescriptField field) {
        fields.add(field);
        return this;
    }

    public TypescriptInterface extend(final TypescriptType argument) {
        inheritance.extend(argument);
        return this;
    }

    public TypescriptInterface extend(final TypescriptType ...arguments) {
        inheritance.extend(arguments);
        return this;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public boolean isEmpty() {
        return fields.isEmpty() && inheritance.isEmpty();
    }

    @Override
    public void accept(final TypescriptRenderableVisitor visitor) {
        visitor.visit(this);
        comments.accept(visitor);
        inheritance.accept(visitor);
        typeParameters.accept(visitor);

        for (TypescriptField field : fields) {
            field.accept(visitor);
        }
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        // Render the documentation
        comments.render(sb);

        // Render the interface
        sb.append("export interface ");
        sb.append(identifier);
        if (!typeParameters.isEmpty()) {
            typeParameters.render(sb);
        }

        if (!inheritance.isEmpty()) {
            sb.append(" ");
            inheritance.render(sb);
        }
        sb.appendln(" {");

        // Render each field
        int index = 0;
        sb.indent();
        for (TypescriptField fieldDeclaration : fields) {
            // Add extra line for commented fields
            if (index > 0 && !fieldDeclaration.getComments().isEmpty()) {
                sb.appendln();
            }

            // Render the field declaration
            fieldDeclaration.render(sb);

            sb.appendln();
            index++;
        }

        sb.unindent();

        // End of interface
        sb.appendln("}");
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public TypescriptComments getComments() {
        return comments;
    }

    public TypescriptInterface setComments(TypescriptComments comments) {
        this.comments = comments;
        return this;
    }

    public List<TypescriptField> getFields() {
        return fields;
    }

    public TypescriptInterface setFields(List<TypescriptField> fields) {
        this.fields = fields;
        return this;
    }

    public TypescriptTypeParameters getTypeParameters() {
        return typeParameters;
    }

    public TypescriptInterface setTypeParameters(TypescriptTypeParameters typeParameters) {
        this.typeParameters = typeParameters;
        return this;
    }

    public TypescriptInheritanceArguments getInheritance() {
        return inheritance;
    }

    public void setInheritance(TypescriptInheritanceArguments inheritance) {
        this.inheritance = inheritance;
    }

    @Override
    public TypescriptSource getSource() {
        return source;
    }

    @Override
    public TypescriptInterface setSource(final TypescriptSource source) {
        this.source = source;
        return this;
    }
}
