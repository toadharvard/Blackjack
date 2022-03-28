package blackjack.actions

import blackjack.classes.ScoreCounter
import blackjack.classes.State
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class WinnerSelectionAction(override val game: IGame) :
    IStateAction {
    override fun execute(): State {
        game.ioHandler.print("Winner selection:")

        game.dealer.hand.flipAllCards(true)

        val playerScore = ScoreCounter.recalculate(game.player)
        val dealerScore = ScoreCounter.recalculate(game.dealer)
        val playerHasBlackjack = (game.player.hand.cards.size == 2 && playerScore == 21)
        val dealerHasBlackjack = (game.dealer.hand.cards.size == 2 && dealerScore == 21)

        game.ioHandler.showCards("Player cards:", game.player.hand.cards)
        game.ioHandler.print("Player score: $playerScore, has blackjack: $playerHasBlackjack")

        game.ioHandler.showCards("Dealer cards:", game.dealer.hand.cards)
        game.ioHandler.print("Dealer score: $dealerScore, has blackjack: $dealerHasBlackjack")


        if (playerScore > 21) {
            game.ioHandler.print("You lose!")
            return State.GameOver(game)
        }

        if (dealerScore > 21) {
            game.ioHandler.print("You won! Balance is doubled")
            game.player.balance += 2 * game.bet
            return State.GameOver(game)
        }

        if (playerHasBlackjack && dealerHasBlackjack || playerScore == dealerScore) {
            game.ioHandler.print("Draw! Your bet is returned")
            game.player.balance += game.bet
            game.bet = 0
            return State.GameOver(game)
        }

        if (playerHasBlackjack) {
            game.ioHandler.print("You won! Earning is 3/2 of the bet.")
            game.player.balance +=  game.bet + game.bet * 3/2
            return State.GameOver(game)
        }
        if (playerScore > dealerScore){
            game.ioHandler.print("You won! Balance is doubled")
            game.player.balance +=  game.bet + game.bet * 3/2
            return State.GameOver(game)
        }

        game.ioHandler.print("You lose!")
        return State.GameOver(game)
    }
}