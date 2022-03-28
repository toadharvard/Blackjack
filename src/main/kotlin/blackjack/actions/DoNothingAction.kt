package blackjack.actions

import blackjack.classes.State
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class DoNothingAction(override val game: IGame) : IStateAction {
    override fun execute(): State {
        return State.GameOver(game)
    }
}

