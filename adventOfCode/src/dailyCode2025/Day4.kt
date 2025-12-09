package dailyCode2025

import util.UtilFunctions
import kotlin.collections.mutableListOf
import kotlin.text.indices

val DIRECTIONS = listOf(
    Pair(-1, -1), Pair(-1, 0), Pair(-1, 1),
    Pair(0, -1), /* current position */ Pair(0, 1),
    Pair(1, -1), Pair(1, 0), Pair(1, 1)
)

data class ChangeInfo(val coordinatesToChange: List<Pair<Int, Int>>, val accessibleCount: Int)

fun main() {
    val input = UtilFunctions.readInputLines("Day4-2025-Input.txt")

    day4Part1(input)
    day4Part2(input)

    println("Merry Christmas!")
}

private fun day4Part1(input: List<String>) {

    var accessibleCount = 0

    for (row in input.indices) {
        for (col in input[row].indices) {
            if (input[row][col] == '@') {
                val adjacentRolls = DIRECTIONS.count { (dx, dy) ->
                    val newRow = row + dx
                    val newCol = col + dy
                    newRow in input.indices && newCol in input[newRow].indices && input[newRow][newCol] == '@'
                }
                if (adjacentRolls < 4) {
                    accessibleCount++
                }
            }
        }
    }

    println("Accessible rolls of paper Part 1: $accessibleCount")
}

private fun day4Part2(input: List<String>) {
    var totalAccessibleCount = 0
    var newInput = mutableListOf<String>()
    input.forEach { newInput.add(it) }

    var accessibleCount = 1
    while (accessibleCount >= 1) {
        val changeInfo = rinceAndRepeat(newInput)

        accessibleCount = changeInfo.accessibleCount
        newInput = findAndReplace(newInput, changeInfo.coordinatesToChange)

        totalAccessibleCount += changeInfo.accessibleCount
    }

    println("Accessible rolls of paper Part 2: $totalAccessibleCount")
}

private fun rinceAndRepeat(input: List<String>): ChangeInfo {
    var accessibleCount = 0
    var cordinatesToReplace = mutableListOf<Pair<Int, Int>>()

    for (row in input.indices) {
        for (col in input[row].indices) {
            if (input[row][col] == '@') {
                val adjacentRolls = DIRECTIONS.count { (dx, dy) ->
                    val newRow = row + dx
                    val newCol = col + dy
                    newRow in input.indices && newCol in input[newRow].indices && input[newRow][newCol] == '@'
                }
                if (adjacentRolls < 4) {
                    accessibleCount++
                    cordinatesToReplace.add(Pair(row, col))
                }
            }
        }
    }

    return ChangeInfo(cordinatesToReplace, accessibleCount)
}


private fun findAndReplace(input: List<String>, cordinatesToReplace: List<Pair<Int, Int>>): MutableList<String> {
    val mutableInput = input.map { it.toCharArray() } // Convert each string to a CharArray

    for (row in mutableInput.indices) {
        for (col in mutableInput[row].indices) {
            if (mutableInput[row][col] == '@' && cordinatesToReplace.contains(Pair(row, col))) {
                mutableInput[row][col] = '.'
            }
        }
    }

    return mutableInput.map { it.concatToString() } as MutableList<String>
}
