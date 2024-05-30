# sandbox

Sandbox to play with Kotlin.

README notes consist of Udemy course plus experience/experimentation.

## Experience
``` 
1.  Overloaded methods:
        * are useful in Java to retrofit a changed method signature into a code base
          AND accomplish that without having to modify all classes (and tests) that use the current method signature.
    Kotlin allows us to solve this problem in a different way. Instead of:
        fun getDriveItem(driveId: String, itemId: String): DriveItem? {
          return getDriveItem(driveId, itemId, null)
        }
    just modify the existing signature:
        fun getDriveItem(driveId: String, itemId: String, 
            customizationExpandParameterMappingSet: Set<String>? = null): DriveItem? {}


```

## Multi-module Project Initialisation
```
1.  Install sdkman to manage tooling
        https://sdkman.io/
    Install latest gradle version and choose as default (as its init cmd worked)
        sdk list gradle
        sdk install gradle 8.5 
    Install kotlin
        sdk list kotlin
        sdk install kotlin 1.9.21
2.  Initialise a new project:
        https://docs.gradle.org/current/samples/sample_building_java_applications_multi_project.html
    where:
        mkdir kotlin-skills
        cd kotlin-skills
        gradle init         # follow the prompts and choose to create "(2) application".
    and:
        import that project into Intellij.
    and:
        refresh the gradle dependencies
    finally:
        run the tests from Intellij to confirm the build is working.
        run the tests from the command line as part of build e.g gradle clean build

3.  Intellij project should have a JDK21 setup to run the examples.

```

## Kotlin REPL (also known as event loop or interactive console)
```
1.  Open Console in Intellij with:
        Tools > Kotlin > Kotlin REPL
    Run code in the console:
        val power = 10.0
        Math.pow(2.0, power)
       
2.  REPL is aware of your project and can reference classes.
```


