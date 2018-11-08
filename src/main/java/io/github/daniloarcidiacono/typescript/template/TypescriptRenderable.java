package io.github.daniloarcidiacono.typescript.template;

import io.github.daniloarcidiacono.typescript.template.visitor.TypescriptRenderableVisitor;

/**
 * Interface for an object that can be rendered to a {@link TypescriptStringBuilder}.
 * @author Danilo Arcidiacono
 */
public interface TypescriptRenderable {
    /**
     * Visitor pattern
     * @param visitor
     */
    void accept(final TypescriptRenderableVisitor visitor);

    /**
     * Appends the code to the builder.
     * @param sb the string builder
     */
    void render(final TypescriptStringBuilder sb);

    /**
     * Renders the code directly to a String.
     * @return the rendered code
     */
    default String render() {
        final TypescriptStringBuilder sb = new TypescriptStringBuilder();
        render(sb);

        return sb.toString();
    }

    /**
     * Renders the code directly to a String, using the provided string builder.
     * @param sb the string builder to use.
     * @return the rendered code
     */
    default String renderWith(final TypescriptStringBuilder sb) {
        render(sb);
        return sb.toString();
    }
}
