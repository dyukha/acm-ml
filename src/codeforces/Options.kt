package codeforces

import main.needPreprocessData
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

/**
 * Created by dmitry on 21-Oct-16.
 */
val workDir = createDir("work")
val statementsDir = createDir("work/statements")
val pagesDir = createDir("work/htmls")
val saveTags = true
val tagsPath = "work/tags"
val tagsFile = createFileForAppend(tagsPath, saveTags && needPreprocessData)
val noTagsFile = createFileForAppend("work/noTags", saveTags && needPreprocessData)
val saveStatements = true
val saveTutorials = true
val tutorialsDir = createDir("work/tutorials")
val tutorialUrlsFile = createFileForAppend("work/tutorialUrls", saveTutorials && needPreprocessData)
val noTutorialFile = createFileForAppend("work/0_noTutorial", saveTutorials && needPreprocessData)
val notLoadedTutorial = createFileForAppend("work/1_notLoadedTutorial", saveTutorials && needPreprocessData)
val noCaptionInTutorial = createFileForAppend("work/2_noCaptionInTutorial", saveTutorials && needPreprocessData)
val noCaptionInTutorialHtml = createFileForAppend("work/3_noCaptionInTutorialHtml", saveTutorials && needPreprocessData)
val noProblemCaptionInTutorial = createFileForAppend("work/4_noProblemCaptionInTutorial", saveTutorials && needPreprocessData)
val noLetterCaptionInTutorial = createFileForAppend("work/5_noLetterCaptionInTutorial", saveTutorials && needPreprocessData)

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