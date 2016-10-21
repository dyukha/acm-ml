package utils

import java.io.*

/**
 * Created by dmitry on 21-Oct-16.
 */

fun append(file: PrintWriter, text: String) {
    file.append(text)
    file.flush()
}

fun readText(file: File) = BufferedReader(FileReader(file)).use { it.readText()}
//fun readSlow(file: File) = file.readText()
//fun readText(file: File) = file.readText()

fun writeText(file: File, text: String) {
    file.createNewFile()
    FileWriter(file).use {
        it.write(text)
        it.flush()
    }
}

fun measureTime(name: String, function: () -> Unit) {
    println("Started $name")
    val startTime = System.nanoTime()
    function.invoke()
    println("Finished $name: ${(System.nanoTime() - startTime) / 1e9}")
}
