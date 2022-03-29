package blackjack.interfaces

import blackjack.classes.Hand

interface IBot : IOHandler {
    override fun print(msg: String) {}
    override fun showCards(msg: String, cards: List<ICard>) {}
    override fun showHands(msg: String, hands: Array<Hand>) {}
    override fun chooseFromHands(msg: String, vararg hands: Hand): Hand {
        return hands.first()
    }

    /**
     * first value of pair is player cards value sum
     * second value of pair is dealer cards value sum
     *
     * second value of map is your action name (e.g. Hit or Stand)
     **/
    val strategy: Map<Pair<Int, Int>, String>
    fun Map<Pair<Int, Int>, String>.addStrategy(
        playerScoreRange: IntRange,
        dealerScoreRange: IntRange,
        action: String
    ): Map<Pair<Int, Int>, String>

    fun run(n: Int, resetBalanceAfterGame: Boolean = true)
}