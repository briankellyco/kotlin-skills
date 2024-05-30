



fun main() {
    val map = mutableMapOf(
            "key1" to "value1",
            "key2" to "value2",
            "key3" to "value3",
            "key4" to "value4"
    )

    val keysToRemove = listOf("key1", "key3")

    keysToRemove.forEach { key ->
        map.remove(key)
    }

    println("Map after removal: $map")
}