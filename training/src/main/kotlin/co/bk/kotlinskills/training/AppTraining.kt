/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package co.bk.kotlinskills.training

fun main() {

    // Test cases for Animal.Dog
    val dog = Animal.Dog(breed = "Golden Retriever")
    println("Testing with Dog:")
    feedAnimal(dog)

    // Test cases for Animal.Cat (mouseHunter = false)
    val lazyCat = Animal.Cat(mouseHunter = false)
    println("\nTesting with lazy Cat (not a mouse hunter):")
    feedAnimal(lazyCat)

    // Test cases for Animal.Cat (mouseHunter = true)
    val hunterCat = Animal.Cat(mouseHunter = true)
    println("\nTesting with hunter Cat (mouse hunter):")
    feedAnimal(hunterCat)
}
