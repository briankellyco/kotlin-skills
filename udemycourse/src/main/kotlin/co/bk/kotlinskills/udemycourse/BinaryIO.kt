package co.bk.kotlinskills.udemycourse

import java.io.*

fun main() {

    // Resources directory needs to be referenced directly. Or co-locate it beside the Kotlin file
    var path = System.getProperty("user.home")
    path = path.plus("/Documents/CODE/GITHUB/kotlin-skills/udemycourse/src/main/resources/testfile.bin")

    val di = DataInputStream(FileInputStream(path))
    var si: String

    /*
     * Learning point - directly use java classes just without the new keyword
     */
    try {
        while(true) {
            si = di.readUTF()
            println(si)
        }
    } catch (e: EOFException) {
        println("End of file reached")
    } catch (e: IOException) {
        println("Some other I/O exception occurred")
    } finally {
        di.close()
    }

    /*
     * Learning point:
     *   Java has try-with-resources which ensures that each resource is closed at the end of the statement.
     *     https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
     *   Kotlin does not have a try-with-resources but it has the same concept which is the "use" function.
     *   Kotlin "use" function will close resources automatically.
     *
     * Meaning this is not possible:
     *        try (FileReader fr = FileReader(path)) {
     *          // use the resource here...
     *        }
     *
     */
    FileReader(path).use { reader ->
        val bufferedReader = reader.buffered()
        bufferedReader.forEachLine { line ->
            println(line)
        }

    } // The 'reader' will be automatically closed when this block exits

}