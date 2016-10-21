package codeforces

import java.io.File
import java.io.InputStreamReader
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * Created by dmitry on 20-Oct-16.
 */
class Crawler {
    fun wrapUrl(url: String) = "http://codeforces.com$url?locale=en"
    val visited = mutableMapOf<String, String>()

    fun getPage(url : String) : String {
        if (visited.containsKey(url))
            return visited[url]!!
        val fileName = Paths.get(pagesDir.absolutePath,
                url.replace(Regex("[^a-zA-Z0-9.-]"), "_").replace("http___codeforces.com_", "") + ".html").toString()
        val file = File(fileName)
        if (file.exists()) {
            val res = file.readText()
            visited[url] = res
            return res
        }
        val res = InputStreamReader(URL(url).openStream()).readText()
        visited[url] = res
        file.writeText(res)
        return res
    }
}