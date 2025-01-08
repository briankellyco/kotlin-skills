package co.bk.kotlinskills.training

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class AnimalTest {

    @Disabled // Disabled until Kotlin 2.1.0 features become easy to run in Intellij
    @Test
    fun `feedAnimal should call feedDog for Animal_Dog`() {
        // Given
        val dog = Animal.Dog(breed = "Golden Retriever")
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // When
        feedAnimal(dog)

        // Then
        val output = outputStream.toString().trim()
        assertEquals("", output) // No output expected; feedDog might be void
    }

    @Disabled
    @Test
    fun `feedAnimal should call feedCat for lazy Animal_Cat (not a mouse hunter)`() {
        // Given
        val lazyCat = Animal.Cat(mouseHunter = false)
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // When
        feedAnimal(lazyCat)

        // Then
        val output = outputStream.toString().trim()
        assertEquals("", output) // No output expected; feedCat might be void
    }

    @Disabled
    @Test
    fun `feedAnimal should print Unknown animal for hunter Animal_Cat`() {
        // Given
        val hunterCat = Animal.Cat(mouseHunter = true)
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // When
        feedAnimal(hunterCat)

        // Then
        val output = outputStream.toString().trim()
        assertEquals("Unknown animal", output)
    }
}
