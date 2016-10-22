package data

import java.io.PrintWriter

/**
 * Created by dmitry on 22-Oct-16.
 */
fun solve() {
    PrintWriter("work/outTagFile").use { outTagFile ->
        for (name in statements.keys) {
            val rank = mutableMapOf<String, Double>()
            for (word in statements[name]!!) {
                val map = wordTagFreq[word] ?: continue
                for ((tag, value) in map) {
                    val cntWord = statementWordFreq[word] ?: continue
                    val cntTag = tagFreq[tag] ?: continue
                    incr(rank, tag, value / cntWord / cntTag)
                }
            }
            val tagsList = rank.toList().sortedByDescending { it.second }.take(10)
            outTagFile.println(name)
            tags[name]?.let { outTagFile.println(it.joinToString(" | ")) }
            outTagFile.println(tagsList.map({ t -> "${t.first}*${t.second}" }).joinToString(" | "))
            outTagFile.println()
        }
    }
}