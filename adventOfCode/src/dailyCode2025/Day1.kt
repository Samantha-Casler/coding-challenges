package dailyCode2025

import util.UtilFunctions


data class Movement(val direction: String, val amount: Int)

fun main() {
    val input = UtilFunctions.readInputLines("Day1-2025-Input.txt")

    val movements = input.mapNotNull { line ->
        val match = Regex("([LR])(\\d+)").matchEntire(line)
        match?.let {
            val (direction, amount) = it.destructured
            Movement(direction, amount.toInt())
        }
    }

    day1Part1(movements)
    day1Part2(movements)

    println("Merry Christmas!")
}



private fun day1Part1(movements: List<Movement>) {
    var position = 50 // Starting position
    var zeroCount = 0 // Counter for landing on 0

    movements.forEach { movement ->
        when (movement.direction) {
            "L" -> {
                position -= movement.amount
                while (position < 0) {
                    position += 100
                }
            }
            "R" -> {
                position += movement.amount
                while (position >= 100) {
                    position -= 100
                }
            }
        }
        if (position == 0) {
            zeroCount++
        }
    }

    println("Landed on 0 a total of $zeroCount times")
}

private fun day1Part2(movements: List<Movement>) {
    var position = 50 // Starting position
    var passedZeroCount = 0 // Counter for passing/landing on 0 during a movement (excluding starting-on-0)

    movements.forEach { movement ->
        when (movement.direction) {
            "L" -> {
                // For left moves we move from position downwards. We count k in 1..amount where (position - k) mod 100 == 0.
                // If starting position == 0 we should not count the starting position, so only count multiples of 100 in the amount.
                passedZeroCount += if (position == 0) {
                    movement.amount / 100
                } else {
                    // The first k that lands on 0 is k = position (if amount >= position), then every +100 after that.
                    (movement.amount + (100 - position)) / 100
                }

                // Update position with proper wrapping
                position = (position - movement.amount) % 100
                if (position < 0) position += 100
            }
            "R" -> {
                // For right moves we move from position upwards. We count k in 1..amount where (position + k) mod 100 == 0,
                // which is simply floor((position + amount) / 100).
                passedZeroCount += (position + movement.amount) / 100

                // Update position with wrapping
                position = (position + movement.amount) % 100
            }
        }
    }

    println("(Part2) Passed/Landed on 0 a total of $passedZeroCount times")
}
