package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.typescript.template.TypescriptComments;
import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * TypeScript interface declaration, with an optional superinterface and type parameters.
 * Comments to the declaration can be optionally provided.
 * @author Danilo Arcidiacono
 */
public class TypescriptInterface implements TypescriptDeclaration {
    // The comments that will be rendered before the enum declaration
    private TypescriptComments comments = new TypescriptComments();

    // The interface TypeScript identifier
    private String identifier;

    // The optional TypeScript identifier of the superinterface
    // Set to null if there is no super interface
    private String superIdentifier;

    // Type parameters
    private TypescriptTypeParameters typeParameters;

    // Fields of the interface
    private List<TypescriptField> fields = new ArrayList<>();

    public TypescriptInterface(final String identifier) {
        // Creates an interface that does not extend another interface
        this.identifier = identifier;
    }

    public TypescriptInterface(final String identifier, final String superIdentifier) {
        // Creates an interface that extends another one
        this.identifier = identifier;
        this.superIdentifier = superIdentifier;
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

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void render(final TypescriptStringBuilder sb) {
        if (sb == null) {
            throw new IllegalArgumentException(TypescriptExceptionMessages.ILLEGAL_TYPESCRIPT_BUILDER);
        }

        // Render the documentation
        comments.render(sb);

        // Render the interface
        if (superIdentifier == null) {
            sb.appendln("export interface " + identifier + " {");
        } else {
            sb.appendln("export interface " + identifier + " extends " + superIdentifier + " {");
        }

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

    public String getSuperIdentifier() {
        return superIdentifier;
    }

    public TypescriptInterface setSuperIdentifier(String superIdentifier) {
        this.superIdentifier = superIdentifier;
        return this;
    }
}
