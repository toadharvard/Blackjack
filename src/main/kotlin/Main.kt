import blackjack.BlackjackGame
import blackjack.classes.Dealer
import blackjack.classes.Player
import blackjack.classes.ScoreCounter
import blackjack.interfaces.ICard
import blackjack.interfaces.IGame
import blackjack.interfaces.IOHandler
import blackjack.interfaces.IPlayer


abstract class Bot(startBalance: Int) : IOHandler {
    protected val player = Player(startBalance)
    protected abstract var game: IGame
    abstract val balance: Int
    final override fun print(msg: String) {}
    final override fun showCards(msg: String, cards: List<ICard>) {}

    fun run(n: Int) {
        for (i in 1..n) {
            game = BlackjackGame(Dealer(), player, this)
            game.run()
        }
    }
}

class DealerLikeBot(startBalance: Int) : Bot(startBalance) {
    override var game: IGame = BlackjackGame(Dealer(), player, this)
    override val balance: Int
        get() = player.balance

    override fun chooseFromPossibleActions(msg: String, vararg actionsNames: String): String {
        if (ScoreCounter.recalculate(player) < 17)
            return "Hit"
        return "Stand"
    }

    override fun chooseFromBetsInRange(msg: String, start: Int, stop: Int): Int {
        return start
    }
}

fun main() {
    val b = DealerLikeBot(1000)
    b.run(100)
    print(b.balance)
}