### Data Types and Null Reference Handling
```
1.  Kotlin doesn't require functions to be in actual class definitions e.g just a function defintion in a file is fine
        top-level-function-not-explicitly-in-class.jpg

2.  Runtime (of Kotlin) must be included with a distribution otherwise your application won't run.

3.  Kotlin imports lots of stuff by default to cut down on imports
        https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/
        
4.  Typed reference.... is how kotlin determines type at COMPILE TIME (not runtime)!

5.  Coding shortcut:
    * create a new file
    * type "main" and then return to generate function
    
6.  Immutability:
    * prefer val over var
    * underlined variables in Intellij indicate they are mutable
    * all variables/constants in Kotlin are by default PUBLIC and FINAL 
    
7.  Typealias:
    * reduce typing and are redirects to existing classes
    * typealias StringBuilder = java.lang.StringBuilder
    * custom example -> typealias EmployeeSet = Set<Employee> -> val employees : EmployeeSet
    
8.  Array accessors:
    * can use square brackets similar to javascript instead of getters
    * val names = arrayListOf("Jane", "Mary") -> println(names[0]) -> Jane
    
9.  Exceptions... no throw in Kotlin... and all exceptions are unchecked

10. Ternary operator is removed in kotlin:
    * q = x ? y : z //nope

11. Static keyword is removed. Concept still exists

12. No "new" keyword to create objects in Kotlin required:
        val obj = Employee("joe", 100)

13. Structural equality vs Referential equality
    * in java == checks if two objects point to the same reference
    * in kotlin == checks if two objects are structurally the same in terms of members data. Equivalent to calling ".equals()"
    * kotlin example
        kotlin-equals-signs-checks-for-structural-equality.jpg
    * in kotlin === checks if two objects point to the same reference (referential)
    Additional operators:
    * !== -> not equal referentially
    * != -> not equal structurally
    
14. Keywords and types
    * instanceOf is not available but instead use "is" keyword -> myObj is ObjectEmployee
    * cast with "as" keyword -> val myEmployee = something as Employee
    * kotlin has "smart casting" and can guess what objects are within code at times. Intellij or Kotlins compiler will give hints on what is possible. Enables less code once again.
    
15. String substitution by just embedding vars with a dollar sign in a string:
        override fun toString(var name: String, var id: Int) {
            return "Employee(name=$name, id=$id)"
        }
    To print a var name you need to escape it:
        println("show var name and value, \$name : $name")
    Expressions in strings:
        val numerator = 10.85
        val denominator = 20.00
        println("value of $numerator divided by $denominator is ${numerator/denominator}")
    String templates more:
        println("employee.id is ${employee.id}")

16. Inline function example:
        fun requiresCapacityBooking(fulfillmentDetails: FulfillmentDetails, shippingOptions: ShippingOptions?) : Boolean =
            fulfillmentDetails.fulfillmentType == FulfillmentType.DELIVERY && shippingOptions != null

17. Strings allow backslashes with triple quotes:
        var filePath = "c:\\some\\path"
    Triple string approach (also allows a string to be broken over several lines):
        var filePath = """c:\some\path"""
    Strings are optimised by JVM to same reference if the values are the same:
        val hello1 = "Hello"
        val hello2 = "Hello"
        println("hello1 is referentially equal to hello2: ${ hello1 === hello2 }") // true
    Triple strings indentation respected:
        val text = """*    1
                      *   11
                      *  111    
                      * 1111"""
        println(text.trimMargin("*"))  // trims up to the defined character; default is |.
            
18. Kotlin smart casting
        val text: Any = "The Any type is the root of the kotlin hierarchy"
        if (text is String) {
            println(text.toUpperCase())   
        }
    
19. Default Types
        val myInt = 5
        val myDouble = 10.3 // not float
    Array init via lambda:
        val evenNumbers = Array(16) { i -> i * 2 }
        for (number in evenNumbers) {
            println(number)
        }
        val allZeros = Array(100) { 0 }      // both approaches valid
        val allZeros = Array(100) { i -> 0 }
    Mixed array:
        val mixedArray = arrayOf("hello", 22, BigDecimal(10.4), 'a')
        println(mixedArray is Array<Any>)
    In Kotlin arrays are collections!!
    
20. Array performance boost is possible if passing a kotlin "primitive" array to a java class.
    Use one of the built-in methods instead of an array based on the Any datatype.
    For example:
        val anyArray = ArrayOf(1, 2, 3, 4)  // no
        val num = intArrayOf(1, 2, 3, 4) // yes
        val chars = charArrayOf('a', 'b', 'c') // yes
    Array init for Any not possible
        var intArray = Array<Int>(5)    // not allowed
    Array init for primities does support size init:
        var intArray = IntArray(5)      // allowed
    Convert Any array to Primitive array
        evenNumbers.toIntArray() // e.g [i] items
    Convert Primitive array to Typed array
        val convertedIntArray : Arrary<Int> = myPrimitiveIntArray.toTypedArray()
        val convertedIntArray = myPrimitiveIntArray.toTypedArray() // compiler inference will probably work too
        
21. Null checks in Kotlin. Lesson examples:
        NullReferences.kt
    Safety operator/safe call operator:
        str?.toUpperCase() //equivalent to if-else check for a null
        val str: String? = null
        println("what happens when we do this: ${str?.toUpperCase()}") // No null pointer in Kotlin... it just outputs "null"
    Elvis operator:
        val str2 = str ?: "This is the default value" // The Elvis operator assigns a default value if variable was null
        println(str2) // outputs "This is the default value"
    Safe cast operator "as?". Any time it is used.. all references to the variable thereafter must use the safe call operator e.g ?.
        val something: Any = arrayOf(1,2,3,4)
        val str3 = something as? String       // Compiler allows this... our val implicitly allows nullables
        //val str3: String = something as? String // Compiler does not allow this as our val explicitly does not allow nullables
        println(str3)
        println(str3?.toUpperCase())
    Non-null assertion with "!!" operator:
        You might want to use when you WANT to get a nullpointer as a fail-safe way to confirm that expected data has been set
        val str: String? = null
        val str2 = str!!.toUpperCase()  // basically says... if var = null throw a nullpointer
    How to think about nullable types? Well they are distinct types in their own right:
        val str: String? = null // this type is
        val str: String = null  // ... different from this type... and that can be seen clearly when you pass the nullable type "String?" to a method expecting a non-nullable "String" type
    BUT.... how do you pass that nullable type to a method expecting a non-nullable type? Make use of "let" (not designed for this purpose but is best choice): Let operator: Let keyword:
        val str: String? = "heh heh"
        str?.let { printText(it) }      // "it" refers to "str" and says ... let call to printText happen if str is not null :) The brackets btw is a lambda.
        fun printText(text: String) {
            println(text)
        }     
    And assign null to object if an input parameter is null? Use LET keyword to selectively assign the non-null value:
       class MyClass {
            var myDate: Date? = null // Declare the property with a default value of null
            
            fun assignInstantAsDate(instant: Instant?) {
                myDate = instant?.let { Date.from(it) }
            }
        }
    For more complex condition checks, such as checking 2-3 conditions... there is no way to use the safe call or elvis operator to do this. You just have to spell it out as per java in that situation:
        if (str1 != null && str2 != null) {...}
        
22. Array initialisation: Initialize with nulls:
        val nullableInts = arrayOfNulls<Int?>(5) // Use arrayOfNulls method as it tips off the compiler that it contains objects which might throw nullpointers
        for (i in nullableInts) {
            println(i) //prints 5 nulls
        }
        println(nullableInts[3].toString()) // compiler knows its null and lots of methods shaded out in intellij
     
        
23. Return types:
        Funky return object style. For an object OperatingHours with props nodeId, opening, closing.... this can be referenced as:
            // option #1
            val operatingHour = findNextOperatingHour(operatingHours, requestTime)
            // option #2
            val (nodeId, opening, closing) = findNextOperatingHour(operatingHours, requestTime)
        Void return types. Represented by "Unit" in Kotlin.
            https://notwoods.github.io/mockk-guidebook/docs/tips/unit/
        Void example #1 in test:
            every {
                availabilityService.reduceAvailability("myId", 12)
            } returns Unit
        Void example #2 in test:
            justRun {
                availabilityService.reduceAvailability("myId", 12)
            }
24. Visibility modifiers on variables are possible. For example:
        // file name: example.kt
        package foo

        private fun foo() { ... } // visible inside example.kt

        public var bar: Int = 5 // property is visible everywhere
        private set             // setter for "bar" is visible only in example.kt. Use indentation as shown
            
        internal val baz = 6    // visible inside the same module
     where "internal" visibility modifier is not available in java. Internal visibility means:
        * that the member is visible within the same module. 
        * More specifically, a module is a set of Kotlin files compiled together, for example:
            An IntelliJ IDEA module.
            A Maven project.
            A Gradle source set (with the exception that the test source set can access the internal declarations of main).
            A set of files compiled with one invocation of the <kotlinc> Ant task.
        * interal modifier can be applied to classes, methods and variables.
            
```

