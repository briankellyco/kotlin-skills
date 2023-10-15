package co.bk.kotlinskills.udemycourse

import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun main(args: Array<String>) {

    //println(getNumber("22"))
    println(getNumber("22.5"))

    // Demos using elvis operator to check for null and then throw a type of exception
    println(getNumberThatReturnsNull("22.5") ?: throw IllegalArgumentException("Number isn't an Int"))

    // Demos function returning nothing
    notImplementedYet("test")
}

fun getNumber(str: String): Int {
    // Just like other bracketed statements (if, when, etc) the last line in a block is the value that is returned
    return try {
        Integer.parseInt(str)
    } catch(nfe: NumberFormatException) {
        0
    } finally {
        println("in finally block")
        //1 uncomment this.... and it will not return this value as finally doesn't return values. Just runs code.
    }
}

fun getNumberThatReturnsNull(str: String): Int? {
    return try {
        Integer.parseInt(str)
    } catch(nfe: NumberFormatException) {
        null
    } finally {
        println("in finally block")
    }
}

fun notImplementedYet(str: String): Nothing {
    throw IllegalArgumentException("implement me!")
}