package dailyCode2025

import util.UtilFunctions
import java.math.BigInteger

fun main() {
    val input = UtilFunctions.readInputLines("Day6-2025-Input.txt")

    val numberOfValueRows = input.size - 1
    val values = input.take(numberOfValueRows)
    val operations = input.last()


    day6Part1(values, operations)
    // day6Part2()

    println("Merry Christmas!")
}

private fun day6Part1(values: List<String>, operations: String) {
    val numberOfColumns = values.first().split(" ").filter { it.isNotBlank() }.size
    var grandTotal = 0.toBigInteger()

    for (i in 0 until numberOfColumns) {
        val numbers = values.map { row -> row.split(" ").filter { it.isNotBlank() }[i] }
        val operation = operations.split(" ").filter { it.isNotBlank() }[i]
        grandTotal += mathProblem(numbers, operation)
    }
    println("Grand Total Part1: $grandTotal")
}

private fun mathProblem(numbers: List<String>, operation: String): BigInteger {
    val parsedNumbers = numbers.mapNotNull { it.toBigInteger() }
    val result: BigInteger? = when (operation) {
        "+" -> parsedNumbers.reduce { acc, num -> acc + num }
        "*" -> parsedNumbers.reduce { acc, num -> acc * num }
        else -> null
    }

    return result?: 0.toBigInteger()
}

private fun day6Part2() {
}
