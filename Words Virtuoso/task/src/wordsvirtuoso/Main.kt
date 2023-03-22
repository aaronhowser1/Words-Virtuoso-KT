package wordsvirtuoso

import java.io.File

const val auto = false
const val debugCheckValid = false

fun main() {

    println("Input the words file:")

    val input = if (!auto) readln() else "Words Virtuoso/task/src/wordsvirtuoso/words.txt"
    val wordsFile = File(input)

    if (!wordsFile.exists()) {
        println("Error: The words file $input doesn't exist.")
        kotlin.system.exitProcess(1)
    }

    var invalidWords = 0
    for (word in wordsFile.readLines()) if (!checkValid(word.lowercase())) invalidWords++

    if (invalidWords > 0) {
        println("Warning: $invalidWords invalid words were found in the $input file.")
    } else {
        println("All words are valid!")
    }

}

fun checkValid(input: String): Boolean {
    fun lengthIs(length: Int): Boolean = input.length == length

    fun allEnglish(): Boolean = !input.any { it !in 'a'..'z' }

    fun duplicateLetters(): Boolean {
        for (char in input) {
            if (input.count {char == it} > 1) return true
        }
        return false
    }

    val returnValue = lengthIs(5) && allEnglish() && !duplicateLetters()

    if (!returnValue && debugCheckValid) println("$input is invalid: ${lengthIs(5)} ${allEnglish()} ${!duplicateLetters()}")

    return returnValue

}