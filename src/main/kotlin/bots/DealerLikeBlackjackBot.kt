package bots

import blackjack.BlackjackGame
import blackjack.classes.Bot
import blackjack.classes.Dealer
import blackjack.classes.Player
import blackjack.classes.ScoreCounter
import blackjack.interfaces.IGame

class DealerLikeBlackjackBot(player: Player) : Bot(player) {
    override val strategy: Map<Pair<Int, Int>, String> = mapOf()

    override fun chooseFromPossibleActions(msg: String, vararg actionsNames: String): String {
        if (ScoreCounter.recalculate(player.activeHand) < 17)
            return "Hit"
        return "Stand"
    }

    override fun chooseFromBetsInRange(msg: String, start: Int, stop: Int): Int {
        return start
    }
}