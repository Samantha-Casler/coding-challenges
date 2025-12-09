package dailyCode2025

import util.UtilFunctions

data class BatteryBanks(val battery: List<Int>, val largest: Int, val locations: List<Int>)

fun main() {
    val input = UtilFunctions.readInputLines("Day3-2025-Input.txt")

    val batteryBanks = input.mapNotNull { line ->
        BatteryBanks(line.chunked(1).map { it.toInt() }, line.maxOf { it.digitToInt() }, line.mapIndexedNotNull { index, c ->
            if (c.digitToInt() == line.maxOf { it.digitToInt() }) index else null
        })
    }

    day3Part1(batteryBanks)
    day3Part2(batteryBanks)

    println("Merry Christmas!")
}



private fun day3Part1(batteryBanks: List<BatteryBanks>) {
    var outputJoltage = 0

    for (bank in batteryBanks) {
        if (bank.locations.size == 1 && bank.locations[0] == bank.battery.size - 1) {
            val newBank = resetLargestLocations(bank)
            outputJoltage += calculateOutputJoltage(newBank)

        } else {

            if (bank.locations.size > 1) {
                outputJoltage += bank.largest * 11
            } else {
                outputJoltage += calculateOutputJoltage(bank)
            }
        }
    }
    println("Final output joltage for part 1: $outputJoltage")
}

private fun calculateOutputJoltage(batteryBank: BatteryBanks): Int {
    var currentLocation = batteryBank.locations[0] + 1
    var nextHighest = batteryBank.battery[batteryBank.locations[0] + 1]

    while (batteryBank.battery.size > currentLocation) {
        if (batteryBank.battery[currentLocation] > nextHighest) {
            nextHighest = batteryBank.battery[currentLocation]
            currentLocation = currentLocation + 1
        } else {
            currentLocation++
        }
    }

    val outputJoltage = batteryBank.largest.toString() + nextHighest.toString()

    return outputJoltage.toInt()
}

private fun resetLargestLocations(batteryBank: BatteryBanks): BatteryBanks {
    val newHighest = batteryBank.battery.filter { it != batteryBank.largest }.maxOf { it }

    return BatteryBanks(batteryBank.battery, newHighest, batteryBank.battery.mapIndexedNotNull { index, c ->
        if (c == newHighest) index else null
    })


}

private fun day3Part2(batteryBanks: List<BatteryBanks>) {
    var outputJoltage = 0L

    for (bank in batteryBanks) {
        outputJoltage += largest12DigitNumber(bank.battery).toLong()
    }

    println("Final output joltage for part 2: $outputJoltage")
}

private fun largest12DigitNumber(battery: List<Int>): String {
    val stack = mutableListOf<Int>()
    val toPick = 12
    val n = battery.size

    for ((i, digit) in battery.withIndex()) {
        while (
            stack.isNotEmpty() &&
            stack.size + (n - i) > toPick &&
            stack.last() < digit
        ) {
            stack.removeAt(stack.size - 1)
        }
        if (stack.size < toPick) {
            stack.add(digit)
        }
    }
    return stack.joinToString("")
}
