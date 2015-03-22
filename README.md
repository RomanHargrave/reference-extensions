Scala Reference Extensions
==========================

Being that scala is a JVM language, it is inevitable that you will have to deal with the nuisance that is the
native null reference. Because null is a native type, not an object, and furthermore assignable to any field of any type,
it is necessary to introduce unneded, non-orthagonal complexity in to software by introducing null checks:

```java
Object myObject	= null
myObject.toString()				// => NullPointerException
null.toString()					// => Syntax error

(null instanceof Object)		// => false
(myObject instanceof Object)	// => false

(myObject == null)				// => true
(boolean) myObject				// => CastException
```

the above code demonstrates some properties of null references in java. While the behaviour of a null reference is partially consistent with the
behaviour of a null reference (`((void*) 0x0)`) in *C*, it cannot be evaluated as a boolean value in a predicate (this is really only possible in C as magic numbers are used in place of native boolean types).
It is furthermore both unnecessary, as Java is a high level language and executed in a platform-agnostic virtual machine, and also because Java is an object-oriented language that *does not* allow for distinction between 
values and references by the programmer in the same way as *C* does, and therefore could have easily implemented a null type as opposed to a null pointer, and could have furthermore added nullable types for better
compile-time bug checking. That being said, what has been done has been done, and java will likely not see the reengineering of null references in the near future. Additionally, what with `null` being the only way
for java developers to succinctly refer to emptiness, it is inevitably present as a significant value in nearly every java API written since the conception of java.

Some languages such as ruby approach the issue of the null reference by defining it in terms of an object, rather
than a primitive type. This allows the developer to approach the null reference with sane and idiomatic code such as

```ruby
myvar = nil
myvar.nil?			# => true
myvar.is_a? Object	# => true

myvar = Object.new
myvar.nil?			# => false
myvar.is_a? Object	# => true
```

Other languages, like Scala and Java 8, the concept of emptiness is may be expressed using optional types

```scala
var myObject	= Option.empty[Object]
myObject.isEmpty	// => true
myObject.get		// => NoSuchElementException
myObject.toString	// => None

myObject		= Some(new Object)
myObject.isEmpty	// => false
myObject.get		// => Object@...
myObject.toString	// => Some(Object@...)

myObject		= Option(null)
myObject.isEmpty	// => true
```

and that is all fine and well; however, the existence of that pernicious null reference has not been terminated,
mostly for the sake of compatibility. Through the use of `Option.apply`, nullable references *can* be handled in
client code, but it often makes for unneeded layers of boilerplate and wrappers in places where it can add visual
complexity to code, and dissuade potential users and contributors.

My intention with `reference-extensions` is to provide some layer of abstraction to *every* reference in scala by
adding implicit decorations and converters that are accessible in a modular and idiomatic fashion which may be leveraged
to concisely deal with potential null refernces.

```scala
import info.hargrave.refext._

val innocentField	= VeryEnterpriseJava14LibrarySingleton.getInstance.getPotentiallyNullReference
// innocentField	== null

// Concise explicit conversion of N-typed reference to N-typed Option
innocentField?				// => None: Option[N]
innocentField.optional		// => None: Option[N]

// Strict non-null assertion via Unary `!`
innocentField!				// => NullPointerException("innocentField is null")

// Implicit conversion of Product to boolean (implicit bridging of isEmpty)
None : Boolean				// => false

// Unary null reference check via Product -> Boolean conversion
innocentField? : Boolean	// => false

// Boolean coercion of references (if imported), can be used seamlessly in a predicate
(innocentField: Boolean)	// => false

// Option coercion of references
(innocentField: Option)		// => None

// Default value shorthand in the same manner as Option#getOrElse
innocentField orElse "yes"	// => "yes"
```

Ultimately, this library is meant to be a semi-experimental extension to Scala by means of the powerful metaprogramming facilities available in Scala.
