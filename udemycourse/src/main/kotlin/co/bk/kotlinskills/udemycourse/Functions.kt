package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {
    //println(labelMultiply(2,4, "black ball number: ")) // normal

    println(labelMultiply(label = "black ball number: ", operand2 = 2, operand1 = 4)) // params ordered in custom fashion

    val s = "this is all lower case"
    print(s.upperFirstAndLast()) // Intellij detects the extension function!!!
}

// Compiler understands what type will be returned so no need to define it.
fun labelMultiply(operand1: Int, operand2: Int, label: String) =
    "$label ${operand1 * operand2}"

// Example extension function ... where the object that it is called on is the "this"
fun String.upperFirstAndLast(): String {
    val upperFirst = this.substring(0,1).toUpperCase() + this.substring(1)
    return upperFirst.substring(0, upperFirst.length - 1) +
            upperFirst.substring(upperFirst.length - 1, upperFirst.length).toUpperCase()
}