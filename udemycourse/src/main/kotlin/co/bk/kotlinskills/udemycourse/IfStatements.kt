package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {

    // Ternary operator " exp ? option1 : option2" doesn't exist in kotlin because "if" statements can return values
    val someCondition = 69 < 22
    val num = if (someCondition) 50 else 592
}