package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {

    println(OtherClassWithCompanion.accessPrivateVar())
    println(OtherClassWithCompanion.accessPrivateVar()) // In the past the "Companion" keyword had to be used... deprecated now...
}

// Instance required to use
class OtherClass {
    private val privateVar = 6

    fun accessPrivateVar() = println("I'm accessing privateVar: $privateVar")
}

// Can statically be used.... or at least everything inside companion object
class OtherClassWithCompanion {

    val someString: String

    constructor(str: String) {
        someString = str
    }

    constructor(str: String, lowerCase: Boolean) {
        if (lowerCase) {
            someString = str.toLowerCase()
        } else {
            someString = str.toUpperCase()
        }
    }

    companion object {
        private var privateVar = 6

        fun accessPrivateVar() = "I'm accessing privateVar: $privateVar"
    }
}