package util

import java.io.File

class UtilFunctions {
    companion object {
        fun readInputLines(fileName:String): List<String> {
            try {
                return File(fileName).readLines()
            } catch (e: Exception) {
                throw IllegalStateException(e)
            }
        }

        fun readInput(fileName:String): String {
            try {
                return File(fileName).readText().trim()
            } catch (e: Exception) {
                throw IllegalStateException(e)
            }
        }

    }
}
