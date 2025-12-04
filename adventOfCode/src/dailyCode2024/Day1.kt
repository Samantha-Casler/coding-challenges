package dailyCode2024

import util.UtilFunctions
import kotlin.time.measureTime


fun main() {
        val input = UtilFunctions.readInputLines("Day1-2024-Input.txt")

        val list1: MutableList<String> = ArrayList()
        val list2: MutableList<String> = ArrayList()
        for (line in input) {
            val numbers = line.split(" {3}".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (numbers.size != 2) {
                break
            }
            list1.add(numbers[0])
            list2.add(numbers[1])
        }

        println(measureTime { day1Part2(list1, list2) })
        println(measureTime { day1Part1(list1, list2) })

        println("Merry Christmas!")
    }

    private fun day1Part2(list1: MutableList<String>, list2: MutableList<String>) {
        var i = 0
        var sum = 0
        while (i < list1.size) {
            val num1 = list1[i].toInt()
            var count = 0
            var j = 0
            while (j < list2.size) {
                if (num1 == list2[j].toInt()) {
                    count++
                }
                j++
            }
            sum += (count * num1)
            i++
        }
        println("Sum part2: $sum")
    }

    private fun day1Part1(list1: MutableList<String>, list2: MutableList<String>) {
        list1.sort()
        list2.sort()

        var i = 0
        var sum = 0
        while (i < list1.size) {
            val num1 = list1[i].toInt()
            val num2 = list2[i].toInt()
            if (num1 > num2) {
                val difference = num1 - num2
                sum += difference
                i++
            } else if (num2 > num1) {
                val difference = num2 - num1
                sum += difference
                i++
            } else {
                sum += 0
                i++
            }
        }
        println("Sum part1: $sum")
    }


