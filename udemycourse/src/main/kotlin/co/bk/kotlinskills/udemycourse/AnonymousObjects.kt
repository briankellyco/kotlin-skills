package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {

    // Create an anonymous object using the "object" keyword

    /*
     * Expression body example
     */
    wantsSomeInterface(object: AnonymousObjectsInterface {
        // we want an expression body here not a block body e.g inline declaration
        override fun mustImplement(num: Int) =
            "this is from mustImplement: ${num * 10}"
    })


//    /*
//     * Block body example
//     */
//    var thisIsMutable = 45
//    wantsSomeInterface(object: AnonymousObjectsInterface {
//        // block body style with brackets and return
//        override fun mustImplement(num: Int): String {
//            thisIsMutable++ // var accessible from anonymous object.... not possible in java
//            return "this is from mustImplement: ${num * 10}"
//      }
//    })
}

interface AnonymousObjectsInterface {
    fun mustImplement(num: Int): String
}

fun wantsSomeInterface(si: AnonymousObjectsInterface) {
    println("printing from wantsSomeInterface ${si.mustImplement(22)}")
}