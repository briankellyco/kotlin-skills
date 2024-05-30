package co.bk.kotlinskills.sandbox

import co.bk.kotlinskills.sandbox.data.Lamp
import java.time.OffsetDateTime
import java.util.*

fun main(args: Array<String>) {

    exampleNullProcessingWithLet();

}


private fun exampleNullProcessingWithLet() {

    var myDateNotNull: Date? = null

    myDateNotNull = OffsetDateTime.now()?.toInstant()?.let { Date.from(it) }

    var myDateNull: Date? = null

    val inputNullDate: OffsetDateTime? = null;

    myDateNotNull = inputNullDate?.toInstant()?.let { Date.from(it) }

    //println ("exampleIterateEmptyList - pairList is empty? ${pairList.isEmpty() }")
}


