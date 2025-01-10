//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val foo = false

    val items = listOf(2,4,6,8,10,11,12,14)

    val evenItems =  items.takeWhile({ item ->
        item % 2 == 0
    }).filter({
        it > 6
    })
    var i: Int = 0
    var b: Boolean = true
    while (i < 100 && b) {
        var item = items[i++]
    }
    println(evenItems.joinToString(" "))

    for (i in 1..1000000) {
        var deck: StandardDeck = StandardDeck()
        deck.shuffle()

        val cards1 = arrayListOf<Card>()
        for (j in 1 .. 5) {
            cards1.add(deck.dealTopCard())
        }
        val hand1 = PokerHand(cards1)

        val cards2 = arrayListOf<Card>()
        for (j in 1 .. 5) {
            cards2.add(deck.dealTopCard())
        }
        val hand2 = PokerHand(cards2)

        val rank1 = hand1.rank
        val rank2 = hand2.rank

        if ((rank1 > 7000) && (rank2 > 7000)) {
            if (rank1 > rank2) {
                println(hand1.toString() + " ($rank1) > " + hand2.toString() + " ($rank2)")
            } else if (rank1 < rank2) {
                println(hand2.toString() + " ($rank2) > " + hand1.toString() + " ($rank1)")
            } else {
                println(hand1.toString() + " ($rank1) == " + hand2.toString() + " ($rank2)")
            }
        }
    }
}