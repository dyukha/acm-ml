package main

import codeforces.*
import utils.*

/**
 * Created by dmitry on 20-Oct-16.
 */
fun main(args: Array<String>) {
    measureTime("main") {
        val crawler = Crawler()
        val taskVisitor = TaskVisitor(crawler)
        val tasks = TasksUrlCrawler(crawler).getTasksUrls()
        for (task in tasks)
            taskVisitor.visit(task)
    }
}