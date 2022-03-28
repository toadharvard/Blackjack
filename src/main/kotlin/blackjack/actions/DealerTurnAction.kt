package blackjack.actions

import blackjack.actions.inGame.HitAction
import blackjack.classes.ScoreCounter
import blackjack.classes.State
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class DealerTurnAction(override val game: IGame) : IStateAction {
    override fun execute(): State {
        game.ioHandler.print("Dealer turn:")

        game.dealer.hand.flipAllCards(true)

        while (ScoreCounter.recalculate(game.dealer) < 17) {
            game.ioHandler.showCards("Player cards:", game.player.hand.cards)
            game.ioHandler.showCards("Dealer cards:", game.dealer.hand.cards)
            HitAction(game, game.dealer).execute()
        }

        return State.WinnerSelection(game)
    }
}