package co.bk.kotlinskills.udemycourse

interface MyInterface {

    val number: Int

    fun myFunction(str: String): String
}

interface MySubInterface: MyInterface {

    fun mySubFunction(num: Int): String
}

open class Implementation: MySubInterface {

    // keyword "override" because its already declared in interface
    override val number: Int = 25

    override fun myFunction(str: String): String {
        TODO("Not yet implemented")
    }

    override fun mySubFunction(num: Int): String {
        TODO("Not yet implemented")
    }
}