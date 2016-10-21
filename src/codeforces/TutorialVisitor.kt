package codeforces

import org.jsoup.Jsoup
import utils.*
import java.io.File

/**
 * Created by dmitry on 21-Oct-16.
 */
class TutorialVisitor {
    fun visit(tutorialUrl: String, name: String, crawler: Crawler) {
        val tutorialPage = crawler.getPage(tutorialUrl)
        if (tutorialPage.contains("Tutorial is loading...")) {
            append(notLoadedTutorial, name + "\n")
            return
        }
        val letterPos = name.indexOfAny(('A'..'Z').toList().toCharArray())
        val contestNumber = name.substring(0..letterPos-1)
        val problemNumber = name.substring(letterPos)
        val doc = Jsoup.parse(tutorialPage)
        val text = doc.select("div.ttypography").first().text()
        if (text.contains(name)) {
            val file = File(tutorialsDir.absolutePath + "/" + name + ".out")
            val start = text.indexOf(name)
            val end = text.indexOf(contestNumber, start + 1)
            writeText(file, if (end == -1) text.substring(start) else text.substring(start..end-1))
            return
        } else {
            append(noCaptionInTutorial, name + "\n")
        }
    }
}