### OO and Kotlin: Classes and Functions
```
1.  Access modifiers
    * multiple private classes in the same file can access each other.... so long as they are in the same file
    * no requirement for class name to match file name
    * 'protected' cannot be used at the top level
    * top level functions are functions that are standalone in a kotlin file (not enclosed within a class)
    An 'internal' modifier means anything that is private to a multi-module module (in a multi-module maven or gradle project)
    Overview:
        access-modifiers-top-level.jpg
2.  All classes in kotlin by default are:
    * public
    * final
3.  Constructors:
    Long hand form:
        class Employee constructor(firstName: String) { // primary constructor
            val firstName: String
            
            init {
                this.firstName = firstName
            }        
        }
    Short hand approach
        private class Employee constructor(firstName: String) {         
            val firstName: String = firstName
        }
    Even shorter approach... put "val" in and we're done as Kotlin takes care of the assignment of vars under the hood:
        class Employee constructor(val firstName: String) {         
        }
    Most concise. But this would only work if NOT using annotations or access modifiers:
        class Employee(val firstName: String) {      <-- btw the brackets are saying this is a primary constructor   
        }        
    For example to explicitly control access with "protected" you have to use the "constructor" keyword:
        class Employee protected constructor(val firstName: String) {        
        }
4.  Constructor default values: a secondary constructor MUST call the primary:
        class Employe(val firstName: String) {        
            // NOTE!!!! no 'val' keyword should be used for secondary constructors....
            constructor(firstName: String, val fulltime: Boolean): this(firstName) {
            }
        }
    So... how do we declare the secondary constructor parameter:
        class Employe(val firstName: String) {        
            
            var fulltime: Boolean = true // we need to declare it in the class
            
            constructor(firstName: String, fulltime: Boolean): this(firstName) {
            }
        }
    More concise by using defaults
        class Employe(val firstName: String, var fulltime: Boolean = true) {        
        }
        
5.  Secondary constructors are possible without a Primary constructor:
        class Dummy {
            val dummy: String
            
            constructor() {
                dummy = "hello"
            }
        }
6.  Properties in kotlin are not fields. They are properties! ... and support dot notation when those props are public
        Employee.firstName = "Brian"
    Properties also support the same access modifiers (even in the primary constructor) e.g 
        private val fulltime: Boolean
    Private properties however are pointless unless you only want that property to be changed from inside the class.
7.  Properties in kotlin DO HAVE A BACKING FIELD.... when you want a custom access modifier on a property!!! (yes different from (6)).
    To do this we need to use the "field" identifier (and this is the only situation where we would use this identifier):
        class Employe(val firstName: String, fulltime: Boolean = true) {
        
            var fulltime = fulltime
            get() {
                println("Running the custom get")
                return field // refers to the backing field... and this getter MUST be immediately be written after the field declaration as shown
            }   
            set(value) {
                println("Running the custom set")
                field = value
            }     
        }
    The custom getter/setter are not called when you initialise the object via the primary constructor. Only via dot notation syntax.
8.  Constants. Top level constants can be defined outside of a class (e.g looks like it sits at the same level as the package declaration)
        val MY_CONSTANT = 100
9.  Data classes provide some functions for free:
        data class Car(val color: String, val model: String, val year: Int) {
        
        }
    which are:
        equals   // if (car1 == car2) returns true..... data class implementation will correctly check the structural equality; a normal class would
                 // return false unless we implemented (overrode) an equals method.
        hashCode
        copy     // creates an object structurally equal to previous method
        toString // particularly nice as it provides a formatted output of the object; a regular class would just print the object reference code
                 // val car = Car("volvo", 2015); println(car)
    Data classes cannot be abstract, sealed or inner classes. 
    Data classes should have all their properties in the primary constructor because the for free functions won't pick up 
    any properties defined directly in the data class.
    Data copy method changing one value:
         car.copy(year = 2015)
    Data classes are a good choice when you just want to store state.
    
10. Functions. Default return type is Unit. Type is optional and kotlin helps us:
        fun main(args: Array<String>): Unit {} // more verbose syntax. Unit indicates that "nothing" will be returned.
        fun main(args: Array<String>) {} 
    Compiler can deduce return types also:
        fun labelMultiply(operand1: Int, operand2: Int, label: String) = 
            "$label ${operand1 * operand2}"
    Functions with braces have what is called a "block body"
    Functions without braces are called an "expression body"
11. Default parameter values in functions:
        fun labelMultiply(operand1: Int, operand2: Int, label: String = "Your lucky number today is: ") = 
            "$label ${operand1 * operand2}"
    Type is ALWAYS required for function params e.g must specify String, Int, etc for the param
12. Parameter order can be customised by name:
        println(labelMultiply(label = "black ball number: ", operand2 = 2, operand1 = 4))
    This is known as "named arguments". A benefit is that you can label your parameters explicitly and document your code effectively.
13. Extension Functions
    * allow us to create the illusion that we are adding methods to other objects e.g String class
    * any public props can be referenced by the extension function
    * could be abused as devs might overruse them instead of creating separate objects
    Example extension function ... where the object that it is called on is the "this"
        fun String.upperFirstAndLast(): String {
            val upperFirst = this.substring(0,1).toUpperCase() + this.substring(1) //NOTE could remove the 'this' too and it will work fine
            return upperFirst.substring(0, upperFirst.length - 1) +
                    upperFirst.substring(upperFirst.length - 1, upperFirst.length).toUpperCase()
        }
        val s = "heh heh lowercase"
        println(s.upperFirstAndLast())
14. Inline functions:
    * not compiled to a separate function but just compiled to its body in the bytecode
    * not all functions can be inlined
    * just add the "inline" keyword first
    Example:
        inline fun labelMultiply(operand1: Int, operand2: Int, label: String = "Your lucky number today is: ") = 
            "$label ${operand1 * operand2}"
            
15. Annotating properties is possible with Kotlin. For example caching a getter call using Springs Cacheable annotation would be:
      @get:Cacheable("blocked-products-for-consumption")
      val blockedProductsForConsumption: List<ProductId>
        get() {
            val (_, value) = dynamicMongoPropertyRepository.findByNameAndSalesLine(
                DynamicPropertyNameEnum.BLOCKED_PRODUCTS_FOR_CONSUMPTION.getName(),
                "AA"
            )
                ?: return emptyList()
            val blockedProducts = value.trim { it <= ' ' }.split(";").toTypedArray()
            return Arrays.stream(blockedProducts).map { ProductId() }.collect(Collectors.toList())
        }
```

