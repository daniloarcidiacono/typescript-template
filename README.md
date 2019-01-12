[![Maven Central](https://img.shields.io/maven-central/v/io.github.daniloarcidiacono/typescript-template.svg?label=Maven%20Central)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.github.daniloarcidiacono%22%20a%3A%22typescript-template%22)
[![codecov](https://codecov.io/gh/daniloarcidiacono/typescript-template/branch/master/graph/badge.svg)](https://codecov.io/gh/daniloarcidiacono/typescript-template)
[![CircleCI](https://circleci.com/gh/daniloarcidiacono/typescript-template/tree/master.svg?style=svg)](https://circleci.com/gh/daniloarcidiacono/typescript-template/tree/master)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

# Typescript Template
This is a library to write formatted TypeScript code using a fluent API.

Here is a simple initialization and usage example:

```java
public class Main {
    public static void main(String[] args) {
        final TypescriptSource source = new TypescriptSource()
            .statement(
                new TypescriptNamespaceImport("../address.d.ts")
                    .selector(new TypescriptImportSelector("Address")),
                new TypescriptInterface(
                    "Person",
                    new TypescriptInterfaceType("Object")
                )
                .setComments(new TypescriptComments("Represents a person."))
                .field(new TypescriptField("name", TypescriptStringType.INSTANCE, TypescriptField.MANDATORY))
                .field(new TypescriptField("age", TypescriptNumberType.INSTANCE, TypescriptField.MANDATORY))
                .field(
                    new TypescriptField("address", new TypescriptInterfaceType("Address"))
                        .setComments(new TypescriptComments("Address of the person.", "(optional field)"))
                )
            );

        // Pretty print
        System.out.println(source.render());
    }
}
```

outputs:

```typescript
import { Address } from '../address.d.ts';
// Represents a person.
export interface Person extends Object {
	name: string;
	age: number;

	/**
	 * Address of the person.
	 * (optional field)
	 */
	address?: Address;
}
```

### Supported language constructs

* Primitive types (`undefined`, `null`, `any`, `void`, `boolean`, `number`, `string`);
* Arrays;
* Dictionaries (e.g. `{ [id: string]: IPerson; }`);
* Generics;
* Union types (e.g. `string | number`);
* Interface declarations, including inheritance and generic arguments;
* Enum declarations;
* Type aliases declarations;
* Imports;
* Comments (single and multiline);

Some basic static analysis can be performed by visiting the AST:

```java
    public class Main {
        public static void main(String[] args) {

            final TypescriptSource source = ...;
            
            // Print dependencies
            System.out.println("Dependencies:");
            source.accept(renderable -> {
                if (renderable instanceof TypescriptInterfaceType) {
                    System.out.println(((TypescriptInterfaceType) renderable).getIdentifier());
                }
            });
        }
    } 
```

yields:

```
Dependencies:
Object
Address
```

The latest version is available on [Maven Central](https://search.maven.org/artifact/io.github.daniloarcidiacono/typescript-template).