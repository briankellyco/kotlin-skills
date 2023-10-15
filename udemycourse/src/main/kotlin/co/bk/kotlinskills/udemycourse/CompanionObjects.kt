package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {

    println(SomeClassWithCompanion.accessPrivateVar())
    println(SomeClassWithCompanion.accessPrivateVar()) // In the past the "Companion" keyword had to be used... deprecated now...
}

// Instance required to use
class SomeClass {
    private val privateVar = 6

    fun accessPrivateVar() = println("I'm accessing privateVar: $privateVar")
}

// Can statically be used.... or at least everything inside companion object
class SomeClassWithCompanion {

    companion object {
        private var privateVar = 8

        fun accessPrivateVar() = "I'm accessing privateVar: $privateVar"
    }
}