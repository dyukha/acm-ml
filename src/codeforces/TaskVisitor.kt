package codeforces

import utils.*
import org.jsoup.Jsoup
import java.io.*

/**
 * Created by dmitry on 20-Oct-16.
 */
class TaskVisitor(val crawler: Crawler) {
    fun visit(url: String) {
        fun getName() : String {
            val regex = Regex("/([^/]*)/([^?/]*)\\?")
            val parts = regex.find(url)!!.groups
            return parts[1]!!.value + parts[2]!!.value
        }
        val name = getName()
        val page = crawler.getPage(url)
        val doc = Jsoup.parse(page)
        if (saveStatements) {
            fun writeStatement() {
                val fileName = "${statementsDir.absoluteFile}/$name.in"
                val text = doc.select("div.problem-statement").map { it.text() }
                writeText(File(fileName), text.joinToString("\n"))
            }
            writeStatement()
        }

        if (saveTags) {
            fun getTags(): List<String>? {
                val text = doc.select("span.tag-box").map { it.text() }
                if (text.isEmpty())
                    return null
                return text
            }

            val tags = getTags()
            if (tags == null) {
                append(noTagsFile, name + "\n")
            } else {
                append(tagsFile, "$name: " + tags.joinToString(" | ") + "\n")
                tagsMap[name] = tags.toSet()
            }
        }
        if (saveTutorials) {
            fun getTutorialUrl(): String? {
                val regex = Regex("<a href=\"(/blog/entry[^\"]*)\"[^>]*>Tutorial")
                val match = regex.find(page) ?: return null
                val parts = match.groups
                return crawler.wrapUrl(parts[1]!!.value)
            }

            val tutorialUrl = getTutorialUrl()
            if (tutorialUrl == null) {
                append(noTutorialFile, name + "\n")
            } else {
                append(tutorialUrlsFile, "$name -> $tutorialUrl\n")
                TutorialVisitor().visit(tutorialUrl, name, crawler)
            }
        }
    }
}