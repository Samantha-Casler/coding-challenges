package util

import java.io.File

class UtilFunctions {
    companion object {
        fun readInput(fileName:String): List<String> {
            try {
                return File(fileName).readLines()
            } catch (e: Exception) {
                throw IllegalStateException(e)
            }
        }

    }
}
