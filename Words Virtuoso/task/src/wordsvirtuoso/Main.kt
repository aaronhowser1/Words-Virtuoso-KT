package wordsvirtuoso

import java.io.File

const val debugCheckValid = false

// Launch arguments are:
// "Words Virtuoso/task/src/wordsvirtuoso/words.txt" "Words Virtuoso/task/src/wordsvirtuoso/candidates.txt"

fun main(args: Array<String>) {

    if (args.size != 2) exit("Error: Wrong number of arguments.")

    val wordsFileName = args[0]
    val candidatesFileName = args[1]

    verifyFiles(wordsFileName, candidatesFileName)
    val wordsFile = File(wordsFileName)
    val candidatesFile = File(candidatesFileName)

    val words = mutableSetOf<String>()
    for (line in wordsFile.readLines()) words.add(line.lowercase())

    val candidateWords = mutableSetOf<String>()
    for (line in candidatesFile.readLines()) candidateWords.add(line.lowercase())

    val randomCandidate = candidateWords.random()

    while (true) {
        println("Input a 5-letter word")
        val input = readln().lowercase()

        if (input == "exit") exit("The game is over.")
        if (input == randomCandidate) exit("Correct!")
        if (!input.validLength()) println("The input isn't a 5-letter word.")
            else if (!input.allEnglish()) println("One or more letters of the input aren't valid.")
            else if (input.duplicateLetters()) println("The input has duplicate letters.")
            else if (!words.contains(input)) println("The input word isn't included in my words list.")
            else println(getClue(randomCandidate, input))
    }

}

fun getClue(secretWord: String, checkedWord: String): String {
    fun checkChar(index: Int): Char {
        val checkedChar = checkedWord[index]
        // If it doesn't contain the char, _
        return if (!secretWord.contains(checkedChar)) '_'
            // If it does contain the char AT THE SAME INDEX, return capitalized
            else if (secretWord[index] == checkedChar) checkedChar.uppercaseChar()
            // If it does contain the char, but not at the same index. return lowercase
            else checkedChar.lowercaseChar()
    }
    var output = ""
    for (i in 0..4) {
        output += checkChar(i)
    }
    return output
}

fun verifyFiles(wordsFileName: String, candidatesFileName: String) {
    val wordsFile = File(wordsFileName)
    val candidatesFile = File(candidatesFileName)

    if (!wordsFile.exists()) exit("Error: The words file $wordsFileName doesn't exist.")
    if (!candidatesFile.exists()) exit("Error: The candidate words file $candidatesFileName doesn't exist.")


    val words = mutableSetOf<String>()
    for (line in wordsFile.readLines()) words.add(line.lowercase())
    if (words.any { !checkValid(it.lowercase()) }) {
        //Invalid words in the all words file
        val amountInvalid = words.count { !checkValid(it.lowercase()) }
        exit("Error: $amountInvalid invalid words were found in the $wordsFileName file.")
    }

    val candidateWords = mutableSetOf<String>()
    for (line in candidatesFile.readLines()) candidateWords.add(line.lowercase())
    if (candidateWords.any { !checkValid(it.lowercase()) }) {
        //Invalid words in the candidate words file
        val amountInvalid = candidateWords.count { !checkValid(it.lowercase()) }
        exit("Error: $amountInvalid invalid words were found in the $candidatesFileName file.")
    }

    if (words.containsAll(candidateWords)) {
        println("Words Virtuoso")
    } else {
        val amountMissing = candidateWords.count { !words.contains(it.lowercase()) }
        exit("Error: $amountMissing candidate words are not included in the $wordsFileName file.")
    }
}

fun exit(exitMessage: String) {
    println(exitMessage)
    kotlin.system.exitProcess(1)
}

fun String.validLength(): Boolean = this.length == 5
fun String.allEnglish(): Boolean = !this.lowercase().any {it !in 'a'..'z'}
fun String.duplicateLetters(): Boolean {
    for (char in this) {
        if (this.count {char == it} > 1) return true
    }
    return false
}

fun checkValid(input: String): Boolean {
    val returnValue = input.validLength() && input.allEnglish() && !input.duplicateLetters()

    if (!returnValue && debugCheckValid) println("$input is invalid: ${input.validLength()} ${input.allEnglish()} ${!input.duplicateLetters()}")

    return returnValue
}