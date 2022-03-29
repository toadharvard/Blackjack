package blackjack.interfaces

interface IInGameAction {
    val displayName: String
        get() = this::class.java.simpleName

    fun execute()
}