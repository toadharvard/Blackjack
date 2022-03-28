package blackjack.actions

import blackjack.actions.inGame.HitAction
import blackjack.actions.inGame.StandAction
import blackjack.classes.ScoreCounter
import blackjack.classes.State
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class PlayerTurnAction(override val game: IGame) :
    IStateAction {
    override fun execute(): State {
        game.ioHandler.print("Player turn:")
        if (ScoreCounter.recalculate(game.player) == 21)
            return State.WinnerSelection(game)

        do {
            game.ioHandler.showCards("Player cards:", game.player.hand.cards)
            game.ioHandler.showCards("Dealer cards:", game.dealer.hand.cards)

            val allowedActions =
                mutableListOf(HitAction(game, game.player), StandAction()).associateBy { it.displayName }

            val chosenActionDisplayName =
                game.ioHandler.chooseFromPossibleActions("What you want to do?", *allowedActions.keys.toTypedArray())
            val chosenAction =
                allowedActions[chosenActionDisplayName]!!

            chosenAction.execute()

            if (ScoreCounter.recalculate(game.player) > 21)
                return State.WinnerSelection(game)

        } while (chosenActionDisplayName != StandAction().displayName)
        return State.DealerTurn(game)
    }
}