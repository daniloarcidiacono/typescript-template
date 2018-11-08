package io.github.daniloarcidiacono.typescript.template;

import io.github.daniloarcidiacono.commons.lang.FileCommons;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link TypescriptComments}.
 * @author Danilo Arcidiacono
 */
class TypescriptCommentsTest {
    @Test
    void test() {
        assertThrows(IllegalArgumentException.class, () -> new TypescriptComments().render(null));
        assertTrue(new TypescriptComments().isEmpty());
        assertFalse(new TypescriptComments("").isEmpty());
    }

    @Test
    void render() {
        assertEquals("", new TypescriptComments().render(), "Empty comments are not rendered");
        assertEquals("// comment\n", new TypescriptComments().comment("comment").render(), "One-liners use forward slash");
        assertEquals(FileCommons.loadResource("comment_01.ts"), new TypescriptComments().comment("first line").comment("second line").render(),  "Multi-line comments use JavaDoc format");
    }

    @Test
    void indentedComments() {
        final TypescriptStringBuilder sb = new TypescriptStringBuilder();
        sb.indent();

        new TypescriptComments().comment("comment").render(sb);
        assertEquals("\t// comment\n", sb.toString(), "Indented oneliners work");

        sb.clear();
        new TypescriptComments().comment("first line").comment("second line").render(sb);
        assertEquals(FileCommons.loadResource("comment_02.ts"), sb.toString(), "Indented multiliners work");
    }
}

