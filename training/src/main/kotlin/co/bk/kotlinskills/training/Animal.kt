package co.bk.kotlinskills.training

/*
 * About Kotlin 2.1.0 features
 *    https://kotlinlang.org/docs/whatsnew21.html#guard-conditions-in-when-with-a-subject
 */
sealed interface Animal {
    data class Cat(val mouseHunter: Boolean) : Animal {
        fun feedCat() {}
    }

    data class Dog(val breed: String) : Animal {
        fun feedDog() {}
    }
}

fun feedAnimal(animal: Animal) {
    when (animal) {
        // Branch with only the primary condition. Returns `feedDog()` when `Animal` is `Dog`
        is Animal.Dog -> animal.feedDog()
        // Branch with both primary and guard conditions. Returns `feedCat()` when `Animal` is `Cat` and is not `mouseHunter`
        // TODO uncomment once using Kotlin 2.1.0
        //is Animal.Cat if !animal.mouseHunter -> animal.feedCat()
        // Returns "Unknown animal" if none of the above conditions match
        else -> println("Unknown animal")
    }
}