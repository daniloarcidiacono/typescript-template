package io.github.daniloarcidiacono.typescript.template.type;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link TypescriptType}.
 * @author Danilo Arcidiacono
 */
class TypescriptTypeTest {
    @Test
    void test() {
        assertEquals("any", TypescriptAnyType.INSTANCE.render());
        assertEquals("boolean", TypescriptBooleanType.INSTANCE.render());
        assertEquals("null", TypescriptNullType.INSTANCE.render());
        assertEquals("undefined", TypescriptUndefinedType.INSTANCE.render());
        assertEquals("number", TypescriptNumberType.INSTANCE.render());
        assertEquals("string", TypescriptStringType.INSTANCE.render());
        assertEquals("void", TypescriptVoidType.INSTANCE.render());
        assertEquals("'constant_literal'", new TypescriptStringConstantType("constant_literal").render());
        assertEquals("string[]", new TypescriptArrayType(TypescriptStringType.INSTANCE).render());
        assertEquals("string | undefined", new TypescriptUnionType(TypescriptStringType.INSTANCE, TypescriptUndefinedType.INSTANCE).render());
        assertEquals("MyEnum", new TypescriptEnumType("MyEnum").render());
    }
}