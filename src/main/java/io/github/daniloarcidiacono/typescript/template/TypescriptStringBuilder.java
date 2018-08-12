package io.github.daniloarcidiacono.typescript.template;

/**
 * String builder that supports indentation (tabs are used as default).
 * @author Danilo Arcidiacono
 */
public class TypescriptStringBuilder {
    enum Status {
        LINE_START,
        LINE_BEGAN
    }

    public static final String TAB = "\t";
    public static final String SPACES = "    ";

    private Status status = Status.LINE_START;
    private int indentLevel = 0;
    private String indentChar = TAB;
    private String indentPrefix = "";
    private StringBuilder sb = new StringBuilder();

    public TypescriptStringBuilder() {
    }

    /**
     * Clears the builder.
     * @return the class instance for chaining further calls.
     */
    public TypescriptStringBuilder clear() {
        sb.setLength(0);
        status = Status.LINE_START;
        return this;
    }

    /**
     * Appends a line along with a new line.
     * @param chunk the chunk to append. If it already contains a new line, it is reused.
     * @return the class instance for further calls.
     */
    public TypescriptStringBuilder appendln(final String chunk) {
        if (!chunk.endsWith("\n")) {
            return append(chunk + "\n");
        }

        return append(chunk);
    }

    /**
     * Appends a new line.
     * @return the class instance for further calls.
     */
    public TypescriptStringBuilder appendln() {
        return appendln("");
    }

    /**
     * Appends a chunk, respecting indentation.
     * @param chunk The chunk (can be multilined)
     * @return the class instance for further calls.
     */
    public TypescriptStringBuilder append(final String chunk) {
        int startIndex = 0;

        while (startIndex < chunk.length()) {
            int endIndex = chunk.indexOf('\n', startIndex);
            if (endIndex == -1) {
                endIndex = chunk.length();
            }

            if (status == Status.LINE_START) {
                // When endIndex == startIndex, we are appending an empty line
                // By convention, empty lines do not have the indentation
                if (endIndex > startIndex) {
                    sb.append(indentPrefix);
                }

                status = Status.LINE_BEGAN;
            }

            sb.append(chunk.substring(startIndex, endIndex));
            if (endIndex < chunk.length()) {
                sb.append('\n');
                status = Status.LINE_START;
            }

            // Next chunk
            startIndex = endIndex + 1;
        }

        return this;
    }

    /**
     * Increments the indentation level.
     * @return the class instance for chaining further calls.
     */
    public TypescriptStringBuilder indent() {
        indent(1);
        return this;
    }

    /**
     * Decrements the indentation level, to a minimum of zero (no indentation).
     * @return the class instance for chaining further calls.
     */
    public TypescriptStringBuilder unindent() {
        indent(-1);
        return this;
    }

    /**
     * Indents or unindents by the specified amount.
     * @param amount the amount of indentation (positive) or unindentation (negative)
     * @return the class instance for chaining further calls.
     */
    public TypescriptStringBuilder indent(final int amount) {
        return setIndentLevel(indentLevel + amount);
    }

    public int getIndentLevel() {
        return indentLevel;
    }

    /**
     * Sets the indentation level to the specified value.
     * @param indentLevel the indentation level. Values below zero are clamped.
     * @return the class instance for chaining further calls.
     */
    public TypescriptStringBuilder setIndentLevel(final int indentLevel) {
        if (indentLevel != this.indentLevel) {
            // Break the current line
            if (status != Status.LINE_START) {
                appendln();
            }

            this.indentLevel = indentLevel;
            if (this.indentLevel < 0) {
                this.indentLevel = 0;
            }

            buildIndentPrefix();
        }

        return this;
    }

    public String getIndentChar() {
        return indentChar;
    }

    /**
     * Changes the indentation char.
     * @param indentChar the indentation char
     * @see #TAB
     * @see #SPACES
     * @return the class instance for chaining further calls.
     */
    public TypescriptStringBuilder setIndentChar(final String indentChar) {
        this.indentChar = indentChar;
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }

    private void buildIndentPrefix() {
        indentPrefix = "";
        for (int i = 0; i < indentLevel; i++) {
            indentPrefix = indentPrefix + indentChar;
        }
    }

    public Status getStatus() {
        return status;
    }
}
