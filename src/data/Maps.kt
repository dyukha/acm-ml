package data

import java.util.*

/**
 * Created by dmitry on 22-Oct-16.
 */
val problems = TreeSet<String>()
val tags = mutableMapOf<String, SortedSet<String>>()
val inferredTag = mutableMapOf<String, SortedSet<String>>()
val statements = mutableMapOf<String, List<String>>()
val solutions = mutableMapOf<String, List<String>>()
val inferredSolution = mutableMapOf<String, String>()
var examplesCount = 0
val wordTagFreq = mutableMapOf<String, MutableMap<String, Double>>()
val wordWordFreq = mutableMapOf<String, MutableMap<String, Int>>()
val withoutTags = mutableSetOf<String>()
val tagFreq = mutableMapOf<String, Double>()

val statementWordFreq = mutableMapOf<String, Double>()
val solutionWordCount = mutableMapOf<String, Int>()

fun splitToWords(str: String) : List<String> {
    return str
            .split(Regex("\\W+"))
            .map {it.trim()}
            .filter {!it.isEmpty()}
}

fun<K> incr(map: MutableMap<K, Double>, key: K, delta: Double) {
    map[key] = map.getOrElse(key, {0.0}) + delta
}