### OO and Kotlin: Inheritance
```
1.  All classes are public and final. To extend them we need to mark as either:
    * open
    * abstract (implicitly open)
    Example:
        ~/Documents/CODE/GITHUB/kotlin-skills/udemycourse/src/main/kotlin/co/bk/kotlinskills/udemycourse/Inheritance.kt
2.  Subclasses must call parent constructor. Either:
    1.  Add this to subclass:
            constructor(): super()
    2.  OR add () to all parts of the parent and subclass. See example 1 in 
            ~/Documents/CODE/GITHUB/kotlin-skills/udemycourse/src/main/kotlin/co/bk/kotlinskills/udemycourse/Inheritance.kt
3.  Override methods in parent classes by:
    1.  Add "open" or "abstracct" to function definition in parent
    2.  And "override" to function in child
    NOTE: override keyword also means open!!! So they can in theory continue to be overridden. It is possible to stop that by preprending "final" in front of the function that is using overridden
4.  Primary and secondary constructors. Generally speaking:
    * rule is just use the secondary constructor without a primary constructor OR rely solely on the primary constructor otherwise the compiler complains about different stuff.
    * examples 3 and 4 demo that in
        ~/Documents/CODE/GITHUB/kotlin-skills/udemycourse/src/main/kotlin/co/bk/kotlinskills/udemycourse/Inheritance.kt
    All constructors must use the primary constructor:
    * you can't call a super secondary constructor if you have a primary constructor (because every constructor must delegate to primary constructor)    
    * see Printer.kt example 3 for no primary constructor and subclass calling secondary in parent class e.g Something
    
5.  Data classes e.g those defined with "data" keyword cannot be extended e.g not compatible with "open" keyword
        data class MyDataClass(val number: Int) {
        }
        
6.  Interfaces example at:
        ~/Documents/CODE/GITHUB/kotlin-skills/udemycourse/src/main/kotlin/co/bk/kotlinskills/udemycourse/InterfaceExample.kt
    Interfaces can have properties and these don't need to be abstract and you can provide accessors for them:
        val number: Int
    However in interfaces "concrete" property init like this is not allowed:
        val number2: Int = 25
    Concrete properties are instead implemented via a "custom get" with stagged syntax below:
        val number2: Int
            get() = 5 * 10
    Interfaces also do not support the concept of "backing fields" (so differ from classes in that respect). For example this does not work:
        val number2: Int
            get() = field * 10
    So properties in interfaces do not have backing fields.

7.  Singletons in Kotlin: 
        Three use cases for the "object" keyword:
        1.  Singletons
        2.  Companion Objects
        3.  Object expressions
    Singletons in Kotlin always use the "object" keyword. 
    No constructors on this class as that wouldn't make sense for an object declared and created at the same time.
    Object created the first time that it is used.
            
8.  Companion Objects in Kotlin
        Kotlin doesn't have the "static" keyword meaning that you can't access class vars or call methods without first having an instance of the class.
    Placing code inside allows us to statically access:
        companion object {}
    Example:
        CompanionObjects.kt
    Named companions are possible with:
        companion object MyNamedCompanion {}
    which would then be accessed via:
        SomeClassWithCompanion.MyNamedCompanion.accessPrivateVar()

9.  Companion Objects can be used to call private constructors (you can use them to implement the Factory pattern).
    A conventional (non companion object) approach to having different constructors can be seen in:
        CompanionObjects02.kt
    So instead of modifying/adding constructors we can use our companion object to return instances of the class. In other words
    the companion object can act as a FACTORY. We need a primary constructor for this which is private (so that only the factory can instantiate it):
        CompanionObjects03.kt
    We make the constructor private with the keywords "private constructor":
        class FactoryClassMadeWithCompanion private constructor (val someString: String) {}
     
10. Anonymous objects in Kotlin. Anonymous objects are "inline" objects essentially.
    Are created by using the "object" keyword. Example at:
        AnonymousObjects.kt
    Objects created via this approach are not singletons. They are used and then discarded.
    Two styles can be used to implement the methods in the anonymous object:
    1. expression body style
    2. block body style
    Additionally Kotlin anonymous objects allow local vars to be accessed (not possible in java).
    
11. Enums in Kotlin
    * pretty much the same to java except declared with "enum class"
    * exception to the semi-colon rule when using with function in same class... see example :)
    
12. Imports in Kotlin
    * package name does not need to match directory structure! (though you are recommended to match them)
    Imports can be renamed to help distinguish between classes (basically aliases):
    * import co.bk.kotlintraining.MyClass as MyCommsClass
    
13. Internal access modifier
    * internal marks something as visible everywhere in the same module.
    Private keyword makes a function accessible only within the same file (applies to top-level functions too!).
    
14. Delegating up to primary constructor (extending a class):
        class MountainBikeKotlin(var seatHeight: Int, cadence: Int, speed: Int, gear: Int):
            BicycleKotlin(cadence, speed, gear) {
        }
    When delegating up DO NOT re-declare the properties e.g rely on the "var" declaration of vars in the supertype. Only declare
    properties once.
    For both classes and functions you need to "open" the super class/function definitions and "override" (at least for functions) in the sub-classes when extending.
    
15. Interfaces and null return types use this syntax:
        fun cancelExpiredReservations(): Void?
    where implementation will be:
        override fun cancelExpiredReservations(): Void? {
            // and logic.....ending with a return null
            return null
        }
        
```

