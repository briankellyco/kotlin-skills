package co.bk.kotlinskills.udemycourse

import java.lang.StringBuilder

fun main(args: Array<String>) {

    // The "run" command allows you to run lambdas
    run {
        println("I'm in a lambda!")
    }

    val persons = listOf(
        HistoricalPerson("Brian", "Boru", 1014),
        HistoricalPerson("King", "Harold", 1066),
        HistoricalPerson("Sigtrygg", "Silkbeard", 1042),
    )

    // Rule is you can move a lambda outside the paranthese e.g (), if it is the last parameter in a function call. Example below
    println(persons.minBy { p -> p.startYear })
    println(persons.minBy { it.startYear })
    println(persons.minBy(HistoricalPerson::startYear)) // Known as a "member reference"

    run(::topLevel) // Member reference to top level function

    println(countTo100())
    println(countTo100usingApply())

    findByLastNameExhibitGlobalReturn(persons, "Boru")
    findByLastNameExhibitLocalReturn(persons, "Boru")

    nestedLambdaLabels()
}

fun topLevel() = println("i'm a top level function!")

// "data" class provides toString for free :)
data class HistoricalPerson(val firstName: String, val lastName: String, val startYear: Int) {
}

// Could convert this from a block body to an expression body which would be even more consise!!!
fun countTo100(): String {
    val numbers = StringBuilder()
    return with(numbers) {
        for (i in 1..99) {
            this.append(i) // "this" refers to the receiver e.g the object we are acting upon, in this case numbers
            append(", ")  // And we don't have to use "this"
        }
        append(100)
        toString()
    }
}

fun countTo100usingApply() =
    StringBuilder().apply() {
        for (i in 1..99) {
            this.append(i) // "this" refers to the receiver e.g the object we are acting upon, in this case numbers
            append(", ")  // And we don't have to use "this"
        }
        append(100)

    }.toString()

fun findByLastNameExhibitGlobalReturn(persons: List<HistoricalPerson>, lastName: String) {

    persons.forEach {
        if (it.lastName == lastName) {
            println("yes person with name $lastName exists")
            return
        }
    }
    // Because the lambda "returns" this line doesn't get printed even though it exists in the function hosting the lambda!!!
    println("no person with that name exists.")
}

fun findByLastNameExhibitLocalReturn(persons: List<HistoricalPerson>, lastName: String) {
    // "labelForReturnBlock@" is a label
    persons.forEach labelForReturnBlock@ {
        if (it.lastName == lastName) {
            println("yes person with name $lastName exists")
            return@labelForReturnBlock // telling it to return to the label means it is now a "locql return" and continues the rest of the function
        }
    }
    // Local return will reach here...
    println("no person with that name exists.")
}

fun nestedLambdaLabels() {
    "string one".apply stringone@ {

        println(toLowerCase())
        "string two".apply {
            println(toLowerCase())
            println(this@stringone.toUpperCase()) // access outer lambda receiver object from inside second receiver object using a label
        }
    }
}