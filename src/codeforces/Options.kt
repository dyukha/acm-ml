package codeforces

import utils.append
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

/**
 * Created by dmitry on 21-Oct-16.
 */
val workDir = createDir("work")
val statementsDir = createDir("work/statements")
val pagesDir = createDir("work/htmls")
val saveTags = false
val tagsFile = createFileForAppend("work/tags", saveTags)
val noTagsFile = createFileForAppend("work/noTags", saveTags)
val saveStatements = false
val saveTutorials = true
val tutorialsDir = createDir("work/tutorials")
val noTutorialFile = createFileForAppend("work/noTutorial", saveTutorials)
val tutorialUrlsFile = createFileForAppend("work/tutorialUrls", saveTutorials)
val noCaptionInTutorial = createFileForAppend("work/noCaptionInTutorial", saveTutorials)
val notLoadedTutorial = createFileForAppend("work/notLoadedTutorial", saveTutorials)

val tagsMap = mutableMapOf<String, Set<String>>()

fun createFileForAppend(path: String, cond: Boolean=true) : PrintWriter {
    if (cond) {
        val file = File(path)
        file.createNewFile()
        file.writeText("")
        return PrintWriter(FileWriter(file, true))
    }
    return PrintWriter(FileWriter("dummy"))
}

fun createDir(path: String) : File {
    val res = File(path)
    res.mkdir()
    return res
}