package co.bk.kotlinskills.sandbox

import co.bk.kotlinskills.sandbox.data.Lamp

fun main(args: Array<String>) {

    exampleIterateEmptyList();
    exampleIteratePopulatedList();
    exampleFilterInstances();

}

open class EducationalMaterial(val title: String)
class Book(title: String, val author: String): EducationalMaterial(title)
class Video(title: String, val duration: Int): EducationalMaterial(title)
class Article(title: String, val publishedDate: String): EducationalMaterial(title)

private fun exampleIterateEmptyList() {

    val emptyList: List<Lamp> = listOf() // or emptyList()
    val pairList = convertLampsToPairs(emptyList)
    println ("exampleIterateEmptyList - pairList is empty? ${pairList.isEmpty() }")
}

private fun exampleIteratePopulatedList() {
    val lamps = listOf(
            Lamp(1, "Red", "Model1", 100),
            Lamp(2, "Blue", "Model2", 150),
            Lamp(3, "Green", "Model3", 200)
    )
    val pairList = convertLampsToPairs(lamps)

    val commaSeparatedValues = pairList.joinToString(", ") { pair ->
        "${pair.first}, ${pair.second}"
    }

    println ("exampleIteratePopulatedList - pairList values are: ${commaSeparatedValues }")
}

private fun exampleFilterInstances() {

    val materials = listOf(
            Book("Kotlin Programming", "Buzz Aldrin"),
            Video("Kotlin a visual guide", 25),
            Article("Kotlin and Java a comparison", "2024-02-13"),
            Video("Advanced Kotlin", 5),
            Book("Java Programming", "Neil Armstrong")
    )

    val videoList = materials.filterIsInstance<Video>()

    videoList.forEach { video ->
            println("${video.title}, Duration: ${video.duration} minutes")
    }
}

private fun convertLampsToPairs(lampList: List<Lamp>): List<Pair<Int, String>> {
    // Simple syntax -> return lampList.map { lamp -> lamp.id to lamp.colour }

    // And values concatenated
    return lampList.map { lamp -> lamp.id to "${lamp.colour}-${lamp.model}-${lamp.power}" }
}