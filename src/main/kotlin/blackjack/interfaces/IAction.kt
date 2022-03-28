package blackjack.interfaces

interface IAction {
    val displayName: String
        get() = this::class.java.simpleName

    fun execute()
}