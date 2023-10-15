package co.bk.kotlinskills.udemycourse


// Top class constants defined outside of a class
val MY_CONSTANT = 100

fun main(args: Array<String>) {

    val employee = Employee("Peter")
    println(employee)
}

// Visible to functions in this file. Long hand approach
private class Employee constructor(firstName: String) { // primary constructor
    val firstName: String

    init {
        this.firstName = firstName
    }
}

//// Short hand approach
//private class Employee constructor(firstName: String) {
//
//    val firstName: String = firstName
//}

// Even shorter
//private class Employee (val firstName: String) {
//}



