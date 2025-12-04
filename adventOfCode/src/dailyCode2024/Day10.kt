package dailyCode2024

import util.UtilFunctions

fun main() {
    val input = UtilFunctions.readInputLines("Day10-2024-Input.txt")

   // day10Part1(input)

    println("Merry Christmas!")
}

//fun day10Part1(input: List<String>) {
//    var trailHeads = mutableListOf<Point>()
//
//    for (line in input) {
//        println(line)
//    }
//
//    input.getPoints().forEach { p ->
//        listOf(p).getObstruction(input).forEach {
//            if (it == '0') {
//                trailHeads.add(p)
//            }
//        }
//    }
//
//    trailHeads.forEach {
//        println(it)
//        var pathVal = 1
//        val possiblePaths = mutableListOf(listOf(it))
//        while (pathVal < 10) {
//            possiblePaths.addAll(findPath(input, pathVal.digitToChar(), possiblePaths))
//            pathVal++
//            println("NEW PATH VAL: $pathVal")
//        }
//    }
//
//}
//
//private fun findPath(input: List<String>, pathVal: Char, possiblePaths: MutableList<List<Point>>): Collection<List<Point>> {
//    val newPaths = mutableListOf<List<Point>>()
//    possiblePaths.forEach {
//        val stepUp = Point(it.x - 1, it.y)
//        val stepDown = Point(it.x + 1, it.y)
//        val stepLeft = Point(it.x, it.y - 1)
//        val stepRight = Point(it.x, it.y + 1)
//
//        if (input.getOrNull(stepUp.x)?.getOrNull(stepUp.y) == null) {
//            println("skip")
//        } else if (input[stepUp.x][stepUp.y] == pathVal) {
//            println("Go up")
//        }
//
//        if (input.getOrNull(stepDown.x)?.getOrNull(stepDown.y) == null) {
//            println("skip")
//
//        } else if (input[stepDown.x][stepDown.y] == pathVal) {
//            println(possiblePaths)
//            println("Go down")
//            var newPath = possiblePaths[0]
//            newPath = newPath.plus(stepDown)
//            newPaths.add(newPath)
//            println(possiblePaths)
//        }
//
//        if (input.getOrNull(stepLeft.x)?.getOrNull(stepLeft.y) == null) {
//            println("skip")
//
//        } else if (input[stepLeft.x][stepLeft.y] == pathVal) {
//            println("Go left")
//        }
//
//        if (input.getOrNull(stepRight.x)?.getOrNull(stepRight.y) == null) {
//            println("skip")
//
//        } else if (input[stepRight.x][stepRight.y] == pathVal) {
//            println("Go right")
//            var newPath = possiblePaths[0]
//            newPath = newPath.plus(stepRight)
//            possiblePaths.removeAt(0)
//            possiblePaths.add(newPath)
//            println(possiblePaths)
//        }
//        return newPaths
//    }
//}
