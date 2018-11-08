	// Well-behaved interface
	export interface TestInterface<A> extends SuperInterface, Animal<string> {
		/**
		 * The
		 * name
		 */
		name?: string;

		// Age
		age?: number;
	}
