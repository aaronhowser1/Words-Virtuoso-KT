fun setStringToGreen(str: String): String {
    val green = "\u001b[32m"
    val reset = "\u001B[0m"

    return "$green$str$reset"

}