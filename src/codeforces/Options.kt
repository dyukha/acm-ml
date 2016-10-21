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
val tutorialUrlsFile = createFileForAppend("work/tutorialUrls", saveTutorials)
val noTutorialFile = createFileForAppend("work/0_noTutorial", saveTutorials)
val notLoadedTutorial = createFileForAppend("work/1_notLoadedTutorial", saveTutorials)
val noCaptionInTutorial = createFileForAppend("work/2_noCaptionInTutorial", saveTutorials)
val noCaptionInTutorialHtml = createFileForAppend("work/3_noCaptionInTutorialHtml", saveTutorials)
val noProblemCaptionInTutorial = createFileForAppend("work/4_noProblemCaptionInTutorial", saveTutorials)
val noLetterCaptionInTutorial = createFileForAppend("work/5_noLetterCaptionInTutorial", saveTutorials)

val tagsMap = mutableMapOf<String, Set<String>>()

fun createFileForAppend(path: String, cond: Boolean=true) : PrintWriter {
    if (cond) {
        val file = File(path)
        file.createNewFile()
        file.writeText("")
        return PrintWriter(FileWriter(file, true))
    }
    return PrintWriter(FileWriter("work/dummy"))
}

fun createDir(path: String) : File {
    val res = File(path)
    res.mkdir()
    return res
}