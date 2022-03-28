package blackjack.classes

import blackjack.interfaces.IHand
import blackjack.interfaces.IPlayer

class Player(override var balance: Int) : IPlayer {
    override val hand: IHand = Hand()
}