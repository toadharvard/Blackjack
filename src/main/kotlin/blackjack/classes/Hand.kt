package blackjack.classes

import blackjack.interfaces.ICard
import blackjack.interfaces.IHand

class Hand(vararg cards: ICard) : IHand {
    private val _cards: MutableList<ICard> = cards.toMutableList()
    override val cards: List<ICard>
        get() = _cards

    override fun dealCard(): ICard {
        return _cards.removeLast()
    }

    override fun addCard(card: ICard) {
        _cards.add(card)
    }

    override fun flipAllCards(isFaceUp: Boolean) {
        for (card in _cards)
            card.isFaceUp = isFaceUp
    }

    override fun clear() {
        _cards.clear()
    }
}