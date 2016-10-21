package codeforces

import java.nio.file.*
import org.jsoup.*
import java.io.PrintWriter

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
        fun writeStatement() {
            val fileName = "${statementsDir.absoluteFile}/$name.in"
            val text = doc.select("div.problem-statement").map { it.text() }
            Files.write(Paths.get(fileName), text, StandardOpenOption.CREATE)
        }
        writeStatement()

        fun getTags() : String? {
            val text = doc.select("span.tag-box").map { it.text() }
            if (text.isEmpty())
                return null
            return text.joinToString(" | ")
        }
        val tags = getTags()
        if (tags == null)
            noTagsFile.appendText(name + "\n")
        else
            tagsFile.appendText("$name: [$tags]\n")

        fun getTutorialUrl() : String? {
            val regex = Regex("<a href=\"(/blog/entry[^\"]*)\"[^>]*>Tutorial")
            val match = regex.find(page) ?: return null
            val parts = match.groups
            return crawler.wrapUrl(parts[1]!!.value)
        }
        val tutorialUrl = getTutorialUrl()
        if (tutorialUrl == null) {
            noTutorialFile.appendText(name + "\n")
            return
        }
        tutorialUrlsFile.appendText("$name -> $tutorialUrl\n")

        val tutorialPage = crawler.getPage(tutorialUrl)
        //<a href="/blog/entry/47644" title="Tutorial" target="_blank">Tutorial</a>
    }
}