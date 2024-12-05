package dailyCode

import util.UtilFunctions
import java.awt.Point

    fun main() {
        val input = UtilFunctions.readInputLines("Day4-2024-Input.txt")

        println("Total XMAS found: ${day4Part1(input)}")
        println("Total X-MAS found: ${day4Part2(input)}")
        println("Merry Christmas!")
    }

    fun day4Part1(input: List<String>): Int {
        val possibleDirections = listOf(Point(-1, 1), Point(0, 1), Point(1, 1),
                Point(-1, 0), Point(1, 0), Point(-1, -1),
                Point(0, -1), Point(1, -1))
        return input.getPoints().sumOf { p ->
            possibleDirections.count { v ->
                val word = listOf(p, p + v, p + v + v, p + v + v + v).getWord(input)
                word == "XMAS"
            }
        }

    }

    fun day4Part2(input: List<String>): Int {
        return input.getPoints().count { p ->
            val word1 = listOf(p + Point(-1, 1), p ,p + Point(1, -1)).getWord(input)
            val word2 = listOf(p + Point(1, 1), p ,p + Point(-1, -1)).getWord(input)
            (word1 == "MAS" || word1 == "SAM") && (word2 == "MAS" || word2 == "SAM")
        }

    }
    operator fun Point.plus(other: Point): Point = Point(x + other.x, y + other.y)
    fun List<String>.getPoints() = indices.flatMap { x -> this[x].indices.map { y -> Point(x, y) } }
    fun List<Point>.getWord(input: List<String>) = mapNotNull { input.getOrNull(it.x)?.getOrNull(it.y) }.joinToString("")

