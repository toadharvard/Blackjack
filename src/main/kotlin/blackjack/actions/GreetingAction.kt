package blackjack.actions

import blackjack.classes.State
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class GreetingAction(override val game: IGame) : IStateAction {
    override fun execute(): State {
        game.ioHandler.print("Welcome to our Blackjack game!")
        return State.AnteBet(game)
    }
}