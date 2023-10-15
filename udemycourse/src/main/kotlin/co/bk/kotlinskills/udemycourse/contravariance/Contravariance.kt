package co.bk.kotlinskills.udemycourse.contravariance

fun main(args: Array<String>) {

    val flowerTender = object: FlowerCare<Flower> {
        //override fun prune(flower: Flower) = println("I'm pruning a flower!")
        override fun prune(flower: Flower) = println("I'm pruning a ${flower.name}!")
    }

    val roseTender = object: FlowerCare<Rose> {
        override fun prune(flower: Rose) = println("I'm pruning a rose!")
    }

    val roseGarden = Garden<Rose>(listOf(Rose(), Rose()), roseTender)
    roseGarden.tendFlower(0)

    val daffodilTender = object: FlowerCare<Daffodil> {
        override fun prune(flower: Daffodil) = println("I'm pruning a daffodil!")
    }

    val daffodilGarden = Garden<Daffodil>(listOf(Daffodil(), Daffodil(), Daffodil()), daffodilTender)
    daffodilGarden.tendFlower(2)

    /*
     * Would not work without the IN keyword on the interface definition that made it contravariant
     */
    val daffodilGardenCovariant = Garden<Daffodil>(listOf(Daffodil(), Daffodil(), Daffodil()), flowerTender)
    daffodilGardenCovariant.tendFlower(2)
}

/* Known as "invariant" as it ONLY wants a class of Flower and not Rose. The implication of this invariance is that if you try
 * to "waterGarden" only  Flowers will be accepted currently (and not Roses)
 * Change that behaviour by adding "out" keyword which makes it covariant (and thus it now accepts Garden<Rose>).
 */
class Garden<T: Flower>(val flowers: List<T>, val flowerCare: FlowerCare<T>) {

    fun pickFlower(i: Int): T = flowers[i]

    fun tendFlower(i: Int) {
        flowerCare.prune(flowers[i])
    }
}


//fun convertToInt(collection: List<Number>) {
//    for (num in collection) {
//        println("${num.toInt()}")
//    }
//}
//
//fun waterGarden(garden: Garden<Flower>) {
//}
//
//fun tendGarden(roseGarden: Garden<Rose>) {
//
//    waterGarden(roseGarden)
//}

open class Flower(val name: String) {

}

class Rose: Flower("Rose") {

}

class Daffodil: Flower("Daffodil") {

}

/**
 * Original that isn't contravariant
 */
//interface FlowerCare<T> {
//    fun prune(flower: T)
//}

//.... and this is contravariant
interface FlowerCare<in T> {
    fun prune(flower: T)
}