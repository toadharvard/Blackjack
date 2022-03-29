package blackjack.actions

import blackjack.classes.ScoreCounter
import blackjack.classes.State
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class WinnerSelectionAction(override val game: IGame) :
    IStateAction {
    override fun execute(): State {
        if (game.player.hands.size == 0)
            return State.GameOver(game)
        val currentHand = game.player.hands.removeFirst()

        val playerScore = ScoreCounter.recalculate(game.player.activeHand)
        val dealerScore = ScoreCounter.recalculate(game.dealer.activeHand)
        val playerHasBlackjack =
            (game.player.activeHand.cards.size == 2 && game.player.hands.size == 1 && playerScore == 21)
        val dealerHasBlackjack =
            (game.dealer.activeHand.cards.size == 2 && game.dealer.hands.size == 1 && dealerScore == 21)

        if (playerScore > 21)
            return State.PlayerLooses(game, currentHand)

        if (dealerScore > 21) {
            return State.PlayerWon(game, currentHand)
        }

        if (playerHasBlackjack && dealerHasBlackjack || playerScore == dealerScore) {
            return State.PlayerDrew(game, currentHand)
        }

        if (playerHasBlackjack)
            return State.PlayerWonWithBlackjack(game, currentHand)

        if (playerScore > dealerScore)
            return State.PlayerWon(game, currentHand)

        return State.PlayerLooses(game, currentHand)
    }
}