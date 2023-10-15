package co.bk.kotlinskills.udemycourse

import java.math.BigDecimal

fun main(args: Array<String>) {

    val mixedList: List<Any> = listOf("heh heh", 1, BigDecimal(11), "winter")
    //val bigDecimalOnly = getElementsOfType<BigDecimal>(mixedList)
    val bigDecimalOnly = getElementsOfTypeReified<BigDecimal>(mixedList)

    for (item in bigDecimalOnly) {
        println(item)
    }
}

// Reified function example
inline fun <reified T> getElementsOfTypeReified(paramList: List<Any>): List<T> {

    var mutableList: MutableList<T> = mutableListOf()
    for (item in paramList) {
        if (item is T) {
            mutableList.add(item)
        }
    }
    return mutableList
}

/*
 * Non-reified function example. Reification allows us to get around the error "Cannot check for instance of erased type "T""
 */
//fun <T> getElementsOfType(paramList: List<Any>): List<T> {
//
//    var mutableList: MutableList<T> = mutableListOf()
//    for (item in paramList) {
//        if (item is T) {
//            mutableList.add(item)
//        }
//    }
//    return mutableList
//}

