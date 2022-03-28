package blackjack.classes
import blackjack.enums.CardFace
import blackjack.interfaces.IPlayer

object ScoreCounter {
    fun recalculate(player: IPlayer): Int {
        var total = 0
        var aceCount = 0
        for (card in player.hand.cards) {
            if (card.face != CardFace.ACE)
                total += if (card.face.value > 10) 10 else card.face.value
            else
                aceCount++
        }
        return if (aceCount > 0 && total + aceCount < 12) total + aceCount + 10 else total + aceCount
    }
}