package io.github.daniloarcidiacono.typescript.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link TypescriptStringBuilder}.
 * @author Danilo Arcidiacono
 */
class TypescriptStringBuilderTest {
    private TypescriptStringBuilder sb;

    @BeforeEach
    void start() {
        sb = new TypescriptStringBuilder();
    }

    @Test
    void appendLnWorks() {
        sb.indent(1);
        assertEquals("\n\n", sb.appendln().appendln().toString());
        assertEquals(TypescriptStringBuilder.Status.LINE_START, sb.getStatus());

        sb.clear();
        assertEquals(TestUtils.loadResource("lines_04.txt"), sb.append("test\n\ntest").toString());
    }

    @Test
    void indentationDefaults() {
        assertEquals(TypescriptStringBuilder.TAB, sb.getIndentChar(), "Default indentation is with tabs");
        sb.setIndentChar(TypescriptStringBuilder.SPACES);
        assertEquals(TypescriptStringBuilder.SPACES, sb.getIndentChar(), "Indentation char can be replaced");
    }

    @Test
    void emptyCase() {
        assertEquals("", sb.toString(), "Empty case");
    }

    @Test
    void oneLiner() {
        sb.append("line");
        assertEquals("line", sb.toString(), "One line");
    }

    @Test
    void twoLines() {
        sb.appendln("first line");
        sb.appendln("second line");
        assertEquals("first line\nsecond line\n", sb.toString(), "Two lines");
    }

    @Test
    void redundantNewLineTrimmed() {
        sb.appendln("line\n");
        assertEquals("line\n", sb.toString(), "Redundant newline is trimmed");
    }

    @Test
    void indentation() {
        sb.indent();
        assertEquals(1, sb.getIndentLevel());

        sb.appendln("line");
        sb.appendln("line2");
        assertEquals(TestUtils.loadResource("lines_01.txt"), sb.toString());

        sb.unindent();
        sb.unindent();
        assertEquals(0, sb.getIndentLevel(), "Indent level capped to zero");
    }

    @Test
    void smartBehaviour() {
        assertEquals(
            TestUtils.loadResource("lines_02.txt"),
            sb.indent(1).appendln("This\nis\nSparta\n").toString(),
            "Multilines are splitted and indented"
        );

        assertEquals(TypescriptStringBuilder.Status.LINE_START, sb.getStatus());
        sb.clear().setIndentLevel(1);
        assertEquals(
            TestUtils.loadResource("lines_03.txt"),
            sb.append("this ").append("is ").appendln("a chunk\nNew line").toString(),
            "Chunks can be joined"
        );
    }
}