package blackjack.classes

import blackjack.interfaces.IDealer
import blackjack.interfaces.IHand

class Dealer : IDealer {
    override var balance = 0
    override val hand: IHand = Hand()
}