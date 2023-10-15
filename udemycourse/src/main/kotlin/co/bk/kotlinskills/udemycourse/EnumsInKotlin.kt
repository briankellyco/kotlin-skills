package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {
    println(Department.ACC.getDeptInfo())
}
//Declared in kotlin with "enum class"
enum class Department(val fullName: String, val numStaff: Int) {
    HR("Human REsources", 2),
    IT("Information Technology", 10),
    ACC("Accounting", 3),
    SALES("Sales", 4); // exception to no semi-colon rule.... is required in enum THAT possesses a function

    fun getDeptInfo() = "The dept $fullName has $numStaff staff"
}

fun topLevel(str: String) = println("Top level function: $str")