package blackjack.actions

import blackjack.classes.State
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class NotEnoughMoneyWarningAction(override val game: IGame) : IStateAction {
    override fun execute(): State {
        game.ioHandler.print("Not enough money for play, minimum is 10")
        return State.GameOver(game)
    }
}