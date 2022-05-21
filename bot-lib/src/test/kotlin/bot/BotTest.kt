package bot

import blackjack.action.inGame.InGameAction
import blackjack.player.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BotTest {
    @Test
    fun `adding new strategy adds corresponds map keys and values`() {
        val b = object : Bot(Player(100)) {
            override val strategy: Map<Pair<Int, Int>, InGameAction> = mutableMapOf<Pair<Int, Int>, InGameAction>()
                .addStrategy(1..10, 1..10, InGameAction.STAND)
                .addStrategy(11..20, 5..5, InGameAction.HIT)
        }
        for (i in 1..10)
            for (j in 1..10)
                assertEquals(b.strategy[Pair(i, j)], InGameAction.STAND)

        for (i in 11..20)
            assertEquals(b.strategy[Pair(i, 5)], InGameAction.HIT)
    }
}