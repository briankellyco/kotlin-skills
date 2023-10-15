package co.bk.kotlinskills.udemycourse

import co.bk.kotlinskills.interoperability.Car
import co.bk.kotlinskills.interoperability.CarTwo

/*
 * Learning Point - nullability
 *   When you are calling Java from a Kotlin file, the kotlin compiler is supposed to automatically compile that Java file.
 *   The Kotlin compiler sees the @NotNull and @Nullable annotations uses them to map to a non-null type and a nullable type respectively.
 * Example:
 *   @Nullable and @NotNull – indicate a variable, parameter, or return value that can or cannot be null.
 *   @Nullable String name = null; // OK
 *   @NotNull String name = null; // Error
 * where:
 *   import org.jetbrains.annotations.Nullable;
 *   import org.jetbrains.annotations.NotNull;
 *
 * Learning Point - compiling the code
 *   In my experience I needed to place the java class under /src/main/java for the compiler to pick it up using the gradle config
 *   as it came out of the box.
 *
 *   The alternative would probably be to modify the build.gradle.kts file to include the java code in the list of source sets.
 *
 * Learning Point - spread operators
 *   A spread operator "*" is needed to unpack arrays if passing them as arguments to a method in a Java class.
 *   Or in other words a Java method accepting a variable number of parameters cannot be passed an array directly, but the array must be unpacked.
 *
 * Learning Point - void methods return Unit
 *   Kotlin does not have the concept of void methods which means if you call a Java method that returns void, it will return Unit.
 *
 *
 */
fun main(args: Array<String>) {

    val car = Car("blue", "Ford", 2015)
    car.color = "green"
    println(car)

    /* Uncomment this to see the compile error "Type mismatch" and the nullability in action.
        var model: String = car.model
        println(model.javaClass)
        model = null
        println(model)
    */

    val carTwo = CarTwo("blue", "Ford", 2015)
    carTwo.color = "green"
    println(carTwo)

    // Array of strings is already unpacked.... so it works
    carTwo.variableMethod(5, "hello", "goodbye")
    val strings = arrayOf("hello", "goodbye")

    // Array of strings is NOT unpacked.... so it fails
    //carTwo.variableMethod(10, strings)

    // Array of strings IS unpacked with the "*" spread operator.... and it works
    carTwo.variableMethod(10, *strings)

    /*
     * Primitive array types require a specific type and not a general type
     */
    // arrayOf fails as it needs a specific type. Could fix with arrayOf(1,2,3).toIntArray()
    //carTwo.wantsIntArray(arrayOf(1, 2, 3)) // fails expected.
    carTwo.wantsIntArray(intArrayOf(1, 2, 3)) // works

    /*
     * Hierarchies of classes are different in Java and Kotlin
     *  In Java, all classes inherit from Object.
     *  In Kotlin, all classes inherit from Any.
     * So if you want to call a method on an object that is not in the Any class, you need to cast it.
     *
     * For example:
     *   Without casting code completion will show that we only have equals(), hashCode() and toString() methods available.
     *   By casting we gain access to the java specific methods and properties.
     *
     *
     */
    // (carTwo.anObject as java.lang.Object).notify()  // able to access this base method now


    println("x = ${CarTwo.carCount}")
    println(CarTwo.carCountString())

    carTwo.demoMethod( { println("I'm in a thread!") })

}