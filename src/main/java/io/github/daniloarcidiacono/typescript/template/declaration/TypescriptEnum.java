package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.typescript.template.TypescriptComments;
import io.github.daniloarcidiacono.typescript.template.TypescriptExceptionMessages;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * TypeScript enumeration declaration.
 * Comments to both the declaration and the entries can be optionally provided.
 * @author Danilo Arcidiacono
 */
public class TypescriptEnum implements TypescriptDeclaration {
    static class EnumEntry {
        private String identifier;
        private String value;
        private TypescriptComments comments;

        public EnumEntry(String identifier, String value, TypescriptComments comments) {
            this.identifier = identifier;
            this.value = value;
            this.comments = comments;
        }

        public String getIdentifier() {
            return identifier;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public TypescriptComments getComments() {
            return comments;
        }

        public void setComments(TypescriptComments comments) {
            this.comments = comments;
        }
    }

    // The comments that will be rendered before the enum declaration
    private TypescriptComments comments = new TypescriptComments();

    // The enum entries
    private List<EnumEntry> entries = new ArrayList<>();

    // The enum TypeScript identifier
    private String identifier;

    public TypescriptEnum(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * Adds an entry to the enum.
     * @param key the enum identifier
     * @param value the enum value
     * @param comments the enum comments
     * @return the class instance for further calls.
     */
    public TypescriptEnum entry(final String key, final String value, final TypescriptComments comments) {
        entries.add(new EnumEntry(key, value, comments));
        return this;
    }

    /**
     * Adds an entry to the enum (without comments).
     * @param key the enum identifier
     * @param value the enum value
     * @return the class instance for further calls.
     */
    public TypescriptEnum entry(final String key, final String value) {
        return entry(key, value, null);
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

        // Render the enum declaration
        sb.appendln("export enum " + identifier + " {");
        sb.indent();
        int index = 0;
        for (EnumEntry entry : entries) {
            if (entry.getComments() != null) {
                entry.getComments().render(sb);
            }

            if (index + 1 < entries.size()) {
                sb.appendln(entry.getIdentifier() + " = " + entry.getValue() + ",");
            } else {
                sb.appendln(entry.getIdentifier() + " = " + entry.getValue());
            }

            index++;
        }

        sb.unindent();

        // Close the declaration
        sb.appendln("}");
    }

    public TypescriptEnum setIdentifier(final String identifier) {
        this.identifier = identifier;
        return this;
    }

    public TypescriptComments getComments() {
        return comments;
    }

    public TypescriptEnum setComments(final TypescriptComments comments) {
        this.comments = comments;
        return this;
    }
}
