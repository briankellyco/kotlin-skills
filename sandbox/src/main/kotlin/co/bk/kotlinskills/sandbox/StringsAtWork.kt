package co.bk.kotlinskills.sandbox

import okhttp3.internal.toImmutableList

fun main(args: Array<String>) {

    //csvCreateFromSet()
    //exampleStringRegex()
    //exampleStringProcessOnlyIfNotEmpty()
    exampleRegexComplex()
}

private fun exampleRegexComplex() {

    val listAllowedItems = listOf(
            "RealDriveName Release 1.1 (außer Kraft)",
            "hello world"
    )

    //val escapedRegex = Regex.escape("RealDriveName Release 1.1 (außer Kraft)")
    val stringToTest = "RealDriveName Release 1.1 (außer Kraft)"

    val isMatched = listAllowedItems.any { str ->
        //str.toRegex().matches(escapedRegex) // fail on complex string
        str.toRegex().matches(stringToTest) // // fail on complex string
        || str == stringToTest // pass on complex string
    }
    println(isMatched)

}

private fun exampleStringRegex() {

    val filenameSet: Set<String> = setOf("Apple", "Ball", "Cat", "all", "All", "ALL", "Dog")

    // equalsIgnoreCase with Kotlin
    val containsAllCaseInsensitive = filenameSet.any { it.equals("all", ignoreCase = true) }

    if (containsAllCaseInsensitive) {
        println("The set contains 'all' (case-insensitive)")
    } else {
        println("The set does not contain 'all' (case-insensitive)")
    }
}

private fun exampleStringProcessOnlyIfNotEmpty() {

    var keywordsFound = emptyArray<String>()
    val emptyText = "";

    // Problem... not empty array
    emptyText
            .split(",")
            .forEach { token ->
                keywordsFound += token.trim()
            }
    println("tokens detected: ${keywordsFound.size}")

    // Solution... array is empty
    keywordsFound = emptyArray<String>()
    emptyText
        .takeIf { it.isNotBlank() }
        ?.split(",")
        ?.forEach { token ->
            keywordsFound += token.trim()
        }
    println("tokens detected: using takeIf: ${keywordsFound.size}")
}

private fun csvCreateFromSet() {
    val mySet = setOf("apple", "banana", "orange", "grapes")
    val csv = mySet.joinToString(separator = ",")
    println ("csvCreateFromSet - output: ${csv}")
}

