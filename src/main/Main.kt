package main

import codeforces.*
import utils.*
import data.*

/**
 * Created by dmitry on 20-Oct-16.
 */

fun preprocess() {
    if (!needPreprocessData)
        return
    val crawler = Crawler()
    val taskVisitor = TaskVisitor(crawler)
    val tasks = TasksUrlCrawler(crawler).getTasksUrls()
    for (task in tasks)
        taskVisitor.visit(task)
}

fun main(args: Array<String>) {
    measureTime("preprocess")  {preprocess()}
    measureTime("load")  {load()}
    measureTime("solve")  {solve()}

}