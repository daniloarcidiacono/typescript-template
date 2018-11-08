package io.github.daniloarcidiacono.typescript.template.declaration;

import io.github.daniloarcidiacono.commons.lang.FileCommons;
import io.github.daniloarcidiacono.typescript.template.TypescriptComments;
import io.github.daniloarcidiacono.typescript.template.TypescriptStringBuilder;
import io.github.daniloarcidiacono.typescript.template.type.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link TypescriptDeclaration}.
 * @author Danilo Arcidiacono
 */
class TypescriptDeclarationTest {
    @Test
    void aliasesAndGenerics() {
        assertEquals(
            FileCommons.loadResource("alias_01.ts"),
            new TypescriptAlias()
                .setIdentifier("MyAlias")
                .parameter("A")
                .parameter("B",
                    new TypescriptInterfaceType("Animal")
                        .argument(
                            new TypescriptStringConstantType("D")
                        )
                )
                .setAlias(
                    new TypescriptUnionType(
                        TypescriptStringType.INSTANCE,
                        TypescriptNumberType.INSTANCE,
                        TypescriptNullType.INSTANCE
                    )
                )
                .setComments(
                    new TypescriptComments()
                        .comment("Testing aliases")
                )
                .renderWith(
                    new TypescriptStringBuilder().indent()
                ),
            "Aliases work"
        );
    }

    @Test
    void enumerations() {
        assertEquals(
            FileCommons.loadResource("enum_01.ts"),
            new TypescriptEnum("MyEnum")
                .entry("key1", "value1", new TypescriptComments().comment("First key"))
                .entry("key2", "value2")
                .setComments(
                    new TypescriptComments()
                        .comment("This enum")
                        .comment("is well behaved")
                )
                .renderWith(
                    new TypescriptStringBuilder()
                        .indent()
                ),
            "Whos a good enum?"
        );
    }

    @Test
    void emptyEnumeration() {
        assertEquals(
            FileCommons.loadResource("enum_02.ts"),
            new TypescriptEnum("EmptyEnum")
                .renderWith(
                    new TypescriptStringBuilder()
                ),
            "Empty enums work"
        );
    }

    @Test
    void interfaces() {
        assertEquals(
            FileCommons.loadResource("interface_01.ts"),
            new TypescriptInterface("EmptyInterface")
                .renderWith(
                    new TypescriptStringBuilder()
                        .indent()
                ),
            "Empty interfaces work"
        );

        assertEquals(
            FileCommons.loadResource("interface_02.ts"),
            new TypescriptInterface("TestInterface")
                .extend(new TypescriptInterfaceType("SuperInterface"))
                .extend(new TypescriptInterfaceType("Animal").argument(TypescriptStringType.INSTANCE))
                .setComments(
                    new TypescriptComments()
                        .comment("Well-behaved interface")
                )
                .setTypeParameters(new TypescriptTypeParameters().parameter("A"))
                .fields(
                    new TypescriptField(
                        "name",
                        TypescriptStringType.INSTANCE
                    )
                    .setComments(new TypescriptComments().comment("The", "name")),
                    new TypescriptField(
                        "age",
                        TypescriptNumberType.INSTANCE,
                        TypescriptField.OPTIONAL
                    )
                    .setComments(new TypescriptComments().comment("Age"))
                )
                .renderWith(
                    new TypescriptStringBuilder()
                        .indent()
                ),
            "Interfaces work"
        );
    }
}