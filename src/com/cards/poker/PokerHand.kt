package com.cards.poker

import com.cards.Rank
import com.cards.Suit

class PokerHand(val cards: ArrayList<PokerCard>) {
    val rank: Int = calculateHandRank()
    private var isSuited: Boolean = false

    private fun calculateHandRank(): Int {
        if (cards.size != 5) {
            throw Exception("Not a standard 5 card poker hand")
        }

        val cardSet = mutableSetOf<PokerCard>()
        val suitSet = mutableSetOf<Suit>()

        cards.forEach { card ->
            cardSet.add(card)
            suitSet.add(card.suit)
        }

        if (cardSet.size < 5) {
            throw Exception("All 5 cards in hand must be unique")
        }

        isSuited = suitSet.size == 1

        val highCardHandRankMapKey = getHandRankMapKey(useHighValues = true)

        handRankMap[highCardHandRankMapKey]?.let { handRank ->
            return handRank
        }

        val lowCardHandRankMapKey = getHandRankMapKey(useHighValues = false)

        handRankMap[lowCardHandRankMapKey]?.let { handRank ->
            return handRank
        }

        throw Exception("Invalid poker hand")
    }

    private fun getHandRankMapKey(useHighValues: Boolean = true): String {
        val cardRankValues = arrayListOf<Int>()

        cards.forEach { card ->
            cardRankValues.add(if (useHighValues) card.highValue else card.lowValue)
        }

        cardRankValues.sortDescending()

        return cardRankValues.joinToString(" ") + if (isSuited) SUITED_SUFFIX else ""
    }

    public fun isHighCardHand(): Boolean {
        return rank >= MIN_HIGH_CARD_RANK && rank <= MAX_HIGH_CARD_RANK
    }

    public fun isPair(): Boolean {
        return rank >= MIN_PAIR_RANK && rank <= MAX_PAIR_RANK
    }

    public fun isTwoPair(): Boolean {
        return rank >= MIN_TWO_PAIR_RANK && rank <= MAX_TWO_PAIR_RANK
    }

    public fun isThreeOfAKind(): Boolean {
        return rank >= MIN_THREE_OF_A_KIND_RANK && rank <= MAX_THREE_OF_A_KIND_RANK
    }

    public fun isStraight(): Boolean {
        return rank >= MIN_STRAIGHT_RANK && rank <= MAX_STRAIGHT_RANK
    }

    public fun isFlush(): Boolean {
        return rank >= MIN_FLUSH_RANK && rank <= MAX_FLUSH_RANK
    }

    public fun isFullHouse(): Boolean {
        return rank >= MIN_FULL_HOUSE_RANK && rank <= MAX_FULL_HOUSE_RANK
    }

    public fun isFourOfAKind(): Boolean {
        return rank >= MIN_FOUR_OF_A_KIND_RANK && rank <= MAX_FOUR_OF_A_KIND_RANK
    }

    public fun isStraightFlush(): Boolean {
        return rank >= MIN_STRAIGHT_FLUSH_RANK && rank <= MAX_STRAIGHT_FLUSH_RANK
    }

    override fun toString(): String {
        return cards.fold("") { acc, card ->
            val prefix = if (acc == "") "" else " "
            acc + "$prefix${card.abbreviation}"
        }
    }

