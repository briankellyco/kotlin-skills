package co.bk.kotlinskills.udemycourse

import java.math.BigDecimal

fun main(args: Array<String>) {

    // NO "else" required when all enum options are supplied
    val timeOfYear = JahresZeit.FRUHLING
    val str = when(timeOfYear) {
        JahresZeit.FRUHLING -> println("tree buds bursting")
        JahresZeit.SOMMER -> println("beer on the ufer")
        JahresZeit.HERBST -> println("leaves a-falling")
        JahresZeit.WINTER -> println("glühwein am Markt")
    }
    println(str)

    val num = 200
    val y = 10

    when(num) {
        y + 90 -> println("100") // calculations can be tested
        100, 600 -> println("600") // 100 or 600 detected through use of CSV separated values
        in 200..298 -> println("200") // test a range and use the "in" keyword
        300 -> println("300")
        else -> println("doesn't match anything")
    }

    val obj1: Any = "Heh heh"
    val obj2: Any = BigDecimal(25.2)
    val obj3: Any = 44
    val something: Any = obj2

    val z = when(something) {
        is String -> {
            println(something.toUpperCase())
            4
        }
        is BigDecimal -> println(something.remainder(BigDecimal(10.5)))
        is Int -> {
            println(something - 11)
            3 // as with ifs.... the last value in a block will be the expression return value
        }
        else -> println("I have no idea what type this is...")
    }
    println(z) // returns UNIT.

    /**
     * Example: WHEN as a replacement for if-elseif-else
     *
     * Note how we do not have brackets to pass the value in here.
     * We just test a bunch of expressions inside the statement.
     */
    val numb = 20
    val numb2 = -50
    when {
        numb < numb2 -> println("num less than num2")
        numb > numb2 -> println("num greater than num2")
        else -> println("num equals num2")
    }

}

enum class JahresZeit {
    FRUHLING,
    SOMMER,
    HERBST,
    WINTER
}