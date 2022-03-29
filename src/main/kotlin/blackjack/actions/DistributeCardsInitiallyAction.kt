package blackjack.actions

import blackjack.classes.Card
import blackjack.classes.Hand
import blackjack.classes.State
import blackjack.enums.CardFace
import blackjack.enums.CardSuit
import blackjack.interfaces.IGame
import blackjack.interfaces.IStateAction

class DistributeCardsInitiallyAction(override val game: IGame, val initialBet: Int) :
    IStateAction {
    override fun execute(): State {
        game.dealer.hands.add(Hand())
        game.dealer.hands[0].addCards(
            game.shoe.dealCard(isFaceUp = true),
            game.shoe.dealCard(isFaceUp = false)
        )

        game.dealer.activeHand = game.dealer.hands.first()

        game.player.hands.add(Hand())
        game.player.hands[0].addCards(
            game.shoe.dealCard(isFaceUp = true),
            game.shoe.dealCard(isFaceUp = true)
        )

        game.player.activeHand = game.player.hands.first()
        game.player.activeHand.bet = initialBet
        return State.PlayerTurn(game)
    }
}