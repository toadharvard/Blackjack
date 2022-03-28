package blackjack.classes

import blackjack.actions.*
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

sealed class State(val game: IGame) {
    abstract val action: IStateAction

    class Start(game: IGame) : State(game) {
        override val action = GreetingAction(game)
    }

    class AnteBet(game: IGame) : State(game) {
        override val action = ChooseBetSizeAction(game)
    }

    class InitialCardDistribution(game: IGame) : State(game) {
        override val action = DistributeCardsInitiallyAction(game)
    }

    class DealerTurn(game: IGame) : State(game) {
        override val action = DealerTurnAction(game)
    }

    class PlayerTurn(game: IGame) : State(game) {
        override val action = PlayerTurnAction(game)
    }

    class WinnerSelection(game: IGame) : State(game) {
        override val action = WinnerSelectionAction(game)
    }

    class GameOver(game: IGame) : State(game) {
        override val action = DoNothingAction(game)
    }


    override fun equals(other: Any?): Boolean {
        return when (other) {
            is State -> this::class.simpleName == other::class.simpleName
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = game.hashCode()
        result = 31 * result + action.hashCode()
        return result
    }
}