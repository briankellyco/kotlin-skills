package co.bk.kotlinskills.sandbox


fun main(args: Array<String>) {

    exampleAddElements()
    exampleOrderElements()
    exampleCaseInsenstiveComparison()
}


private fun exampleAddElements() {

    var baseTokens = setOf("IS_AUTHENTICATED")
    var extraTokensSet = setOf("Apple", "Orange", "Pear")
    extraTokensSet.also {
        if (it != null) {
            //baseTokens += it   // e.g enthält immer IS_AUTHENTICATED
            baseTokens = it  // replaces baseTokens with items in extraTokens e.g enthält nur Apple,Orange,Pear
        }
    }
    println("baseTokens size: ${baseTokens}")
}


private fun exampleOrderElements() {

    var items = setOf("Apple", "Orange", "Pear")

    val ordered : Set<String> = LinkedHashSet(items)

    val unordered : Set<String> = HashSet(items)

    println("ordered $ordered")
    println("unordered $unordered")
}

private fun exampleCaseInsenstiveComparison() {

    val set = setOf("apple", "banana", "orange")
    val searchString = "Apple"

    val containsCaseInsensitive = set.any { it.equals(searchString, ignoreCase = true) }

    if (containsCaseInsensitive) {
        println("The set contains the string \"$searchString\" in a case-insensitive way.")
    } else {
        println("The set does not contain the string \"$searchString\" in a case-insensitive way.")
    }

}

