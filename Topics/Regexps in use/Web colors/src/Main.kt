fun main() {

    val exampleInput = "Web colors are colors used when displaying web pages. They can be represented as #123ABC: three pairs of hexadecimal digits. The first two digits are red, the two in the middle are green and the last two digits are blue. Example: #AABBCC, #ff0000."
    val DEBUG = false

    val text = if (DEBUG) exampleInput else readln()
    val regexColors = "#[0-9a-fA-F]{6}\\b".toRegex()
    // write your code here

    for (hex in regexColors.findAll(text)) println(hex.value)
}
