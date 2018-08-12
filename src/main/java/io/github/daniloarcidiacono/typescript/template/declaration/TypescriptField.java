package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.typescript.template.TypescriptComments;
import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;
import io.github.daniloarcidiacono.typescript.template.type.TypescriptType;

/**
 * TypeScript interface field declaration.
 * Comments to the field can be optionally provided.
 * @author Danilo Arcidiacono
 */
public class TypescriptField implements TypescriptDeclaration {
    public static final boolean OPTIONAL = false;

    // The comments that will be rendered before the enum declaration
    private TypescriptComments comments = new TypescriptComments();

    // The field TypeScript identifier
    private String identifier;

    // The type of the field
    private TypescriptType type;

    // Whether the field is mandatory or not
    private boolean mandatory;

    public TypescriptField(final String identifier, final TypescriptType type) {
        this.identifier = identifier;
        this.type = type;
        this.mandatory = OPTIONAL;
    }

    public TypescriptField(final String identifier, final TypescriptType type, final boolean mandatory) {
        this.identifier = identifier;
        this.type = type;
        this.mandatory = mandatory;
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

        comments.render(sb);
        sb.append(identifier);
        if (!mandatory) {
            sb.append("?");
        }
        sb.append(": ");
        sb.append(type.render());
        sb.append(";");
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public TypescriptType getType() {
        return type;
    }

    public void setType(TypescriptType type) {
        this.type = type;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public TypescriptComments getComments() {
        return comments;
    }

    public TypescriptField setComments(TypescriptComments comments) {
        this.comments = comments;
        return this;
    }
}