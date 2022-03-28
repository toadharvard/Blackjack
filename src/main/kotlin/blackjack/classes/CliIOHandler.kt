package blackjack.classes

import blackjack.interfaces.ICard
import blackjack.interfaces.IOHandler
import blackjack.interfaces.IPlayer

class CliIOHandler : IOHandler {
    override fun print(msg: String) {
        println(msg)
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

    override fun chooseFromBetsInRange(msg: String, start: Int, stop: Int): Int {
        var choice: Int
        do {
            this.print(msg)
            this.print("$start..$stop")
            choice = (readLine() ?: "-1").toInt()
        } while (choice !in start..stop)

        return choice
    }

    override fun showCards(msg: String, cards: List<ICard>) {
        this.print(msg)
        for (card in cards) {
            if (card.isFaceUp)
                this.print(card.toString())
        }
    }
}