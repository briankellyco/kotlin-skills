package co.bk.kotlinskills.sandbox


fun main() {
    val unit = printAndReturn()
    println("After function call") // This line will be executed after the function call
}

fun printAndReturn() {
    println("Before return")
    return
    println("After return") // This code is unreachable
}


//class LanguageNuances {
//
//
//    // TODO chain API calls if result is not null
//    fun myMethod(client: ApiClient) {
//        client.makeApiCall()?.let { result ->
//            // The first API call was successful and result is not null
//            // You can perform operations with 'result' here
//            client.makeSecondApiCall(result) // Second API call with the result of the first call
//        }
//    }
//
//
//}

