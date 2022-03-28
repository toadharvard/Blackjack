package blackjack.actions.inGame

import blackjack.interfaces.IAction
import blackjack.interfaces.IGame
import blackjack.interfaces.IPlayer

class HitAction(val game: IGame, val player: IPlayer) : IAction {
    override val displayName: String
        get() = "Hit"

    override fun execute() {
        player.hand.addCard(game.shoe.dealCard(isFaceUp = true))
    }
}