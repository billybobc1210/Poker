package com.cards.poker

class BestFiveCardPokerHandEvaluator(val cards: ArrayList<PokerCard>) {
    var bestFiveCardPokerHand: PokerHand = calculateBestFiveCardPokerHand()

    private fun calculateBestFiveCardPokerHand(): PokerHand {
        if ((cards.size < 5) || (cards.size > 7)) {
            throw Exception("Must provide 5-7 cards")
        }

        val cardSet = mutableSetOf<PokerCard>()

        cards.forEach { card ->
            cardSet.add(card)
        }

        if (cardSet.size < cards.size) {
            throw Exception("All cards in hand must be unique")
        }

        var bestFiveCardPokerHand: PokerHand? = null

        for (i in 0 .. cards.size-5) {
            for (j in i+1..cards.size-3) {
                for (k in j+1..cards.size-3) {
                    for (l in k+1..cards.size-2) {
                        for (m in l+1..cards.size-1) {
//                            println("$i $j $k $l $m")
                            val pokerHand = PokerHand(
                                arrayListOf(
                                    cards[i],
                                    cards[j],
                                    cards[k],
                                    cards[l],
                                    cards[m]
                                )
                            )

                            if ((bestFiveCardPokerHand == null) || (bestFiveCardPokerHand.rank < pokerHand.rank)) {
                                bestFiveCardPokerHand = pokerHand
                            }
                        }
                    }
                }
            }
        }

        bestFiveCardPokerHand?.let { result ->
            return result
        }

        throw Exception("Invalid 7-card poker hand")
    }

    override fun toString(): String {
        return cards.fold("") { acc, card ->
            val prefix = if (acc == "") "" else " "
            acc + "$prefix${card.abbreviation}"
        }
    }
}