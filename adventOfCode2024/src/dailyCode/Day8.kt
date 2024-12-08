package dailyCode

import util.UtilFunctions
import java.awt.Point

var numLines: Int = 0
var numColumns: Int = 0
val antiNodeLocations = mutableSetOf<Point>()

fun main() {
    val input = UtilFunctions.readInputLines("Day8-2024-Input.txt")

    numLines = input.size
    numColumns = input[0].length
    val antennaLocations = mutableListOf<Point>()
    input.getPoints().forEach { p ->
        listOf(p).getAntennas(input).forEach {
            if (it != '.') {
                antennaLocations.add(p)
            }
        }
    }
    val antennaFrequencies = mutableMapOf<Char, MutableList<Point>>()
    antennaLocations.forEach {
        val antenna = input[it.x][it.y]
        if (antennaFrequencies[antenna] == null) {
            antennaFrequencies[antenna] = mutableListOf(it)
        } else {
            antennaFrequencies[antenna]!!.add(it)
        }
    }
    day8Part1(antennaFrequencies)

    println("Total anti-node locations: ${antiNodeLocations.size}")

    println("Merry Christmas!")
}

fun day8Part1(antennaFrequencies: MutableMap<Char, MutableList<Point>> ) {
    antennaFrequencies.forEach { (antenna, locations) ->
        var i = 0
        while (i < locations.size) {
            val current = locations[i]
            var j = i + 1
            while (j < locations.size) {
                val next = locations[j]
                val xDiff = next.x - current.x
                val yDiff = next.y - current.y

                Point (current.x - xDiff, current.y - yDiff).let {
                    if (it.x < 0 || it.y < 0 || it.x >= numLines || it.y >= numColumns) {

                    } else {
                        antiNodeLocations.add(it)
                    }
                }

                Point (next.x + xDiff, next.y + yDiff).let {
                    if (it.x < 0 || it.y < 0 || it.x >= numLines || it.y >= numColumns) {
                    } else {
                        antiNodeLocations.add(it)
                    }
                }

                j++
            }
            i++
        }

    }

}

fun List<Point>.getAntennas(input: List<String>) = mapNotNull { input.getOrNull(it.x)?.getOrNull(it.y) }

