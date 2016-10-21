package codeforces

import org.jsoup.Jsoup
import utils.*
import java.io.File

/**
 * Created by dmitry on 21-Oct-16.
 */
class TutorialVisitor {
    fun visit(tutorialUrl: String, name: String, crawler: Crawler) {
        val tutorialPage = crawler.getPage(tutorialUrl).replace("</strong><strong>", "")
        if (tutorialPage.contains("Tutorial is loading...")) {
            append(notLoadedTutorial, name + "\n")
            return
        }
        fun substr(str: String, start: Int, end: Int) =
            if (end == -1) str.substring(start)
            else str.substring(start..end-1)
        val file = File(tutorialsDir.absolutePath + "/" + name + ".out")
        val letterPos = name.indexOfAny(('A'..'Z').toList().toCharArray())
        val contestNumber = name.substring(0..letterPos-1)
        val problemNumber = name.substring(letterPos)
        val doc = Jsoup.parse(tutorialPage)
        val element = doc.select("div.ttypography").first()
        val text = element.text()
        if (text.contains(name)) {
            val start = text.indexOf(name)
            val end = text.indexOf(contestNumber, start + 1)
            writeText(file, substr(text, start, end))
            return
        }
        append(noCaptionInTutorial, name + "\n")
        val tutorialHtml = element.html()
        val linkCandidate = "/contest/$contestNumber/problem/$problemNumber"
        if (tutorialHtml.contains(name) || tutorialHtml.contains(linkCandidate)) {
            fun getStart() : Int {
                val nameStart = tutorialHtml.indexOf(name)
                if (nameStart != -1)
                    return nameStart
                return tutorialHtml.indexOf(linkCandidate) + linkCandidate.length + 2
            }
            val start = getStart()
            val end = tutorialHtml.indexOf(contestNumber, start + 1)
            val selectedHtml = substr(tutorialHtml, start, end)
            val doc = Jsoup.parse(selectedHtml)
            writeText(file, doc.text())
            return
        }
        append(noCaptionInTutorialHtml, name + "\n")

        fun anyPos(str: String, patterns: List<String>, startIndex: Int = 0) : Int {
            for (pat in patterns) {
                val ind = str.indexOf(pat, startIndex, ignoreCase = true)
                if (ind != -1)
                    return ind
            }
            return -1
        }

        val problemCandidates = listOf(">Problem $problemNumber<", ">Task $problemNumber<", ">Problem $problemNumber.<", ">Task $problemNumber.<",
                "> Problem $problemNumber<", "> Task $problemNumber<", "> Problem $problemNumber.<", "> Task $problemNumber.<", ">Problem $problemNumber:")
        val nextC = (problemNumber[0] + 1).toChar()
        if (anyPos(tutorialHtml, problemCandidates) != -1) {
            val start = anyPos(tutorialHtml, problemCandidates) + 1
            val nextCandidates = listOf(">Problem $nextC<", ">Task $nextC<", ">Problem $nextC.<", ">Task $nextC.<",
                    "> Problem $nextC<", "> Task $nextC<", "> Problem $nextC.<", "> Task $nextC.<", ">Problem $nextC:")
            val end = anyPos(tutorialHtml, nextCandidates, start)
            val selectedHtml = substr(tutorialHtml, start, end)
            val doc = Jsoup.parse(selectedHtml)
            writeText(file, doc.text())
            return
        }
        append(noProblemCaptionInTutorial, name + "\n")
        val letterCaptionCandidates = listOf("<strong>$problemNumber</strong>", ">$problemNumber.")
        if (anyPos(tutorialHtml, letterCaptionCandidates) != -1) {
            val start = anyPos(tutorialHtml, letterCaptionCandidates) + 1
            val nextCandidates = listOf("<strong>$nextC</strong>", ">$nextC.")
            val end = anyPos(tutorialHtml, nextCandidates, start)
            val selectedHtml = substr(tutorialHtml, start, end)
            val doc = Jsoup.parse(selectedHtml)
            writeText(file, doc.text())
            return
        }
        append(noLetterCaptionInTutorial, name + "\n")

//        A —

    }
}