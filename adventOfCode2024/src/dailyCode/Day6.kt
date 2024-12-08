package dailyCode

import util.UtilFunctions
import java.awt.Point

fun main() {
    val input = UtilFunctions.readInputLines("Day6-2024-Input.txt")

    println("Distinct spaces patrolled: ${day6Part1(input)}")
    println("Possible obstruction locations: ${day6Part2(input)}")

    println("Merry Christmas!")
}

fun day6Part1(input: List<String>): Int {
    // not sure why now but the x and y seem to be flipped
    // TODO: investigate why this is happening... maybe eventually lol
    var gardLocation = getInitialGardLocation(input)
    var directionOfTravel = Direction.UP
    val path = mutableSetOf(gardLocation)

    while (true) {
        val nextPoint = when (directionOfTravel) {
            Direction.UP -> Point(gardLocation.x - 1, gardLocation.y)
            Direction.DOWN -> Point(gardLocation.x + 1, gardLocation.y)
            Direction.LEFT -> Point(gardLocation.x, gardLocation.y - 1)
            Direction.RIGHT -> Point(gardLocation.x, gardLocation.y + 1)
        }

        if (input.getOrNull(nextPoint.x)?.getOrNull(nextPoint.y) == null) {
            break
        }

        if (input.getOrNull(nextPoint.x)?.getOrNull(nextPoint.y) != '#') {
            gardLocation = nextPoint
            path.add(gardLocation)
        } else {
            directionOfTravel = when (directionOfTravel) {
                Direction.UP -> Direction.RIGHT
                Direction.DOWN -> Direction.LEFT
                Direction.LEFT -> Direction.UP
                Direction.RIGHT -> Direction.DOWN
            }
        }
    }
    return path.size
}

fun day6Part2(input: List<String>): Int {
    // not sure why now but the x and y seem to be flipped
    // TODO: investigate why this is happening... maybe eventually lol
    var gardLocation: Point
    var directionOfTravel: Direction
    var path: MutableSet<Point>
    var consecutiveRepeatSteps = 0
    var validNewObstructionLocations = 0

    input.getPoints().forEach { p ->
        gardLocation = getInitialGardLocation(input)
        directionOfTravel = Direction.UP
        path = mutableSetOf(gardLocation)
        val newObstructionLocation = p
        if (input.getOrNull(newObstructionLocation.x)?.getOrNull(newObstructionLocation.y) != '#'
                && newObstructionLocation != getInitialGardLocation(input)) {

            while (true) {
                val nextPoint = when (directionOfTravel) {
                    Direction.UP -> Point(gardLocation.x - 1, gardLocation.y)
                    Direction.DOWN -> Point(gardLocation.x + 1, gardLocation.y)
                    Direction.LEFT -> Point(gardLocation.x, gardLocation.y - 1)
                    Direction.RIGHT -> Point(gardLocation.x, gardLocation.y + 1)
                }

                if (input.getOrNull(nextPoint.x)?.getOrNull(nextPoint.y) == null) {
                    break
                }

                if (input.getOrNull(nextPoint.x)?.getOrNull(nextPoint.y) != '#' && nextPoint != newObstructionLocation) {
                    gardLocation = nextPoint
                    if (path.contains(gardLocation)) {
                        consecutiveRepeatSteps++
                        // brute forcy but it got the right answer, specific to my input
                        // TODO: come back to this after learning more about/using more kotlin magic
                        if (consecutiveRepeatSteps == 100) {
                            validNewObstructionLocations++
                            break
                        }
                    } else {
                        consecutiveRepeatSteps = 0
                    }
                    path.add(gardLocation)
                } else {
                    directionOfTravel = when (directionOfTravel) {
                        Direction.UP -> Direction.RIGHT
                        Direction.DOWN -> Direction.LEFT
                        Direction.LEFT -> Direction.UP
                        Direction.RIGHT -> Direction.DOWN
                    }
                }
            }
        }
    }
    return validNewObstructionLocations // not 69 and 2673 too high
}

fun getInitialGardLocation(input: List<String>): Point {
    var gardLocation = Point(0, 0)
    input.getPoints().forEach { p ->
        listOf(p).getObstruction(input).forEach() {
            if (it == '^') {
                gardLocation = p
            }
        }
    }
    return gardLocation
}


fun List<Point>.getObstruction(input: List<String>) = mapNotNull { input.getOrNull(it.x)?.getOrNull(it.y) }

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}
