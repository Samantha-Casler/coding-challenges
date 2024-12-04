package dailyCode

import util.UtilFunctions

    fun main() {
        val input = UtilFunctions.readInput("Day3-2024-Input.txt")

        day3Part1(input)
        day3Part2(input)

        println("Merry Christmas!")
    }

    fun day3Part1(input: String) {
        val matching = Regex("""mul\((\d+),(\d+)\)""").findAll(input)
        val uncorrupted = matching.sumOf { it.groups[1]!!.value.toInt() * it.groups[2]!!.value.toInt()}
        println("Sum of all matches part 1: $uncorrupted")
    }

    var execute = true
    fun day3Part2(input: String) {
        val matching = Regex("""(do\(\)|don't\(\)|mul\((\d+),(\d+)\))""").findAll(input)
        val uncorrupted = matching.sumOf {
            when (it.groups[1]!!.value) {
                "do()" -> 0.also { execute = true }
                "don't()" -> 0.also { execute = false }
                else -> {
                    if (execute) it.groups[2]!!.value.toInt() * it.groups[3]!!.value.toInt() else 0}
            }
        }
        println("Sum of all matches part 2: $uncorrupted")
    }
