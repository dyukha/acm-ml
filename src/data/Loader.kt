package data

import codeforces.statementsDir
import codeforces.tagsPath
import codeforces.tutorialsDir
import utils.readText
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.PrintWriter

/**
 * Created by dmitry on 22-Oct-16.
 */
fun load() {
    for (file in statementsDir.listFiles()) {
        val name = file.nameWithoutExtension
        statements[name] = splitToWords(readText(file))
        examplesCount++
    }
    val delta = 1.0 / examplesCount
    for (file in tutorialsDir.listFiles()) {
        val name = file.nameWithoutExtension
        solutions[name] = splitToWords(readText(file))
    }
    BufferedReader(FileReader(tagsPath)).use { fin ->
        while (true) {
            val line = fin.readLine()?.trim()
            if (line == null || line.isEmpty())
                break
            val split = line.indexOf(":")
            val name = line.substring(0, split)
            val tags = line.substring(split + 2).split("|").map({it.trim()}).toSortedSet()
            data.tags[name] = tags
            for (tag in tags)
                incr(tagFreq, tag, delta)
        }
    }
    for ((name, statement) in statements) {
        for (word in statement)
            incr(statementWordFreq, word, delta)
        val tags = data.tags[name]
        if (tags == null) {
            withoutTags.add(name)
            continue
        }
        for (word in statement) {
            val map = wordTagFreq.getOrPut(word, {mutableMapOf()})
            for (tag in tags)
                incr(map, tag, delta)
        }
    }
}
