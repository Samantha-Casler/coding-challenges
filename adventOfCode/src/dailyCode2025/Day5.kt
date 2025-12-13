package dailyCode2025

import util.UtilFunctions
import kotlin.collections.mutableListOf

fun main() {
    val input = UtilFunctions.readInputLines("Day5-2025-Input.txt")

    val (ranges, ids) = splitInput(input)

    day5Part1(ranges, ids)
    day5Part2(ranges)

    println("Merry Christmas!")
}

private fun splitInput(input: List<String>): Pair<List<LongRange>, List<Long>> {
    val ranges = mutableListOf<LongRange>()
    val ids = mutableListOf<Long>()

    var isParsingIds = false
    for (line in input) {
        if (line.isBlank()) {
            isParsingIds = true
            continue
        }

        if (!isParsingIds) {
            val (start, end) = line.split("-").map { it.toLong() }
            ranges.add(start..end)
        } else {
            ids.add(line.toLong())
        }
    }

    return ranges to ids
}

private fun day5Part1(ranges: List<LongRange>, ids: List<Long>) {

    var count = 0
    for (id in ids) {
        for (range in ranges) {
            if (id in range) {
                count++
                break
            }
        }
    }

    println("Count of IDs within ranges (Part 1): $count")
}

private fun day5Part2(ranges: List<LongRange>) {
    val sortedRanges = ranges.sortedBy { it.first }
    var count = 0L

    val mergedRanges = mutableListOf<LongRange>()
    for (range in sortedRanges) {
        if (mergedRanges.isEmpty() || mergedRanges.last().last < range.first - 1) {
            mergedRanges.add(range)
        } else {
            val lastRange = mergedRanges.removeAt(mergedRanges.size - 1)
            mergedRanges.add(lastRange.first..maxOf(lastRange.last, range.last))
        }
    }
    for (range in mergedRanges) {
        count += (range.endInclusive - range.start + 1)
    }

    println("Count of unique IDs within ranges (Part 2): $count")
}