### Kotlin logic statements
```
1.  The "for" loop is different in Kotlin. For loops in kotlin use ranges.
        val range = 1..5
        val charRange = 'a'..'z'
        val stringRange = "ABD".."XYZ" // any range can be used if it is comparable
    Check if value is within range:
        println(3 in range)
        println('q' in charRange)
        println("CCC" in stringRange)
        println("CCCCCC" in stringRange)
        println("ZZZZZZ" in stringRange)
        
        val backwardRange = 5.downTo(1)
        println(5 in backwardRange) // true
        
        val nopeRange = 5..1
        println(5 in nopeRange) // false
        
        // Step ranges can be defined for numeric and char datatypes
        val stepRange = 3..15
        val stepThree = stepRange.step(3)
        
2.  Arrays have indices that can be referenced:
        // Arrays have indices
        for (index in seasons.indices) {
            println("${seasons[index]} is season number $index")
        }
    Lambda approach:
        seasons.forEach { println(it) }
        seasons.forEachIndexed { index, value -> println("${value} is season number ${index}) } // method to access index
        
3.  Labels assigned to loops:
        // Labels applied to loops with break and continue
        for (i in 1..3) {
            println("i = ${i}")
            jloop@ for (j in 1..4) {
                println("j = ${j}")
                for (k in 5..10) {
                    println("k = ${k}")
                    if (k == 7) {
                        break@jloop;
                    }
                }
            }
    }
    
4.  IF statements. In Kotlin its worth noting that the Ternary operator " exp ? option1 : option2" doesn't exist.
    That is because "if" statements can evaluate to a value allowing us to return values. Java cannot do this.
    For example:
        val someCondition = 69 < 22
        val num = if (someCondition) 50 else 592
    For example following also works....where the only rule is that the return value is the last statement in each block
        val num2 = if (someCondition) {
            println("something")
            50
        } else {
            592
        }
        println(num2)
        
5.  WHEN expression. The java switch statement on steriods!!!
        val z = when(something) {
            is String -> {
                println(something.toUpperCase())
                4
            }
            is BigDecimal -> println(something.remainder(BigDecimal(10.5)))
            is Int -> {
                println(something - 11)
                3 // as with ifs.... the last value in a block will be the expression return value
            }
            else -> println("I have no idea what type this is...")
        }
        println(z) // returns UNIT.
    WHEN as an alternative for if-elseif-else
        /**
         * Example: WHEN as a replacement for if-elseif-else
         * 
         * Note how we do not have brackets to pass the value in here. 
         * We just test a bunch of expressions.
         */
        val numb = 20
        val numb2 = -50
        when {
            numb < numb2 -> println("num less than num2")
            numb > numb2 -> println("num greater than num2")
            else -> println("num equals num2")
        }

6.  Try-Catch Expressions:
    * kotlin doesn't distinguish between checked and unchecked
    Finally block will NEVER return a value (it can run code but doesn't return values to the expression).
    * elvis operator can be used with try-catch to check the return type of a function. See TryCatchExpression.kt
   
7.  Nothing type explicitly indicates a function will return .... well nothing always (for example an exception or an infinite loop)
    Example see TryCatchExpression.kt
```

### Kotlin lambda expressions
```
1.  Lambdas can be moved outside the parantheses of a method call IF the lambda is the last parameter. 
    * see LamdaExpressions.kt and persons.minBy {....} 
    
2.  Java ONLY allows access to final variables inside lambdas.
    Kotlin DOES allow access to mutable variables inside lambdas... so no warning about trying to access local var that is not final!!
        var num = 10
        run {
            num += 15
            println(num)
        }
        
3.  Kotlin supports member references:
        println(persons.minBy(HistoricalPerson::startYear)) // Known as a "member reference"
    Another form of that is:
        run(::topLevel) // Member reference to top level function
        
4.  Scope functions execute a block of code on an object. Five of them:
        "With" 
        "let"
        "run"
        "apply"
        "also" 
    For example:
        https://medium.com/@fatihcoskun/kotlin-scoping-functions-apply-vs-with-let-also-run-816e4efb75f5
        https://kotlinlang.org/docs/scope-functions.html
    where each scope function takes two params:
        a receiver
        a block of code that executes on the receiver

5.  WITH scope function:
        val person: Person = getPerson()
        with(person) {
            print(name)
            print(age)
        }
    instead of:
        val person: Person = getPerson()
        print(person.name)
        print(person.age)
    It removes repetition of the person variable.
    The with() function returns what is returned by executing its block argument
    
6.  ALSO scope function. The also() function returns the same object that was provided as its receiver. Use the also() function, if your block does not access 
    its receiver parameter at all, or if it does not mutate its receiver parameter. 
        val person: Person = getPerson().also {
            print(it.name)
            print(it.age)
        }
        
7.  All function defintions:
        inline fun <T, R> with(receiver: T, block: T.() -> R): R {
            return receiver.block()
        }
        inline fun <T> T.also(block: (T) -> Unit): T {
            block(this)
            return this
        }
        inline fun <T> T.apply(block: T.() -> Unit): T {
            block()
            return this
        }
        inline fun <T, R> T.let(block: (T) -> R): R {
            return block(this)
        }
        inline fun <T, R> T.run(block: T.() -> R): R {
            return block()
        }

8.  APPLY function. Use the apply() function if you are not accessing any functions of the receiver within your block, and also want to return the same receiver. 
    This is most often the case when initializing a new object.
        val peter = Person().apply {
            // only access properties in apply block!
            name = "Peter"
            age = 18
        }
        
9.  Lambdas in Kotlin have a characteristic where if they are written in-lined they can return for both the lambda AND the function that the lambda  was called within. For example:
        LambdaExpressions.findByLastNameExhibitGlobalReturn()
    To avoid this behaviour and return only from the lambda (also known as a "local return") we need to use a label. For example:
        LambdaExpressions.findByLastNameExhibitLocalReturn()

10. Labels can be used in nested lambdas also to reference different receiver objects. For example:
        LambdaExpressions.nestedLambdaLabels()
         
```

