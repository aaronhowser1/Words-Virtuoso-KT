// You can experiment here, it won’t be checked

val exampleInput = "Web colors are colors used when displaying web pages. They can be represented as #123ABC: three pairs of hexadecimal digits. The first two digits are red, the two in the middle are green and the last two digits are blue. Example: #AABBCC, #ff0000."

fun main(args: Array<String>) {
    val DEBUG = true
    val input = if (DEBUG) exampleInput else readln()

    val colorRegex = Regex("#(0-9|A-F)${6}")

    for (hex in colorRegex.findAll(input)) println(hex)

}
