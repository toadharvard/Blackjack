package blackjack.interfaces

interface IHand {
    val cards: List<ICard>
    fun dealCard(): ICard
    fun addCard(card: ICard)
    fun flipAllCards(isFaceUp: Boolean)
    fun clear()
}