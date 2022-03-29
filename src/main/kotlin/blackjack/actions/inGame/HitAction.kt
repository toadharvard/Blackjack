package blackjack.actions.inGame

import blackjack.classes.Hand
import blackjack.interfaces.IGame
import blackjack.interfaces.IInGameAction
import blackjack.interfaces.IPlayer

class HitAction(val game: IGame, val activeHand: Hand) : IInGameAction {
    override val displayName: String
        get() = "Hit"

    override fun execute() {
        activeHand.addCard(game.shoe.dealCard(isFaceUp = true))
    }
}