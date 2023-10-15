package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {

    val jahresZeit = listOf("Frühling", "Sommer", "Herbst", "Winter")
    println(jahresZeit.javaClass) // An immutable list is one of these "java.util.Arrays$ArrayList"

    // Kotlin new collection function
    println(jahresZeit.getOrNull(5)) // get 5th element in list or return null

    // Pairs created via zip function and drops items that cannot create the pair if list sizes are different lengths e.g [(red, Frühling), (brown, Sommer), (orange, Herbst)]
    val darkColoursList = listOf("red", "brown", "orange")
    println(darkColoursList.zip(jahresZeit))

    // Concatenate e.g flatten lists
    val combinedList = darkColoursList + jahresZeit
    println(combinedList)

    // Concatenate without duplicates
    val jahresZeitDupes = listOf("Frühling", "Sommer", "Herbst", "Winter", "Sommer")
    val noDupesList = darkColoursList.union(jahresZeitDupes)
    println(noDupesList)

    val emptyList = emptyList<String>()
    println(emptyList.javaClass)

    var nonNullList = listOfNotNull("hello", null, "goodbye")
    println(nonNullList)


    val mutableList = mutableListOf<Int>(1,2,3)
    mutableList[1] = 20
    println(mutableList)

    //To get a mutable list you need one of these e.g  java.util.ArrayList
    val arrayList = arrayListOf(1,2,4)
    println(arrayList.javaClass)

    // Prints the array ref
    val array = arrayOf("green", "white", "black")
    val colourList = listOf(array)
    println(colourList)

    // Unpacks the array items using the "spread" operator
    val arrayTwo= arrayOf("green", "white", "black")
    val colourListTwo = listOf(*arrayTwo)
    println(colourListTwo)

    val ints = intArrayOf(1,2,3)
    println(ints.toList())

    // We have to specify the type because we are NOT SUPPLYING any initial values
    val intsA = arrayOf(1,2,3,4,5)
    val add10List: MutableList<Int> = mutableListOf()
    for (i in intsA) {
        add10List.add(i + 10)
    }
    println(add10List)

    // Java8 style
    val add10Java8Style = intsA.map { it + 10 }
    println(add10Java8Style)

}