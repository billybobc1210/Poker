package com.cards.poker

class SevenCardPokerHand(val cards: ArrayList<PokerCard>) {
    var bestFiveCardPokerHand: PokerHand = calculateBestFiverCardPokerHand()

    private fun calculateBestFiverCardPokerHand(): PokerHand {
        if (cards.size != 7) {
            throw Exception("Not a 7 card poker hand")
        }

        val cardSet = mutableSetOf<PokerCard>()

        cards.forEach { card ->
            cardSet.add(card)
        }

        if (cardSet.size < 5) {
            throw Exception("All 7 cards in hand must be unique")
        }

        var bestFiveCardPokerHand: PokerHand? = null

        for (i in 0 .. 2) {
            for (j in i+1..3) {
                for (k in j+1..4) {
                    for (l in k+1..5) {
                        for (m in l+1..6) {
                            val pokerHand = PokerHand(
                                arrayListOf(
                                    cards[i],
                                    cards[j],
                                    cards[k],
                                    cards[l],
                                    cards[m]
                                )
                            )

                            if (bestFiveCardPokerHand == null) {
                                bestFiveCardPokerHand = pokerHand
                            } else if (bestFiveCardPokerHand.rank < pokerHand.rank) {
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