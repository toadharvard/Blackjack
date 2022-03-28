package blackjack.actions

import blackjack.classes.State
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class DistributeCardsInitiallyAction(override val game: IGame) :
    IStateAction {
    override fun execute(): State {
        game.dealer.hand.addCard(game.shoe.dealCard(isFaceUp = true))
        game.dealer.hand.addCard(game.shoe.dealCard(isFaceUp = false))

        game.player.hand.addCard(game.shoe.dealCard(isFaceUp = true))
        game.player.hand.addCard(game.shoe.dealCard(isFaceUp = true))
        return State.PlayerTurn(game)
    }
}