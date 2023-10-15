package co.bk.kotlinskills.udemycourse

import java.io.File

fun main() {
    // The path "." refers to the current working directory of your program e.g intellij project location
    File(".").walkTopDown()
        .forEach { println(it) }


    // Walk operations return sequences which are suitable for chaining with other functions.
    File(".").walkTopDown()
        .filter { it.name.endsWith(".iml") }
        .forEach { println(it) }
}