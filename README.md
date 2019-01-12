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