package codeforces

import utils.*
import java.io.File
import java.io.InputStreamReader
import java.net.URL
import java.nio.file.Paths

/**
 * Created by dmitry on 20-Oct-16.
 */
class Crawler {
    fun wrapUrl(url: String) = "http://codeforces.com$url?locale=en"
    val visited = mutableMapOf<String, String>()

    fun getPage(url : String, force: Boolean = false) : String {
        val fileName = Paths.get(pagesDir.absolutePath,
                url.replace(Regex("[^a-zA-Z0-9.-]"), "_").replace("http___codeforces.com_", "") + ".html").toString()
        val file = File(fileName)

        if (force) {
            val res = InputStreamReader(URL(url).openStream()).readText()
            visited[url] = res
            file.writeText(res)
            return res
        }
        if (visited.containsKey(url))
            return visited[url]!!
        if (file.exists()) {
            val res = readText(file)
            visited[url] = res
            return res
        }
        val res = InputStreamReader(URL(url).openStream()).readText()
        visited[url] = res
        file.writeText(res)
        return res
    }
}