package dailyCode2025

import java.math.BigInteger
import util.UtilFunctions

data class Ranges(val first: BigInteger, val last: BigInteger)

fun main() {
    val input = UtilFunctions.readInput("Day2-2025-Input.txt")

    val ranges = input.split(",").map {
        val (first, last) = it.split("-").map { num ->
                BigInteger(num)
        }
        Ranges(first, last)
    }

    day2Part1(ranges)
    day2Part2(ranges)

    println("Merry Christmas!")
}



private fun day2Part1(ranges: List<Ranges>) {
    val invalidIds = mutableListOf<BigInteger>()

    for (range in ranges) {
        var current = range.first
        while (current <= range.last) {
            val currentStr = current.toString()
            val mid = currentStr.length / 2

            if (currentStr.length % 2 == 0 && currentStr.substring(0, mid) == currentStr.substring(mid)) {
                invalidIds.add(current)
            }

            current = current.add(BigInteger.ONE)
        }
    }

    val sumOfInvalidIds = invalidIds.fold(BigInteger.ZERO) { acc, id -> acc + id }
    println("Sum of Invalid IDs: $sumOfInvalidIds")
}

private fun day2Part2(ranges: List<Ranges>) {
    val invalidIds = mutableListOf<BigInteger>()

    for (range in ranges) {
        var current = range.first
        while (current <= range.last) {
            val currentStr = current.toString()
            val length = currentStr.length

            for (repeatLength in 1..length / 2) {
                if (length % repeatLength == 0) {
                    val segment = currentStr.substring(0, repeatLength)
                    if (segment.repeat(length / repeatLength) == currentStr) {
                        invalidIds.add(current)
                        break
                    }
                }
            }

            current = current.add(BigInteger.ONE)
        }
    }

    val sumOfInvalidIds = invalidIds.fold(BigInteger.ZERO) { acc, id -> acc + id }
    println("Sum of Invalid IDs: $sumOfInvalidIds")
}
