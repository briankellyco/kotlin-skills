package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {

    val shortList: List<Short> = listOf(1,2,3,4)
    convertToInt(shortList)
}

fun convertToInt(collection: List<Number>) {
    for (num in collection) {
        println("${num.toInt()}")
    }
}

fun waterGarden(garden: Garden<Flower>) {
}

fun tendGarden(roseGarden: Garden<Rose>) {

    waterGarden(roseGarden)
}
open class Flower {

}

class Rose: Flower() {

}

/* Known as "invariant" as it ONLY wants a class of Flower and not Rose. The implication of this invariance is that if you try
 * to "waterGarden" only  Flowers will be accepted currently (and not Roses)
 * Change that behaviour by adding "out" keyword which makes it covariant (and thus it now accepts Garden<Rose>).
 */
class Garden<out T: Flower> {

    val flowers: List<T> = listOf()

    fun pickFlower(i: Int): T = flowers[i]

    // NOTE: problematic function now would be following after adding "out" as we are trying to use the type in an "in" position
    //fun plantFlower(flower: T) {
    //}
}