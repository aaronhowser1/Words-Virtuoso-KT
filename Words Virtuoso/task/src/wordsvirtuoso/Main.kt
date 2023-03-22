package wordsvirtuoso

fun main() {
    println("Input a 5-letter string:")

    val input = readln().lowercase()

    val output =
        if (!lengthIs(input, 5)) "The input isn't a 5-letter string."
        else if (allEnglish(input)) "The input has invalid characters."
        else if (duplicateLetters(input)) "The input has duplicate letters."
        else "The input is a valid string."

    println(output)

}

fun lengthIs(input: String, length: Int): Boolean = input.length == length

fun allEnglish(input: String): Boolean = input.any { it !in 'a'..'z' }

fun duplicateLetters(input: String): Boolean {
    for (char in input) {
        if (input.count {char == it} > 1) return true
    }
    return false
}