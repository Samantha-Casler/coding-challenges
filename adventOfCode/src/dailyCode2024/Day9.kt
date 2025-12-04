package dailyCode2024

import util.UtilFunctions

fun main() {
    val input = UtilFunctions.readInput("Day9-2024-Input.txt")

    day9Part1(input)

    println("Merry Christmas!")
}

fun day9Part1(input: String) {
    val disk = input.trim().toCharArray()
    val diskLayout = mutableListOf<DiskLayout>()
    var idNumber = 0L

    for (i in 0 until disk.size) {
        if (i.rem(2) == 0) {
            diskLayout.add(DiskLayout(disk[i].toString().toInt(), Type.FILE, idNumber))
            idNumber++
        } else {
            diskLayout.add(DiskLayout(disk[i].toString().toInt(), Type.FREE_SPACE))
        }
    }
    val diskLayoutList = mutableListOf<Long>()
    for (i in 0 until diskLayout.size) {
        var j = 0
        if (diskLayout[i].type == Type.FILE) {
            while (j < diskLayout[i].length) {
                diskLayoutList.add(diskLayout[i].idNumber)
                j++
            }
        } else {
            while (j < diskLayout[i].length) {
                diskLayoutList.add(-1)
                j++
            }
        }
    }

    var i = 0
    val newDiskLayout = mutableListOf<Long>()
    var size = diskLayoutList.size
    while (diskLayoutList.size != newDiskLayout.size) {
        if (diskLayoutList[i] == -1L) {
            while (diskLayoutList[diskLayoutList.lastIndex] == -1L) {
                diskLayoutList.removeLast()
                size--
            }
            newDiskLayout.add(diskLayoutList[diskLayoutList.lastIndex])
            diskLayoutList.removeLast()
            size--
        } else {
            newDiskLayout.add(diskLayoutList[i])
        }
        i++
    }

    var sum = 0L
    for (i in 0 until newDiskLayout.size) {
        sum += newDiskLayout[i] * i
    }
    println("Sum: $sum")
}

private class DiskLayout(val length: Int, val type: Type, val idNumber: Long = -1L)

enum class Type {
    FILE, FREE_SPACE
}
