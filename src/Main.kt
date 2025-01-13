fun main() {
    val deck = StandardDeck()
    for (i in 1..1000000) {
        deck.shuffle()

        val cards1 = arrayListOf<PokerCard>()
        val cards2 = arrayListOf<PokerCard>()

        for (j in 1 .. 5) {
            cards1.add(PokerCard(deck.dealTopCard()))
            cards2.add(PokerCard(deck.dealTopCard()))
        }

        val hand1 = PokerHand(cards1)
        val hand2 = PokerHand(cards2)

        val rank1 = hand1.rank
        val rank2 = hand2.rank

        if ((rank1 > 7000) && (rank2 > 7000)) {
            if (rank1 > rank2) {
                println("$hand1 ($rank1) > $hand2 ($rank2)")
            } else if (rank1 < rank2) {
                println("$hand2 ($rank2) > $hand1 ($rank1)")
            } else {
                println("$hand1 ($rank1) == $hand2 ($rank2)")
            }
        }
    }
}