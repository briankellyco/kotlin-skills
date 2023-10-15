package co.bk.kotlinskills.udemycourse.textadventure

// A function in a file without the class syntax is known as a "top level function".
fun main(args: Array<String>) {

    val locations = LocationHelper().readLocationInfo()
    var loc = 64

    while (true) {

        // Elvis operator "?:" sets default if null is detected
        val location = locations[loc] ?: Location(0,
            "Sorry, something went wrong, so the game will terminate")

        println(location.description)

        if (location.locationID == 0) {
            break
        }

        print("Available exits are: ")
        location.exits.keys.forEach {
            print("$it, ")
        }

        val direction = readLine()?.toUpperCase() ?: "Z"
        if (location.exits.containsKey(direction)) {
            loc = location.exits[direction]!!
        }
        else {
            println("You can't go in that direction")
        }
    }

}