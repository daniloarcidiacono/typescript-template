package io.github.daniloarcidiacono.typescript.template;

import java.util.Scanner;

/**
 * Collection of utilities used by tests.
 * @author Danilo Arcidiacono
 */
public abstract class TestUtils {
    /**
     * Builds a multi-line, indented, string.
     *
     * @param indent the indentation amount
     * @param lines the lines to concatenate
     * @return the built string
     */
    public static String concat(final int indent, final String ...lines) {
        final TypescriptStringBuilder sb = new TypescriptStringBuilder();
        sb.indent(indent);

        for (String line : lines) {
            sb.appendln(line);
        }

        return sb.toString();
    }

    /**
     * Sames as {@link #concat(int, String...)} but with no indentation.
     * @param lines the lines to concatenate
     * @return the built string
     */
    public static String concat(final String ...lines) {
        return concat(0, lines);
    }
}
