package blackjack.interfaces

interface IGame {
    val ioHandler: IOHandler
    val dealer: IDealer
    val player: IPlayer
    var bet: Int
    val shoe: IShoe
    fun run()
}