    companion object {
        const val NUM_HIGH_CARD_AND_FLUSH_RANKS: Int = 1277
        const val NUM_HIGH_CARD_RANKS: Int = NUM_HIGH_CARD_AND_FLUSH_RANKS
        const val MIN_HIGH_CARD_RANK: Int = 0
        const val MAX_HIGH_CARD_RANK: Int = MIN_HIGH_CARD_RANK + NUM_HIGH_CARD_RANKS - 1
        const val NUM_PAIR_RANKS: Int = 2860
        const val MIN_PAIR_RANK: Int = MAX_HIGH_CARD_RANK + 1
        const val MAX_PAIR_RANK: Int = MIN_PAIR_RANK + NUM_PAIR_RANKS - 1
        const val NUM_TWO_PAIR_RANKS = 858
        const val MIN_TWO_PAIR_RANK: Int = MAX_PAIR_RANK + 1
        const val MAX_TWO_PAIR_RANK: Int = MIN_TWO_PAIR_RANK + NUM_TWO_PAIR_RANKS - 1
        const val NUM_THREE_OF_A_KIND_RANKS: Int = 858
        const val MIN_THREE_OF_A_KIND_RANK: Int = MAX_TWO_PAIR_RANK + 1
        const val MAX_THREE_OF_A_KIND_RANK: Int = MIN_THREE_OF_A_KIND_RANK + NUM_THREE_OF_A_KIND_RANKS - 1
        const val NUM_STRAIGHT_AND_STRAIGHT_FLUSH_RANKS: Int = 10
        const val NUM_STRAIGHT_RANKS: Int = NUM_STRAIGHT_AND_STRAIGHT_FLUSH_RANKS
        const val MIN_STRAIGHT_RANK: Int = MAX_THREE_OF_A_KIND_RANK + 1
        const val MAX_STRAIGHT_RANK: Int = MIN_STRAIGHT_RANK + NUM_STRAIGHT_RANKS - 1
        const val NUM_FLUSH_RANKS: Int = NUM_HIGH_CARD_AND_FLUSH_RANKS
        const val MIN_FLUSH_RANK: Int = MAX_STRAIGHT_RANK + 1
        const val MAX_FLUSH_RANK: Int = MIN_FLUSH_RANK + NUM_FLUSH_RANKS - 1
        const val NUM_FOUR_OF_A_KIND_AND_FULL_HOUSE_RANKS: Int = 156
        const val NUM_FULL_HOUSE_RANKS: Int = NUM_FOUR_OF_A_KIND_AND_FULL_HOUSE_RANKS
        const val MIN_FULL_HOUSE_RANK: Int = MAX_FLUSH_RANK + 1
        const val MAX_FULL_HOUSE_RANK: Int = MIN_FULL_HOUSE_RANK + NUM_FULL_HOUSE_RANKS - 1
        const val NUM_FOUR_OF_A_KIND_RANKS = NUM_FOUR_OF_A_KIND_AND_FULL_HOUSE_RANKS
        const val MIN_FOUR_OF_A_KIND_RANK: Int = MAX_FULL_HOUSE_RANK + 1
        const val MAX_FOUR_OF_A_KIND_RANK: Int = MIN_FOUR_OF_A_KIND_RANK + NUM_FOUR_OF_A_KIND_RANKS - 1
        const val NUM_STRAIGHT_FLUSH_RANKS: Int = NUM_STRAIGHT_AND_STRAIGHT_FLUSH_RANKS
        const val MIN_STRAIGHT_FLUSH_RANK: Int = MAX_FOUR_OF_A_KIND_RANK + 1
        const val MAX_STRAIGHT_FLUSH_RANK: Int = MIN_STRAIGHT_FLUSH_RANK + NUM_STRAIGHT_FLUSH_RANKS - 1
        const val SUITED_SUFFIX: String = " SUITED"

        private val handRankMap = mutableMapOf<String, Int>()

        private fun initHandRankMapForFourOfAKindsAndFullHouses() {
            var fourOfAKindHandRank: Int = MAX_FOUR_OF_A_KIND_RANK
            var fullHouseHandRank: Int = MAX_FULL_HOUSE_RANK

            for (majorCardRankValue in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                for (minorCardRankValue in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                    if (majorCardRankValue == minorCardRankValue) {
                        continue
                    }

                    val fourOfAKindRankValues = arrayListOf(
                        majorCardRankValue,
                        majorCardRankValue,
                        majorCardRankValue,
                        majorCardRankValue,
                        minorCardRankValue
                    )
                    fourOfAKindRankValues.sortDescending()
                    val fourOfAkindKey = fourOfAKindRankValues.joinToString(" ")

                    val fullHouseRankValues = arrayListOf(
                        majorCardRankValue,
                        majorCardRankValue,
                        majorCardRankValue,
                        minorCardRankValue,
                        minorCardRankValue
                    )
                    fullHouseRankValues.sortDescending()
                    val fullHouseKey = fullHouseRankValues.joinToString(" ")

                    handRankMap[fourOfAkindKey] = fourOfAKindHandRank--
                    handRankMap[fullHouseKey] = fullHouseHandRank--
                }
            }
        }

        private fun initHandRankMapForThreeOfAKinds() {
            var threeOfAKindHandRank: Int = MAX_THREE_OF_A_KIND_RANK

            for (threeOfAKindCardRankValue in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                for (topKickerCardRankValue in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.THREE)) {
                    for (bottomKickerCardRankValue in topKickerCardRankValue - 1 downTo PokerCard.getHighValue(Rank.TWO)) {
                        if ((threeOfAKindCardRankValue == topKickerCardRankValue) ||
                            (threeOfAKindCardRankValue == bottomKickerCardRankValue) ||
                            (topKickerCardRankValue == bottomKickerCardRankValue)) {
                            continue
                        }

                        val rankValues = arrayListOf(
                            threeOfAKindCardRankValue,
                            threeOfAKindCardRankValue,
                            threeOfAKindCardRankValue,
                            topKickerCardRankValue,
                            bottomKickerCardRankValue
                        )
                        rankValues.sortDescending()
                        val key = rankValues.joinToString(" ")

                        handRankMap[key] = threeOfAKindHandRank--
                    }
                }
            }
        }

