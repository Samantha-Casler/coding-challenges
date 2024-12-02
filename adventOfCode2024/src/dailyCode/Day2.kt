package dailyCode

import util.UtilFunctions
import kotlin.math.abs
import kotlin.math.sign

class Day2 {

    companion object {
        fun day2() {
            val input = UtilFunctions.readInput("Day2-2024-Input.txt").map { it.split(" ").map { it.toInt() } }
            var countSafePart1 = 0
            for (line in input) {
                day2Part1(line).let { if (it) countSafePart1++ }
            }

            println("Safe reports part 1: $countSafePart1")
            println("Safe reports part 2: ${input.count{ it.day2Part2()}}")

            println("Merry Christmas!")
        }


        private fun List<Int>.day2Part2(): Boolean {
            return indices.any { index ->
                this.toMutableList().also { it.removeAt(index) }.isSafe()
            }
        }

        private fun List<Int>.isSafe(): Boolean {
            val pairs = zipWithNext().map { it.first - it.second }
            val incOrDec = pairs.map { it.sign }.toSet()
            // very useful! incOrDec tells us if they are all increasing or decreasing, if more than one sign then
            // we know it is not valid
            return incOrDec.size == 1 && pairs.all { abs(it) in 1..3 }
        }

        private fun day2Part1(list1: List<Int>): Boolean {
            if (isIncreasing(list1)) {
                if (differingAmtValid(list1, true)) {
                    return true
                }
            } else {
                if (differingAmtValid(list1, false)) {
                    return true
                }
            }
            return false
        }

        private fun isIncreasing(list1: List<Int>): Boolean {
            for (i in 0 until list1.size - 1) {
                if (list1[i] > list1[i + 1]) {
                    return false
                }
            }
            return true
        }

        private fun differingAmtValid(list1: List<Int>, isIncreasing: Boolean): Boolean {
            for (i in 0 until list1.size - 1) {
                if (isIncreasing) {
                    if (list1[i]+ 1 != list1[i + 1] && list1[i] + 2 != list1[i + 1] && list1[i] + 3 != list1[i + 1]) {
                        return false
                    }
                } else {
                    if (list1[i] - 1 != list1[i + 1] && list1[i] - 2 != list1[i + 1] && list1[i] - 3 != list1[i + 1]) {
                        return false
                    }
                }
            }
            return true
        }

    }
}
