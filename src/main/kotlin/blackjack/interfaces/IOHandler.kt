package blackjack.interfaces


interface IOHandler {
    fun print(msg: String)
    fun showCards(msg: String, cards: List<ICard>)
    fun chooseFromPossibleActions(msg: String, vararg actionsNames: String): String
    fun chooseFromBetsInRange(msg: String, start: Int, stop: Int): Int
}