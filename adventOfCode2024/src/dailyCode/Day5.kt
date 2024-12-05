package dailyCode

import util.UtilFunctions

fun main() {
    val input = UtilFunctions.readInputLines("Day5-2024-Input.txt")

    val rules = mutableMapOf<Int, MutableList<Int>>()
    // take everything up to a certain point
    input.takeWhile { it.isNotEmpty() }.forEach {
            val instructions = it.split("|").map(String::toInt)
            rules.getOrPut(instructions[0]) { mutableListOf() }.add(instructions[1])
    }

    // drop everything before a certain point
    val updates = input.dropWhile { it.isNotEmpty() }.drop(1).map { it.split(",").map(String::toInt) }

    val incorrectUpdates = day5Part1(rules, updates)
    day5Part2(rules, incorrectUpdates)

    println("Merry Christmas!")
}

fun day5Part1(rules: MutableMap<Int, MutableList<Int>>, updates: List<List<Int>>): MutableList<List<Int>> {
    val correctUpdates = mutableListOf<List<Int>>()
    val incorrectUpdates = mutableListOf<List<Int>>()
    for (update in updates) {
        val seen = mutableListOf<Int>()
        val isOrdered = update.reversed().none { n ->
            (n in seen).also { seen.addAll(rules[n] ?: emptyList()) }
        }
        if (isOrdered) {
            correctUpdates.add(update)
        } else {
            incorrectUpdates.add(update)
        }
    }

    var total = 0
    for (update in correctUpdates) {
        total += update[update.size/2]
    }
    println("Total correct updates: $total")

    return incorrectUpdates
}

fun day5Part2(rules: MutableMap<Int, MutableList<Int>>, updates: MutableList<List<Int>>) {
    var total = 0
    for (update in updates) {
        val newOrder = mutableListOf<Int>()
        update.forEachIndexed { i, n ->
            // check the rules for the current number,
            val violation = rules[n]?.mapNotNull { newOrder.indexOf(it).takeIf { it != -1 } }?.minOrNull()
            // if a number in the new list matches a rule, insert the current number in its place (shift the rest)
            newOrder.add(violation ?: i, n)
        }
        total += newOrder[newOrder.size/2]
    }
    println("Total incorrect updates: $total")
}

