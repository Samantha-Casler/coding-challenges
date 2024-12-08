package dailyCode

import util.UtilFunctions

var totalCalibrationPart1 = 0L
var totalCalibrationPart2 = 0L

val ops = listOf<(Long, Long) -> Long>(Long::plus, Long::times)

fun main() {
    val input = UtilFunctions.readInputLines("Day7-2024-Input.txt")
    for (line in input) {
        val split = line.split(":")
        val result = split[0].toLong()
        val values = split[1].trim().split(" ").map { it.toLong() }
        day7Part1(result, values)
        day7Part2(result, values)

    }

    println("Total Calibration part 1: $totalCalibrationPart1")
    println("Total Calibration part 2: $totalCalibrationPart2")

    println("Merry Christmas!")
}

fun day7Part1(result: Long, values: List<Long>): Long {
    val sum = values.sum()
    val multi = values.reduce { acc, i -> acc * i }

    if (sum == result || multi == result) {
        totalCalibrationPart1 += result
    } else if (values.isOperationValid(result, values[0], 1, ops)) {
        totalCalibrationPart1 += result
    }

    return totalCalibrationPart1
}

fun day7Part2(result: Long, values: List<Long>): Long {
    val sum = values.sum()
    val multi = values.reduce { acc, i -> acc * i }

    if (sum == result || multi == result) {
        totalCalibrationPart2+= result
    } else if (values.isOperationValid(result, values[0], 1, ops + listOf { a, b -> a * b.offsetBy10() + b })) {
        totalCalibrationPart2 += result
    }

    return totalCalibrationPart2
}


fun List<Long>.isOperationValid(target: Long, current: Long, i: Int, ops: List<(Long, Long) -> Long>): Boolean {
    if (i == size) return current == target
    return ops.any { op -> isOperationValid(target, op(current, this[i]), i + 1, ops) }
}

fun Long.offsetBy10(): Int {
    var order = 1
    var num = this
    while (num != 0L) {
        num /= 10
        order *= 10
    }
    return order
}
