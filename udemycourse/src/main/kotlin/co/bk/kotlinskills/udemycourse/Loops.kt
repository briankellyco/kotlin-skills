package co.bk.kotlinskills.udemycourse

fun main(args: Array<String>) {

    val range = 1..5
    for (i in range){
        println(i)
    }

    for (num in 1..20 step 4) {
        println(num)
    }

    for (i in 20 downTo 15) {
        println(i)
    }

    for (i in 20 downTo 10 step 5) {
        println(i)
    }

    // Only counts up to and excluding last range number
    for (i in 3 until 10) {
        println(i)
    }

    val seasons = arrayOf("spring", "summer", "herbst", "winter")
    for (season in seasons) {
        println(season)
    }

    val notASeason = "whatever" !in seasons
    println(notASeason)

    val notInRange = 32 !in 1..10
    println(notInRange)

    val str = "hello"
    print('e' !in str)
    print('e' in str)

    // Arrays have indices
    for (index in seasons.indices) {
        println("${seasons[index]} is season number $index")
    }

    // Labels applied to loops with break and continue
    for (i in 1..3) {
        println("i = ${i}")
        jloop@ for (j in 1..4) {
            println("j = ${j}")
            for (k in 5..10) {
                println("k = ${k}")
                if (k == 7) {
                    break@jloop;
                }
            }
        }
    }

    // fibonacci sequence simplified...
    var total: Int
    var secondToLast = 0
    var last = 1

    println(secondToLast)
    println(last)
    for (i in 1..13) {
        total = secondToLast + last
        println(total)
        secondToLast = last
        last = total
    }

}