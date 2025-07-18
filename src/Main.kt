import com.cards.Rank
import com.cards.StandardCard
import com.cards.StandardDeck
import com.cards.Suit
import com.cards.poker.BestFiveCardPokerHandEvaluator

fun main() {
    val deck = StandardDeck()
    for (i in 1..1000) {
        deck.shuffle()

        val c1 = mutableSetOf<StandardCard>()

        for (j in 1 .. 7) {
            c1.add(deck.dealTopCard())
        }

        val bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(c1)
        val bestPokerHand = bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand
        println("$bestFiveCardPokerHandEvaluator -> $bestPokerHand (${bestPokerHand.rank})")

        println(StandardCard(Rank.SIX, Suit.HEARTS))


//        val cards1 = arrayListOf<PokerCard>()
//        val cards2 = arrayListOf<PokerCard>()
//
//        for (j in 1 .. 5) {
//            cards1.add(PokerCard(deck.dealTopCard()))
//            cards2.add(PokerCard(deck.dealTopCard()))
//        }
//
//        val hand1 = PokerHand(cards1)
//        val hand2 = PokerHand(cards2)
//
//        val rank1 = hand1.rank
//        val rank2 = hand2.rank
//
//        if ((rank1 < 10) && (rank2 < 10)) {
//            if (rank1 > rank2) {
//                println("$hand1 ($rank1) > $hand2 ($rank2)")
//            } else if (rank1 < rank2) {
//                println("$hand2 ($rank2) > $hand1 ($rank1)")
//            } else {
//                println("$hand1 ($rank1) == $hand2 ($rank2)")
//            }
//        }
    }
}