### Kotlin collections
```
1.  Kotlin enforce non-mutability on most collection objects. Java doesn't do this and if you pass a Kotlin collection to java the immuatibility is not
    respected by java.

2.  All read-only interfaces are co-variant.... that means you can assign a list of BigDecimal to a list of Any. Meaning when a class is co-variant
    you can treat it like one of its parent classes.
    Co-variance can be recognised by the "out" keyword. For example:
        public interface Collection<out E>: Iterable<E> {}
        
3.  Kotlin added collection methods:
        list.getOrNull() // returns null if item in collection/array does not exist. Avoids having to do a size check on a collection.
    Identify max in collection:
        val ints = listOf(1,2,3,4,5)
        println(ints.max()) // prints 5
    Pairs can be created by combining two lists using the collection zip function:
        // Pairs created via zip function and drops items that cannot create the pair if list sizes are different lengths 
        // e.g [(red, Frühling), (brown, Sommer), (orange, Herbst)]
        val jahresZeit = listOf("Frühling", "Sommer", "Herbst", "Winter")
        val darkColoursList = listOf("red", "brown", "orange")
        println(darkColoursList.zip(jahresZeit))  
    Concatenate lists together (basically flatten the lists into a single list with "+")
        val combinedList = darkColoursList + jahresZeit // [red, brown, orange, Frühling, Sommer, Herbst, Winter]
    Avoid duplicates in lists by using "union":
        val jahresZeitDupes = listOf("Frühling", "Sommer", "Herbst", "Winter", "Sommer")
        val noDupesList = darkColoursList.union(jahresZeitDupes)
        println(noDupesList) // [red, brown, orange, Frühling, Sommer, Herbst, Winter]
    Avoid duplicates also with "distinct":
        val jahresZeitDupes = listOf("Frühling", "Sommer", "Herbst", "Winter", "Sommer")
        val noDupeSeasons = jahresZeitDupes.distinct()
        println(noDupeSeasons)
    Mutability of a list can be changed simply with:
        val mutableSeasons = jahresZeitDupes.toMutableList()
        
4.  Maps in Kotlin are actually Pairs and can be created using the "Pair" keyword or implicitly via "to" keyword:
        
        // val immutableMap = mapOf<Int, Car>() // NOTE: the generics for the mapOf() are totally optional but can be added for clarity
        val immutableMap = mapOf(
            1 to Car("green", "peugeot", 2001),
            2 to Car("blue", "ford", 2011),
            3 to Car("red", "tesla", 2021),
        )
    Example:
        val claims = mapOf(
            "user_name" to "a.d"
        )
    Mutable maps created with:
        mutableMapOf()
    Both mapOf() and mutableMapOf() output "java.util.LinkedHashMap" which Kotlin likes because the order is predictable and allows easier conversion 
    between sets/lists/maps.
    
5.  HashMap instead of LinkedHashMap... ask for one. HashMap's are always mutable.
        val mutableMap = hashMapOf(
            1 to Car("green", "peugeot", 2001),
            2 to Car("blue", "ford", 2011),
            3 to Car("red", "tesla", 2021),
        )

6.  Destructure maps which meaning access component parts of them.
        val pair = Pair(10, "ten")
        val (firstValue, secondValue) = pair
        println(firstValue)
        println(secondValue)
    Use destructuring declaration intellij hint means do something like:
        for ((k, v) in mutableMap) {
            println(k)   // instead of item.key and item.value
            println(v)
        }
    Destructuring ability does not come out of the box. You can to implement "component functions". 
        For example this won't work:
            class Car(val colour: String, val model: String, val year: Int) {}
            val car = Car("blue","mercedes",1965)
            val (colour,model,year) = car // FAILS with message about needing to implement component functions
        Will work:
            class Car(val colour: String, val model: String, val year: Int) {
                operator fun component1() = colour
                operator fun component2() = model
                operator fun component3() = year
            }        
            val car = Car("blue","mercedes",1965)
            val (colour,model,year) = car 
            println("colour=$colour, model=$model, year=$year)
    Data classes are given the ability to do destructuring in addition to the toString and equals methods. Data classes get them for free e.g
        data class Car(val colour: String, val model: String, val year: Int) {}
        val car = Car("blue","mercedes",1965)
        val (colour,model,year) = car 
        println("colour=$colour, model=$model, year=$year)
        
7.  Mutability via collection methods will always depend on whether the function is part of the immutable or mutable interface.
        // Adding/removing elements does NOT change the actual set e.g plus/minus
        // This is because the plus and minus functions are part of the immutable interface. Check the interfaces to understand expectations
        val mutableInts = mutableSetOf(1,2,3,4,5)
        mutableInts.plus(10)
        println(mutableInts) // [1, 2, 3, 4, 5]
    Mutable lists and sets will need a type to be specified if initial values are not provided. For example:
        // We have to specify the type because we are NOT SUPPLYING any initial values
        val intsA = arrayOf(1,2,3,4,5)
        val add10List: MutableList<Int> = mutableListOf()
        for (i in intsA) {
            add10List.add(i + 10)
        }
        println(add10List)
    Or java8 style:
        val add10Java8Style = intsA.map { it + 10 }
        
        
8.  Filter function does not operate on the collection directly but returns a new map.


```

