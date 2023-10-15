package co.bk.kotlinskills.udemycourse.dto


/* Example dto class from java.
 *
 * Notice that in Kotlin we DO NOT make the vars "private" (unlike java) because we want them to be modifiable by the outside (and not restricted to
 * just code in this class)
 */
class BicycleKotlin(var cadence: Int, var speed: Int, var gear: Int) {

    fun applyBrake(decrement: Int) {
        speed -= decrement
    }

    fun speedUp(increment: Int) {
        speed += increment
    }
}