package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {

    println(FactoryClassMadeWithCompanion.accessPrivateVar())
    println(FactoryClassMadeWithCompanion.accessPrivateVar()) // In the past the "Companion" keyword had to be used... deprecated now...

    val factoryClassMadeWithCompanion01 = FactoryClassMadeWithCompanion.justAssign("this is the string asis")
    val factoryClassMadeWithCompanion02 =
        FactoryClassMadeWithCompanion.upperOrLowerCase("this is the string asis", false)
    println(factoryClassMadeWithCompanion01.someString)
    println(factoryClassMadeWithCompanion02.someString)
}


// Can statically be used.... or at least everything inside companion object
class FactoryClassMadeWithCompanion private constructor (val someString: String) {

    companion object {
        private var privateVar = 6

        fun accessPrivateVar() = "I'm accessing privateVar: $privateVar"

        // Method has an expression body
        fun justAssign(str: String) = FactoryClassMadeWithCompanion(str)

        // Method has a block body
        fun upperOrLowerCase(str: String, lowerCase: Boolean): FactoryClassMadeWithCompanion {
            if (lowerCase) {
                return FactoryClassMadeWithCompanion(str.toLowerCase())
            } else {
                return FactoryClassMadeWithCompanion(str.toUpperCase())
            }
        }
    }
}