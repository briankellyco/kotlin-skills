package co.bk.kotlinskills.udemycourse

import java.time.Year

// Object created the first time that it is used.
object SingletonCompanyCommunications {

    val currentYear = Year.now().value

    fun getTagLine() = "Rock n' Roll rocks!"

    fun getCopyrightLine() = "Copyright \u00A9  $currentYear bkco. All rights reserved."
}

fun main(args: Array<String>) {
    println(SingletonCompanyCommunications.getCopyrightLine())
    println(SingletonCompanyCommunications.getTagLine())
}