package codeforces

/**
 * Created by dmitry on 20-Oct-16.
 */
class TasksUrlCrawler(private val crawler: Crawler) {
    val archiveUrl = crawler.wrapUrl("/problemset")

    fun getProblemsUrl(html: String) : Sequence<String> {
        val regex = Regex("<a href=\"(/problemset/problem/[^\"]*)\">")
        return regex.findAll(html).map { crawler.wrapUrl(it.groups[1]!!.value) }
    }

    fun getNextPage(html: String) : String? {
        val regex = Regex("<a href=\"(/problemset/page/[^\"]*)\" class=\"arrow\">&rarr")
        val match = regex.find(html) ?: return null
        return crawler.wrapUrl(match.groups[1]!!.value)
    }

    fun getTasksUrls() : Set<String> {
        val tasks = mutableSetOf<String>()
        var curUrl : String? = archiveUrl
        while (curUrl != null) {
            val curPage = crawler.getPage(curUrl)
            tasks.addAll(getProblemsUrl(curPage))
//            if (tasks.size > 10)
//                break
            curUrl = getNextPage(curPage)
        }
        return tasks
    }
}