        private fun initHandRankMapForTwoPairs() {
            var twoPairHandRank: Int = MAX_TWO_PAIR_RANK

            for (topPairCardRankValue in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.THREE)) {
                for (bottomPairCardRankValue in topPairCardRankValue - 1 downTo PokerCard.getHighValue(Rank.TWO)) {
                    for (kickerCardRankValue in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                        if ((topPairCardRankValue == bottomPairCardRankValue) ||
                            (topPairCardRankValue == kickerCardRankValue) ||
                            (bottomPairCardRankValue == kickerCardRankValue)) {
                            continue
                        }

                        val rankValues = arrayListOf(
                            topPairCardRankValue,
                            topPairCardRankValue,
                            bottomPairCardRankValue,
                            bottomPairCardRankValue,
                            kickerCardRankValue
                        )
                        rankValues.sortDescending()
                        val key = rankValues.joinToString(" ")

                        handRankMap[key] = twoPairHandRank--
                    }
                }
            }
        }

        private fun initHandRankMapForPairs() {
            var pairHandRank: Int = MAX_PAIR_RANK

            for (pairCardRankValue in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                for (topKickerCardRankValue in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.FOUR)) {
                    for (middleKickerCardRankValue in topKickerCardRankValue - 1 downTo PokerCard.getHighValue(Rank.THREE)) {
                        for (bottomKickerCardRankValue in middleKickerCardRankValue - 1 downTo PokerCard.getHighValue(Rank.TWO)) {
                            if ((pairCardRankValue == topKickerCardRankValue) ||
                                (pairCardRankValue == middleKickerCardRankValue) ||
                                (pairCardRankValue == bottomKickerCardRankValue) ||
                                (topKickerCardRankValue == middleKickerCardRankValue) ||
                                (topKickerCardRankValue == bottomKickerCardRankValue) ||
                                (middleKickerCardRankValue == bottomKickerCardRankValue)) {
                                continue
                            }

                            val rankValues = arrayListOf(
                                pairCardRankValue,
                                pairCardRankValue,
                                topKickerCardRankValue,
                                middleKickerCardRankValue,
                                bottomKickerCardRankValue
                            )
                            rankValues.sortDescending()
                            val key = rankValues.joinToString(" ")

                            handRankMap[key] = pairHandRank--
                        }
                    }
                }
            }
        }

        private fun initHandRankMapForHighCardsFlushesStraightsAndStraightFlushes() {
            var highCardHandRank: Int = MAX_HIGH_CARD_RANK
            var flushHandRank: Int = MAX_FLUSH_RANK
            var straightHandRank: Int = MAX_STRAIGHT_RANK
            var straightFlushHandRank: Int = MAX_STRAIGHT_FLUSH_RANK

            val falseFiveHighStraightHandRankKey = arrayListOf(
                PokerCard.getHighValue(Rank.ACE),
                PokerCard.getHighValue(Rank.FIVE),
                PokerCard.getHighValue(Rank.FOUR),
                PokerCard.getHighValue(Rank.THREE),
                PokerCard.getHighValue(Rank.TWO),
            ).joinToString(" ")

            val trueFiveHighStraightHandRankKey = arrayListOf(
                PokerCard.getHighValue(Rank.FIVE),
                PokerCard.getHighValue(Rank.FOUR),
                PokerCard.getHighValue(Rank.THREE),
                PokerCard.getHighValue(Rank.TWO),
                PokerCard.getLowValue(Rank.ACE),
            ).joinToString(" ")

            for (highCardRankValue in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.SIX)) {
                for (secondHighestCardRankValue in highCardRankValue - 1 downTo PokerCard.getHighValue(Rank.FIVE)) {
                    for (thirdHighestCardRankValue in secondHighestCardRankValue - 1 downTo PokerCard.getHighValue(Rank.FOUR)) {
                        for (fourthHighestCardRankValue in thirdHighestCardRankValue - 1 downTo PokerCard.getHighValue(Rank.THREE)) {
                            for (fifthHighestCardRankValue in fourthHighestCardRankValue - 1 downTo PokerCard.getHighValue((Rank.TWO))) {
                                val key: String = arrayListOf(
                                    highCardRankValue,
                                    secondHighestCardRankValue,
                                    thirdHighestCardRankValue,
                                    fourthHighestCardRankValue,
                                    fifthHighestCardRankValue
                                ).joinToString(" ")

                                if (key == falseFiveHighStraightHandRankKey) {
                                    continue
                                }

                                if (highCardRankValue - fifthHighestCardRankValue == 4) {
                                    handRankMap[key] = straightHandRank--
                                    handRankMap["$key$SUITED_SUFFIX"] = straightFlushHandRank--
                                } else {
                                    handRankMap[key] = highCardHandRank--
                                    handRankMap["$key$SUITED_SUFFIX"] = flushHandRank--
                                }
                            }
                        }
                    }
                }
            }

            handRankMap[trueFiveHighStraightHandRankKey] = MIN_STRAIGHT_RANK
            handRankMap["$trueFiveHighStraightHandRankKey$SUITED_SUFFIX"] = MIN_STRAIGHT_FLUSH_RANK
        }

        init {
            initHandRankMapForFourOfAKindsAndFullHouses()
            initHandRankMapForThreeOfAKinds()
            initHandRankMapForTwoPairs()
            initHandRankMapForPairs()
            initHandRankMapForHighCardsFlushesStraightsAndStraightFlushes()
//
//            val rankToHandMap = handRankMap.entries.associateBy({ it.value }) { it.key }
//
//            (0..7461).forEach { rank ->
//                println(rankToHandMap[rank] + " -> $rank")
//            }
        }
    }
}