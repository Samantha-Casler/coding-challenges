package dailyCode

import util.UtilFunctions

class Day2 {

    companion object {
        private var removedCount = 0
        fun day2() {
            val input: String = UtilFunctions.readInput("Day2-2024-Input.txt")

            val lines = input.lines()
            var countSafePart1 = 0
            var countSafePart2 = 0
            for (line in lines) {
                val numbers = line.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (numbers.isEmpty()) {
                    break
                }
                removedCount = 0
                day2Part1(numbers.toMutableList()).let { if (it) countSafePart1++ }
                day2Part2(numbers.toMutableList()).let { if (it) countSafePart2++ }
                println("Safe reports part 2: $countSafePart2") // less than 425 not 545

            }

            println("Safe reports part 1: $countSafePart1")
            println("Safe reports part 2: $countSafePart2") // less than 425 not 545 not 422 not 355

            println("Merry Christmas!")
        }

        private fun day2Part2(list1: MutableList<String>): Boolean {
            return false
        }

        private fun day2Part1(list1: MutableList<String>): Boolean {
            if (isIncreasing(list1)) {
                if (differingAmtValid(list1, true)) {
                    return true
                }
            } else if (isDecreasing(list1)) {
                if (differingAmtValid(list1, false)) {
                    return true
                }
            }

            return false
        }

        private fun isIncreasing(list1: MutableList<String>): Boolean {
            for (i in 0 until list1.size - 1) {
                if (list1[i].toInt() > list1[i + 1].toInt()) {
                    return false
                }
            }
            return true
        }

        private fun isDecreasing(list1: MutableList<String>): Boolean {
            for (i in 0 until list1.size - 1) {
                if (list1[i].toInt() < list1[i + 1].toInt()) {
                    return false
                }
            }
            return true
        }

        private fun differingAmtValid(list1: MutableList<String>, isIncreasing: Boolean): Boolean {
            for (i in 0 until list1.size - 1) {
                if (isIncreasing) {
                    if (list1[i].toInt() + 1 != list1[i + 1].toInt() && list1[i].toInt() + 2 != list1[i + 1].toInt() && list1[i].toInt() + 3 != list1[i + 1].toInt()) {
                        return false
                    }
                } else {
                    if (list1[i].toInt() - 1 != list1[i + 1].toInt() && list1[i].toInt() - 2 != list1[i + 1].toInt() && list1[i].toInt() - 3 != list1[i + 1].toInt()) {
                        return false
                    }
                }
            }
            return true
        }

    }
}
