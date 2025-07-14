package com.cards.poker

class BestFiveCardPokerHandEvaluator(val cards: Set<PokerCard>) {
    var bestFiveCardPokerHand: PokerHand = calculateBestFiveCardPokerHand()

    private fun calculateBestFiveCardPokerHand(): PokerHand {
        if ((cards.size < 5) || (cards.size > 7)) {
            throw Exception("Must provide 5-7 cards")
        }

        val cardList = cards.map { it }

        var bestFiveCardPokerHand: PokerHand? = null

        for (i in 0 .. cardList.size-5) {
            for (j in i+1..cardList.size-3) {
                for (k in j+1..cardList.size-3) {
                    for (l in k+1..cardList.size-2) {
                        for (m in l+1..cardList.size-1) {
//                            println("$i $j $k $l $m")
                            val pokerHand = PokerHand(
                                setOf(
                                    cardList[i],
                                    cardList[j],
                                    cardList[k],
                                    cardList[l],
                                    cardList[m]
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