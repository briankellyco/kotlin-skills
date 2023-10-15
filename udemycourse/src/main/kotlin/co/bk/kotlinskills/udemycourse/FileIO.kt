package co.bk.kotlinskills.udemycourse

import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.io.path.copyTo


/*
 * Walking the file tree.
 *   https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/
 *
 * File class:
 *   https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/java.io.-file/
 *
 * Path class:
 * 1. use  "kotlin.io.path.Path" and NOT java.nio.file.Path to read file resources.
 *
 */


// Kotlin doesn't have its own IO classes so has tacked on extensions to the Java IO classes.


fun main() {

    // Working directory is the root of the intellij project. How to get it?
    var path = Paths.get("").toAbsolutePath().toString()
    println("Working Directory = $path")

    // Resources directory needs to be referenced directly. Or co-locate it beside the Kotlin file.
    path = System.getProperty("user.home")
    path = path.plus("/Documents/CODE/GITHUB/kotlin-skills/udemycourse/src/main/resources/testfile.txt")

    /*
     * Learning point:
     *   File().reader() is a Kotlin extension function.
     *
     *   It is equivalent to the Java:
     *     new InputStreamReader(new FileInputStreamReader(new File("/testfile.txt")), "UTF-8")
     */
    File(path).reader().forEachLine { println(it) }


    /*
     * Learning point on closing resources:
     *   1. Load the resource, use it and then close it manually OR
     *   2. Use the "use" function which will close the resource automatically.
     *         Go To > Declaration (to read the javadoc for the function)
     */
    // 1
    val reader = File(path).reader()
    val lines = reader.readText()
    println(lines)
    reader.close()

    // 2 Auto closes and much more concise
    val lines02 = File(path).reader().use { it.readText() }
    println(lines02)





}

//fun readTextFileFromResources(fileName: String): String {
//    val classLoader = Thread.currentThread().contextClassLoader
//    val inputStream: InputStream = classLoader.getResourceAsStream(fileName)
//    val text = inputStream.bufferedReader().use { it.readText() }
//    return text
//}
//
//fun getResourceAsText(path: String): String? =
//    object {}.javaClass.getResource(path)?.readText()
