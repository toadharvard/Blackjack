package blackjack.actions

import blackjack.classes.State
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class ChooseBetSizeAction(override val game: IGame) :
    IStateAction {
    override fun execute(): State {
        if (game.player.balance < 10) {
            return NotEnoughMoneyWarningAction(game).execute()
        }

        game.bet = game.ioHandler.chooseFromBetsInRange("Choose from possible bets", 10, game.player.balance)
        game.player.balance -= game.bet
        return State.InitialCardDistribution(game)
    }
}