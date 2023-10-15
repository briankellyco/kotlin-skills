package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {

    // NOTE: the generics for the mapOf() are totally optional but can be added for clarity
    // val immutableMap = mapOf<Int, Car>(
    val immutableMap = mapOf(
        1 to Car("green", "peugeot", 2001),
        2 to Car("blue", "ford", 2011),
        3 to Car("red", "tesla", 2021),
    )
    println(immutableMap.javaClass)
    println(immutableMap)

    val mutableMap = mutableMapOf(
        1 to Car("purple", "peugeot", 2001),
        2 to Car("silver", "ford", 2011),
        3 to Car("yellow", "tesla", 2021),
    )

    // Add element to set
    val setInts = setOf(5,3, 10,15,-32)
    println(setInts.plus(33))
    println(setInts.plus(10))
    println(setInts.minus(15))

    // Filter a set. It DOES NOT operate on the collection directly and will return a new map
    println(setInts.filter { it % 2 != 0 })
    println(immutableMap.filter { it.value.year == 2021 })
    println(mutableMap.filter { it.value.colour == "silver" })
    println("the filters map is $mutableMap")

    // Math on the set... average
    println(setInts.average())
    println(setInts.drop(3)) // drops first three elements from the set


    // Adding/removing elements does NOT change the actual set e.g plus/minus
    // This is because the plus and minus functions are part of the immutable interface. Check the interfaces to understand expectations
    val mutableInts = mutableSetOf(1,2,3,4,5)
    mutableInts.plus(10)
    println(mutableInts)
}

class Car(val colour: String, val model: String, val year: Int) {

}