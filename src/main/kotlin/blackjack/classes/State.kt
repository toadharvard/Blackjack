package blackjack.classes

import blackjack.actions.*
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

sealed class State(val game: IGame) {
    abstract fun doBeforeActionExecution()
    abstract val action: IStateAction
    abstract fun doAfterActionExecution()

    class Start(game: IGame) : State(game) {
        override fun doBeforeActionExecution() {
            game.ioHandler.print("Welcome to Blackjack game!")
            game.ioHandler.print("You have ${game.player.balance}$!")
        }

        override val action = ToNextStateAction(game, AnteBet(game))
        override fun doAfterActionExecution() {}
    }

    class AnteBet(game: IGame) : State(game) {
        override fun doBeforeActionExecution() {}
        override val action = AskForBetAction(game)
        override fun doAfterActionExecution() {}
    }

    class NotEnoughMoney(game: IGame) : State(game) {
        override fun doBeforeActionExecution() {
            game.ioHandler.print("Not enough money to play. You should have at least 10$")
        }

        override val action = ToNextStateAction(game, GameOver(game))
        override fun doAfterActionExecution() {}
    }

    class InitialCardDistribution(game: IGame, val initialBet: Int) : State(game) {
        override fun doBeforeActionExecution() {
            game.player.hands.clear()
            game.dealer.hands.clear()
            game.dealer.activeHand.clear()
            game.player.activeHand.clear()
        }

        override val action = DistributeCardsInitiallyAction(game, initialBet)
        override fun doAfterActionExecution() {}
    }

    class DealerTurn(game: IGame) : State(game) {
        override fun doBeforeActionExecution() {
            game.dealer.activeHand.flipAllCards(true)
            game.ioHandler.print("--- Cards before dealer move: ")
            game.ioHandler.showHands("Player hands: ", game.player.hands.toTypedArray())
            game.ioHandler.showCards("Player cards: ", game.player.activeHand.cards)
            game.ioHandler.showCards("Dealer cards: ", game.dealer.activeHand.cards)
        }

        override val action = DealerTurnAction(game)
        override fun doAfterActionExecution() {
            game.ioHandler.print("--- Cards after dealer move: ")
            game.ioHandler.showCards("Player cards: ", game.player.activeHand.cards)
            game.ioHandler.showCards("Dealer cards: ", game.dealer.activeHand.cards)
        }
    }

    class PlayerTurn(game: IGame) : State(game) {
        override fun doBeforeActionExecution() {
            game.ioHandler.print("--- Cards before player move: ")
            game.ioHandler.showHands("Player hands: ", game.player.hands.toTypedArray())
            game.ioHandler.showCards("Player cards: ", game.player.activeHand.cards)
            game.ioHandler.showCards("Dealer cards: ", game.dealer.activeHand.cards)
        }

        override val action = PlayerTurnAction(game)
        override fun doAfterActionExecution() {
            game.ioHandler.print("--- Cards after player move: ")
            game.ioHandler.showHands("Player hands: ", game.player.hands.toTypedArray())
            game.ioHandler.showCards("Player cards: ", game.player.activeHand.cards)
            game.ioHandler.showCards("Dealer cards: ", game.dealer.activeHand.cards)
        }
    }

    class WinnerSelection(game: IGame) : State(game) {
        override fun doBeforeActionExecution() {

        }

        override val action = WinnerSelectionAction(game)
        override fun doAfterActionExecution() {

        }
    }

    class PlayerWon(game: IGame, private val currentHand: Hand) : State(game) {
        override fun doBeforeActionExecution() {
            game.ioHandler.print("Player won!")
        }

        override val action = ToNextStateAction(game, WinnerSelection(game))
        override fun doAfterActionExecution() {
            game.player.balance += 2 * currentHand.bet
        }
    }

    class PlayerDrew(game: IGame, private val currentHand: Hand) : State(game) {
        override fun doBeforeActionExecution() {
            game.ioHandler.print("Player drew. Not bad.")
        }

        override val action = ToNextStateAction(game, WinnerSelection(game))
        override fun doAfterActionExecution() {
            game.player.balance += currentHand.bet
        }
    }

    class PlayerWonWithBlackjack(game: IGame, private val currentHand: Hand) : State(game) {
        override fun doBeforeActionExecution() {
            game.ioHandler.print("Player won with blackjack!")
        }

        override val action = ToNextStateAction(game, WinnerSelection(game))
        override fun doAfterActionExecution() {
            game.player.balance += currentHand.bet + currentHand.bet * 3 / 2
        }
    }

    class PlayerLooses(game: IGame, private val currentHand: Hand) : State(game) {
        override fun doBeforeActionExecution() {
            game.ioHandler.print("Player looses :(")
        }

        override val action = ToNextStateAction(game, WinnerSelection(game))
        override fun doAfterActionExecution() {
        }
    }


    class GameOver(game: IGame) : State(game) {
        override fun doBeforeActionExecution() {
            game.ioHandler.print("Game over!")
        }

        override val action = ToNextStateAction(game, Start(game))
        override fun doAfterActionExecution() {
        }
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is State -> this::class.java.simpleName == other::class.java.simpleName
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = game.hashCode()
        result = 31 * result + action.hashCode()
        return result
    }
}