import codeforces.*

/**
 * Created by dmitry on 20-Oct-16.
 */
fun main(args: Array<String>) {
    val crawler = Crawler()
    val taskVisitor = TaskVisitor(crawler)
    val tasks = TasksUrlCrawler(crawler).getTasksUrls()
    for (task in tasks)
        taskVisitor.visit(task)
}