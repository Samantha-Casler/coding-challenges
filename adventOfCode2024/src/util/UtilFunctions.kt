package util

import java.nio.file.Files
import java.nio.file.Path

class UtilFunctions {
    companion object {
        fun readInput(fileName:String): String {
            try {
                return Files.readString(Path.of(fileName).toAbsolutePath())
            } catch (e: Exception) {
                throw IllegalStateException(e)
            }
        }

    }
}
