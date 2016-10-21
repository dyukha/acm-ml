package codeforces

import java.io.File

/**
 * Created by dmitry on 21-Oct-16.
 */
val workDir = createDir("work")
val statementsDir = createDir("work/statements")
val pagesDir = createDir("work/htmls")
val noTutorialFile = createFile("work/noTutorial")
val tutorialUrlsFile = createFile("work/tutorialUrls")
val tagsFile = createFile("work/tags")
val noTagsFile = createFile("work/noTags")

fun createFile(path: String) : File {
    val res = File(path)
    res.createNewFile()
    res.writeText("")
    return res
}
fun createDir(path: String) : File {
    val res = File(path)
    res.mkdir()
    return res
}