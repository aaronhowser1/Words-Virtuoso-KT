fun findSerialNumberForGame(listGames: List<String>) {

    val wantedGame = readln()

    for (game in listGames) {
        val (gameName, serialXBox, serialPC) = game.split('@')
        if (wantedGame == gameName) {
            println("Code for Xbox - $serialXBox, for PC - $serialPC")
            break
        }
    }

}