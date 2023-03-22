package wordsvirtuoso

import java.io.File

const val auto = false
const val debugCheckValid = false

fun main(args: Array<String>) {

    if (args.size != 2) exit("Error: Wrong number of arguments.")

    val wordsFileName = args[0]
    val candidatesFileName = args[1]

    val wordsFile = File(wordsFileName)
    if (!wordsFile.exists()) exit("Error: The words file $wordsFileName doesn't exist.")

    val candidates = File(candidatesFileName)
    if (!candidates.exists()) exit("Error: The candidate words file $candidatesFileName doesn't exist.")

    val words = wordsFile.readLines()
    if (words.any { !checkValid(it.lowercase()) }) {
        //Invalid words in the all words file
        val amountInvalid = words.count { !checkValid(it.lowercase()) }
        exit("Error: $amountInvalid invalid words were found in the $wordsFileName file.")
    }

    val candidateWords = candidates.readLines()
    if (candidateWords.any { !checkValid(it.lowercase()) }) {
        //Invalid words in the candidate words file
        val amountInvalid = candidateWords.count { !checkValid(it.lowercase()) }
        exit("Error: $amountInvalid invalid words were found in the $candidatesFileName file.")
    }

    if (words.containsAll(candidateWords)) {
        exit("Words Virtuoso")
    } else {
        val amountMissing = candidateWords.count { !words.contains(it.lowercase()) }
        exit("Error: $amountMissing candidate words are not included in the $wordsFileName file.")
    }


}

fun exit(exitMessage: String) {
    println(exitMessage)
    kotlin.system.exitProcess(1)
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