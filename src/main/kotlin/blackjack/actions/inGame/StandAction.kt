package blackjack.actions.inGame

import blackjack.interfaces.IAction

class StandAction : IAction {
    override val displayName: String
        get() = "Stand"

    override fun execute() {
    }
}