### Kotlin Files & Resources
``` 
1.  Kotlin doesn't have its own IO classes and so you need to leverage the Java classes.
    Access files using package:
        kotlin.io.path.Path 
    and NOT:
        java.nio.file.Path
    example:
        var path = Paths.get("").toAbsolutePath().toString() // returns intellij working directory
        
2.  Kotlin "reader" extension:
        File(path).reader().forEachLine { println(it) }
    is equivalent to
        new InputStreamReader(new FileInputStreamReader(new File("/testfile.txt")), "UTF-8")

3.  Auto closing reader connections is possible via use of the "use" keyword. Example:
        val lines02 = File(path).reader().use { it.readText() }
        println(lines02)
    instead of:
        val reader = File(path).reader()
        val lines = reader.readText()
        println(lines)
        reader.close()
    example:
        FileIO.kt
        
4.  Java try-with-resources construct is not available in Kotlin. In java it ensures resources are closed after use.
        try (FileReader fr = FileReader(path)) {
             // use the resource here...
        } // Java closes the resource here automatically
    Kotlin instread offers the "use" keyword to do the same thing. Example:
        FileReader(path).use { reader ->
            val bufferedReader = reader.buffered()
            bufferedReader.forEachLine { line ->
                println(line)
            }
        } // The 'reader' will be automatically closed when this block exits
    Example:
        BinaryIO.kt
        
        
5.  Walk functions return sequences. With sequences you can do chaining quite a bit:
        File(".").walkTopDown()
            .filter { it.name.endsWith(".iml") }
            .forEach { println(it) } 
    Example:
        WalkFieTree.kt
        
            
        
        
```

### Kotlin Generics
```
1.  Reified parameters in Kotlin:
    * inline function parameters in Kotlin can be "reified"
    * reification is a Kotlin feature that prevents the type from being erased at runtime
    * to use reification you must 1) inline the parameter 2) and reify the type parameter
    * SEE ~/Documents/CODE/GITHUB/kotlin-skills/udemycourse/docs/reification-can-solve-error-on-T.jpg
2.  Reification in practice means changing:
        fun <T> getElementsOfType(paramList: List<Any>): List<T> {...}
    to:
        inline fun <reified T> getElementsOfTypeReified(paramList: List<Any>): List<T> {...}
    see:
        Generics.kt
3.  Reification is ONLY needed if you wanted to check the type inside a function.
    Code that cannot be reified is:
    * classes, properties or non-inline functions
4.  Covariance:
    * can be used to preserve sub-typing
    * when you have a covariant object the sub-typing is preserved
    * Covariance is a type property which is not supported by Java
    * a MutableList is NOT covariant (and so sub-typing is not preserved)
    * a non-mutable list is covaraint (and sub-typing is preserved) e.g covariance ONLY works for immutable collections
    Example not in java:
    * SEE ~/Documents/CODE/GITHUB/kotlin-skills/udemycourse/docs/covariance-not-supported-in-java.jpg
    * SEE ~/Documents/CODE/GITHUB/kotlin-skills/udemycourse/docs/covariance-supported-in-kotlin.jpg
    * SEE ~/Documents/CODE/GITHUB/kotlin-skills/udemycourse/docs/covariance-supported-in-kotlin-02.jpg

5.  Covariance can be added. For example consider:
        // Known as "invariant" as it ONLY wants a class of Flower and not Rose. The implication of this invariance is that if you try
        // to "waterGarden" only  Flowers will be acceptedfun plantFlower(flower: T) { currently (and not Roses)
        class Garden<T: Flower> {
        }
    when:
        fun waterGarden(garden: Garden<Flower>) {
        }
    How do you make the waterGarden function accept a Garden<Rose> object? Well simply add "out" keyword e.g
        class Garden<out T: Flower> {
        }
    Cost accompanies the use of "out" keyword:
    * you can ONLY use a covariant class in the "out" position
    * a function return type is an OUT position
    * a function parameter is an IN position
    * for example adding the out suddently means you can't plant flowers (reports using out parameter in in position!!!):
            fun plantFlower(flower: T) {...}
    * the OUT position restriction is important because it stops invalid types from being added to the collection!!
    * the restriction (supplying an OUT param to a funciton as a param for example) can be worked around through use of an annotation
    but should ONLY be used when you are absolutely sure it won't lead to a runtime exception e.g won't change the list
    NOTE the following too:
    * Constructor parameters do not have IN and OUT parameters and so constructors will always accept covariant classes. Thats because
    constructors are only called when objects are created.
5.  Contravariance:
    * covariance preserves the order (and looks DOWN the inheritance tree); contravariance on the other hand INVERSES the order.
    * is the opposite of covariance (we are saying we want to accept a subclass and all of its super types e.g looks UP the inheritance tree)
    * enable contravariance with the IN keyword (we say we will accept any type that has X IN its inheritance tree e.g ROSE)
    Usage example:
    * enabling contravariance would allow us to make some methods more generic e.g flowerTender method instead of custom methods per object type roseTender, daffodilTender
    * SEE ~/Documents/CODE/GITHUB/kotlin-skills/udemycourse/src/main/kotlin/co/bk/kotlinskills/udemycourse/contravariance/Contravariance.kt
6.  Declaration-site variance:
    * means the use of IN and OUT keywords
    * you don't have "declaration site variance" in java (it only has use-site variance)
    * delcaration-site refers to on an instance or on a class
7.  Use-site variance:
    * basically means we don't change the classes or instances but instead modify our function to allow the types that we need to be passed
    through use of the IN or OUT keyword.
    * the covariant equivalent in java is this wildcard approach "List<? extends Car"
    * the contravariant equivalent in java is this approach "List<? super Car>"
    * java does not have declaration-site variance
    For example:
        fun <T: Car> copyCar(source: MutableList<out T>, destination: MutableList<T>) {
            for (car in source) {
                destination.add(car)
            }

        }
```

