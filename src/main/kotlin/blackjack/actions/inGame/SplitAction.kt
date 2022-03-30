package blackjack.actions.inGame

import blackjack.classes.Hand
import blackjack.interfaces.IGame
import blackjack.interfaces.IInGameAction

class SplitAction(val game: IGame, val hand: Hand) : IInGameAction {
    override val displayName: String = "Split"

    override fun execute() {
        val newHand = hand.splitByLastCard()
        game.player.hands.add(newHand)
        HitAction(game, hand).execute()
        HitAction(game, newHand).execute()
        newHand.bet = hand.bet
    }
}