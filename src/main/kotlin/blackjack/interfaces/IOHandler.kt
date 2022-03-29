package blackjack.interfaces

import blackjack.classes.Hand


interface IOHandler {
    fun print(msg: String)
    fun showCards(msg: String, cards: List<ICard>)
    fun showHands(msg: String, hands: Array<Hand>)
    fun chooseFromPossibleActions(msg: String, vararg actionsNames: String): String
    fun chooseFromBetsInRange(msg: String, start: Int, stop: Int): Int
    fun chooseFromHands(msg: String, vararg hands: Hand): Hand
}