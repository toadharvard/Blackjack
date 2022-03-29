package blackjack.classes

import blackjack.interfaces.ICard
import blackjack.interfaces.IOHandler


class CliIOHandler : IOHandler {
    override fun print(msg: String) {
        println(msg)
    }

    override fun showCards(msg: String, cards: List<ICard>) {
        this.print(msg)
        for (card in cards) {
            if (card.isFaceUp)
                this.print(card.toString())
        }
    }

    override fun showHands(msg: String, hands: Array<Hand>) {
        this.print(msg)
        var i = 1
        for (hand in hands) {
            if (!hand.blocked) {
                this.print("$i. ${hand.cards}")
                i++
            }
        }
    }

    override fun chooseFromPossibleActions(msg: String, vararg actionsNames: String): String {
        var choice: String
        do {
            this.print(msg)
            actionsNames.forEach { this.print(it) }
            choice = readLine() ?: ""
        } while (choice !in actionsNames)

        return choice
    }

    override fun chooseFromHands(msg: String, vararg hands: Hand): Hand {
        if (hands.size == 1) return hands.first()
        var choice: Int
        do {
            this.print(msg)
            this.print("1..${hands.size}")
            choice = readLine()?.toInt() ?: -1
        } while (choice !in 1..hands.size)

        return hands[choice - 1]
    }

    override fun chooseFromBetsInRange(msg: String, start: Int, stop: Int): Int {
        var choice: Int
        do {
            this.print(msg)
            this.print("$start..$stop")
            choice = (readLine() ?: "-1").toInt()
        } while (choice !in start..stop)

        return choice
    }
}