package co.bk.kotlinskills.udemycourse

/*
 * Example 1

// Adding the "open" keyword allows us to extend this public and final class
open class Printer {
}

// Class is Public and final by default
class LaserPrinter: Printer {

    // Syntax to call the empty constructor that Kotlin generates for Printer class under-the-hood
    // alternatively add () brackets to all items and don't add this constructor
    constructor(): super()
}
*/

/*
 * Example 2
 */
fun main(args: Array<String>) {
    val laserPrinter = LaserPrinter("HP1234", 24)
    laserPrinter.printModel()
}
// Because its abstract we can remove "open" keyword when using with "abstract" classes (as they have to be extended by definition)
abstract class Printer(val modelName: String) {

    open fun printModel() = println("The model of this printer is $modelName")
    abstract fun bestSellingPrice(): Double
}

// opened for SpecialLaserPrinter
open class LaserPrinter(modelName: String, ppm: Int): Printer(modelName) {

    // Had to add "override" keywords to override the abstract method.
    // The "override" keyword implictly contains "open" (marking a function as open).
    // Adding a "final" keyword to a function stops any subclasses from overriding it.
    final override fun printModel() = println("The model of this laserPrinter is $modelName")
    override fun bestSellingPrice(): Double = 123.23

}

class SpecialLaserPrinter(modelName: String): LaserPrinter(modelName, 0) {

}

/**
 * Example 3: inheritance where parent has no default constructor e.g no brackets or class definition like "class Something(param: String)"
 *
 * Because it has no primary constructor the subclass can call the secondary constructor in the parent class e.g super(paramB)
 *
open class Something {

    val propertyA: String

    constructor(paramA: String) {
        propertyA = paramA
    }
}

class SomethingElse: Something {

    constructor(paramB: String): super(paramB)
}
 */

/*
 * Example 4: inheritance where parent has default constructor
 *
 * // And the compiler doesn't like this now because it hasn't been initialised!!
 * // val propertyA: String
 * // Rule is just use the secondary constructor without a primary constructor OR rely solely on the primary constructor otherwise the compiler; starts complaining.
 */
//open class Something(val x: Int) {
//
//    // And the compiler doesn't like this "propertyA" now because it hasn't been initialised!!
//    val propertyA: String
//
//    constructor(paramA: String, y: Int): this(y) {
//        propertyA = paramA
//    }
//}
//
//class SomethingElse: Something {
//
//    constructor(paramB: String, z: Int): super(z)
//}