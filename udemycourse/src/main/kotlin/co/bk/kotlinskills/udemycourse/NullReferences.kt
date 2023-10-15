package co.bk.kotlinskills.udemycourse


fun main(args: Array<String>) {

    //1. val str: String = null // Compiler reports "Null cannot be a value of a non-null type String"
    //2. val str: String? = null // Compiler message disappears as we said OK... this is a nullable type... by adding the "?"

/*  //3.
    val str: String? = "Heh heh my my"

    // Greyed out methods
    //str.toUpperCase() // Greyed out methods now in intellij (due to us supplying a nullable type) e.g toUpperCase()

    // Non-greyed out methods
    if (str != null) {
        str.toUpperCase() // Similar to smart casting the compiler remembers the null check
    }

    // Another way of writing the null check is... using a "safety operator/safe call operator" e.g "?."
    str?.toUpperCase() //a.k.a safe accessor. Rule use ? when calling methods if var definition used a ?

 */

/*
    //4.
    val str: String? = null
    println("what happens when we do this: ${str?.toUpperCase()}") // No null pointer in Kotlin... it just outputs "null"
*/

/*  //5.
    val str: String? = null
    println("what happens when we do this: ${str?.toUpperCase()}") // No null pointer in Kotlin... it just outputs "null"

    val str2 = str ?: "This is the default value" // The Elvis operator assigns a default value if variable was null
    println(str2) // outputs "This is the default value"
*/

    //6. Safe call operator combined with elvis allows us to return a value even if a chain of calls fails with a null somewhere
    //val whatever = bankBranch?.address?.country ?: "IE"

    //7. Safe cast operator "as?". Any time it is used.. all references to the variable thereafter must use the safe call operator e.g ?.
    val something: Any = arrayOf(1,2,3,4)
    val str3 = something as? String       // Compiler allows this... our val implicitly allows nullables
    //val str3: String = something as? String // Compiler does not allow this as our val explicitly does not allow nullables
    println(str3)
    println(str3?.toUpperCase())

}