### Kotlin Logging
```
1.  SL4F is an acceptable option but there is a better option:
        <dependency>
          <groupId>io.github.microutils</groupId>
          <artifactId>kotlin-logging</artifactId>
          <version>1.3.2</version>
        </dependency>
2.  Pros and cons described:
        https://ohadshai.medium.com/logging-in-kotlin-95a4e76388f2
    Namely the benefits over the original slf4j implementation:
        * Getting the logger without the class name itself (excellent for copy paste)
        * Much cleaner messages with String templates and lazy evaluation
3.  Usage:
        1.  Add following just below import statements in file:
                private val logger = KotlinLogging.logger {}
        2.  Use it via:
                logger.info("hello world")
            or lazily:
                logger.debug{"lazy evaluated $message"} //means ONLY gets evaluated if "debug" level is enabled at runtime
                logger.error { "could not retrieve ip country for $requestURL due to $exception" }
```

### Kotlin Interoperability with Java
``` 
1.  Learning Point - nullability
        When you are calling Java from a Kotlin file, the kotlin compiler is supposed to automatically compile that Java file.
        The Kotlin compiler sees the @NotNull and @Nullable annotations uses them as a hint to map to a non-null type and 
        a nullable type respectively.
        
        Nullability is Kotlins built-in protection against nulls. Java does not have this capability.
        
    Example:
        @Nullable and @NotNull – indicate a variable, parameter, or return value that can or cannot be null. Both serve as documentation only.
        @Nullable String name = null; // OK
        @NotNull String name = null; // Error
    where:
        import org.jetbrains.annotations.Nullable;
        import org.jetbrains.annotations.NotNull;
    and Kotlin will also pay attention to:
        javax and android nullable annotations
        
2.  Learning Point - spread operators
        A spread operator "*" is needed to unpack arrays if passing them as arguments to a method in a Java class.
        Or in other words a Java method accepting a variable number of parameters cannot be passed an array directly, but the array must be unpacked.
 
3.  Learning Point - void methods return Unit
         Kotlin does not have the concept of void methods which means if you call a Java method that returns void, it will return Unit.
         
4.  Primitive array types require a specific type and not a general type. For example a Java method:
        public void wantsIntArray(int[] theArray) {
            for (int i: theArray) {
                System.out.println("Here's the int: " + i);
            }
        }
    and Kotlin interacts with it as follows:
        // arrayOf fails as it needs a specific type. Could fix with arrayOf(1,2,3).toIntArray()
        //carTwo.wantsIntArray(arrayOf(1, 2, 3)) // fails expected.
        carTwo.wantsIntArray(intArrayOf(1, 2, 3)) // works
        
5.  Hierarchies of classes are different in Java and Kotlin
      In Java, all classes inherit from Object.
      In Kotlin, all classes inherit from Any.
    So if you want to call a method on an object that is not in the Any class, you need to cast it.
    where:
        Without casting code completion will show that we only have equals(), hashCode() and toString() methods available.
        By casting we gain access to the java specific methods and properties.
    Example:
        InteroperabilityWithJava.kt
    where:
        (carTwo.anObject as java.lang.Object).notify()  // able to access this base method now
        
6.  Java static methods:
    * making Java static vars public is good enough to access them using the Kotlin dot notation e.g Car.carCount
    * Kotlin does not have static variables
    and access to these static variables is the same way as accessing "companion objects". Namely:
        println("x = ${CarTwo.carCount}")

7.  SAM conversions. Single Abstract Method.
    * when a java interface ONLY has a SAM then you can use a "lambda expression" rather than an "anonymous instance" .... when you call a method
    that wants an instance of the interface
    For example:
    1.  Runnable is a SAM (Single Abstract Method) interface as it has ONLY one method e.g run()
    2.  SAM... instance created via anonymous instance... old approach
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("I'm in a thread!");
                }
            }).start();
    3. SAM.... instance created via lambda expression... newer approach since Java 8
            new Thread( () -> System.out.println("I'm in a thread!") ).start();

8.  SAM conversion for Kotlin means that Kotlin can use the lambda expression approach. The only rule the Kotlin code needs to 
    follow is that the lambda expression must MATCH the expectation of the Java interface method. In practice that means matching
    the method signature. 
    For example:
        * Runnable.run() has no parameters and returns void. So the lambda expression must also have no parameters and return void.
        * carTwo.demoMethod( { println("I'm in a thread!") })  // is a lambda expression that passes no parameters and returns void
        * see InteroperabilityWithJava.kt for the full demoMethod() example

```




