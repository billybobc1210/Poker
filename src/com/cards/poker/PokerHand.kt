package com.cards.poker

import com.cards.Rank
import com.cards.StandardCard

class PokerHand(val cards: Set<StandardCard>) {
    val rank: Int = calculateHandRank()

    init {
        require(cards.size == 5) { "Not a standard 5 card poker hand" }
    }

    private fun calculateHandRank(): Int {
        val isSuited = cards.map { card -> card.suit }.toSet().size == 1

        val highCardHandRankMapKey = buildHandRankMapKey(ArrayList(cards.map { card -> highValue(card.rank) }), isSuited)

        handRankMap[highCardHandRankMapKey]?.let { result ->
            return result
        }

        val lowCardHandRankMapKey = buildHandRankMapKey(ArrayList(cards.map { card -> lowValue(card.rank) }), isSuited)

        handRankMap[lowCardHandRankMapKey]?.let { result ->
            return result
        }

        throw Exception("Invalid poker hand")
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
        const val NUM_HIGH_CARD_AND_FLUSH_RANKS: Int = 1277 // CHOOSE(13,5) - 10
        const val NUM_HIGH_CARD_RANKS: Int = NUM_HIGH_CARD_AND_FLUSH_RANKS
        const val MIN_HIGH_CARD_RANK: Int = 0
        const val MAX_HIGH_CARD_RANK: Int = MIN_HIGH_CARD_RANK + NUM_HIGH_CARD_RANKS - 1
        const val NUM_PAIR_RANKS: Int = 2860 // CHOOSE(13,1) * CHOOSE(12,3)
        const val MIN_PAIR_RANK: Int = MAX_HIGH_CARD_RANK + 1
        const val MAX_PAIR_RANK: Int = MIN_PAIR_RANK + NUM_PAIR_RANKS - 1
        const val NUM_TWO_PAIR_RANKS = 858 // CHOOSE(13,2) * CHOOSE(11,1)
        const val MIN_TWO_PAIR_RANK: Int = MAX_PAIR_RANK + 1
        const val MAX_TWO_PAIR_RANK: Int = MIN_TWO_PAIR_RANK + NUM_TWO_PAIR_RANKS - 1
        const val NUM_THREE_OF_A_KIND_RANKS: Int = 858 // CHOOSE(13,1) * CHOOSE(12,2)
        const val MIN_THREE_OF_A_KIND_RANK: Int = MAX_TWO_PAIR_RANK + 1
        const val MAX_THREE_OF_A_KIND_RANK: Int = MIN_THREE_OF_A_KIND_RANK + NUM_THREE_OF_A_KIND_RANKS - 1
        const val NUM_STRAIGHT_AND_STRAIGHT_FLUSH_RANKS: Int = 10 // 5-high through Ace-high
        const val NUM_STRAIGHT_RANKS: Int = NUM_STRAIGHT_AND_STRAIGHT_FLUSH_RANKS
        const val MIN_STRAIGHT_RANK: Int = MAX_THREE_OF_A_KIND_RANK + 1
        const val MAX_STRAIGHT_RANK: Int = MIN_STRAIGHT_RANK + NUM_STRAIGHT_RANKS - 1
        const val NUM_FLUSH_RANKS: Int = NUM_HIGH_CARD_AND_FLUSH_RANKS
        const val MIN_FLUSH_RANK: Int = MAX_STRAIGHT_RANK + 1
        const val MAX_FLUSH_RANK: Int = MIN_FLUSH_RANK + NUM_FLUSH_RANKS - 1
        const val NUM_FOUR_OF_A_KIND_AND_FULL_HOUSE_RANKS: Int = 156 // CHOOSE(13,1) * CHOOSE(12,1)
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
        const val FALSE_FIVE_HIGH_STRAIGHT_HAND_RANK_KEY = "14 5 4 3 2"
        const val TRUE_FIVE_HIGH_STRAIGHT_HAND_RANK_KEY = "5 4 3 2 1"
        const val TRUE_FIVE_HIGH_STRAIGHT_FLUSH_HAND_RANK_KEY = "$TRUE_FIVE_HIGH_STRAIGHT_HAND_RANK_KEY$SUITED_SUFFIX"

        private val handRankMap = mutableMapOf<String, Int>()

        private fun buildHandRankMapKey(cardRankValues: ArrayList<Int>, isSuited: Boolean): String {
            cardRankValues.sortDescending()

            return cardRankValues.joinToString(" ") + if (isSuited) SUITED_SUFFIX else ""
        }

        private fun initHandRankMapForFourOfAKindsAndFullHouses() {
            var fourOfAKindHandRank: Int = MAX_FOUR_OF_A_KIND_RANK
            var fullHouseHandRank: Int = MAX_FULL_HOUSE_RANK

            for (majorCardRankValue in highValue(Rank.ACE) downTo highValue(Rank.TWO)) {
                for (minorCardRankValue in highValue(Rank.ACE) downTo highValue(Rank.TWO)) {
                    if (majorCardRankValue == minorCardRankValue) {
                        continue
                    }

                    val fourOfAkindKey = buildHandRankMapKey(
                        arrayListOf(
                            majorCardRankValue,
                            majorCardRankValue,
                            majorCardRankValue,
                            majorCardRankValue,
                            minorCardRankValue
                        ),
                        false
                    )

                    val fullHouseKey = buildHandRankMapKey(
                        arrayListOf(
                            majorCardRankValue,
                            majorCardRankValue,
                            majorCardRankValue,
                            minorCardRankValue,
                            minorCardRankValue
                        ),
                        false
                    )

                    handRankMap[fourOfAkindKey] = fourOfAKindHandRank--
                    handRankMap[fullHouseKey] = fullHouseHandRank--
                }
            }
        }

        private fun initHandRankMapForThreeOfAKinds() {
            var threeOfAKindHandRank: Int = MAX_THREE_OF_A_KIND_RANK

            for (threeOfAKindCardRankValue in highValue(Rank.ACE) downTo highValue(Rank.TWO)) {
                for (topKickerCardRankValue in highValue(Rank.ACE) downTo highValue(Rank.THREE)) {
                    for (bottomKickerCardRankValue in topKickerCardRankValue - 1 downTo highValue(Rank.TWO)) {
                        if ((threeOfAKindCardRankValue == topKickerCardRankValue) ||
                            (threeOfAKindCardRankValue == bottomKickerCardRankValue)) {
                            continue
                        }

                        val key = buildHandRankMapKey(
                            arrayListOf(
                                threeOfAKindCardRankValue,
                                threeOfAKindCardRankValue,
                                threeOfAKindCardRankValue,
                                topKickerCardRankValue,
                                bottomKickerCardRankValue
                            ),
                            false
                        )

                        handRankMap[key] = threeOfAKindHandRank--
                    }
                }
            }
        }

        private fun initHandRankMapForTwoPairs() {
            var twoPairHandRank: Int = MAX_TWO_PAIR_RANK

            for (topPairCardRankValue in highValue(Rank.ACE) downTo highValue(Rank.THREE)) {
                for (bottomPairCardRankValue in topPairCardRankValue - 1 downTo highValue(Rank.TWO)) {
                    for (kickerCardRankValue in highValue(Rank.ACE) downTo highValue(Rank.TWO)) {
                        if ((topPairCardRankValue == kickerCardRankValue) ||
                            (bottomPairCardRankValue == kickerCardRankValue)) {
                            continue
                        }

                        val key = buildHandRankMapKey(
                            arrayListOf(
                                topPairCardRankValue,
                                topPairCardRankValue,
                                bottomPairCardRankValue,
                                bottomPairCardRankValue,
                                kickerCardRankValue
                            ),
                            false
                        )

                        handRankMap[key] = twoPairHandRank--
                    }
                }
            }
        }

        private fun initHandRankMapForPairs() {
            var pairHandRank: Int = MAX_PAIR_RANK

            for (pairCardRankValue in highValue(Rank.ACE) downTo highValue(Rank.TWO)) {
                for (topKickerCardRankValue in highValue(Rank.ACE) downTo highValue(Rank.FOUR)) {
                    for (middleKickerCardRankValue in topKickerCardRankValue - 1 downTo highValue(Rank.THREE)) {
                        for (bottomKickerCardRankValue in middleKickerCardRankValue - 1 downTo highValue(Rank.TWO)) {
                            if ((pairCardRankValue == topKickerCardRankValue) ||
                                (pairCardRankValue == middleKickerCardRankValue) ||
                                (pairCardRankValue == bottomKickerCardRankValue)) {
                                continue
                            }

                            val key = buildHandRankMapKey(
                                arrayListOf(
                                    pairCardRankValue,
                                    pairCardRankValue,
                                    topKickerCardRankValue,
                                    middleKickerCardRankValue,
                                    bottomKickerCardRankValue
                                ),
                                false
                            )

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

            for (highCardRankValue in highValue(Rank.ACE) downTo highValue(Rank.SIX)) {
                for (secondHighestCardRankValue in highCardRankValue - 1 downTo highValue(Rank.FIVE)) {
                    for (thirdHighestCardRankValue in secondHighestCardRankValue - 1 downTo highValue(Rank.FOUR)) {
                        for (fourthHighestCardRankValue in thirdHighestCardRankValue - 1 downTo highValue(Rank.THREE)) {
                            for (fifthHighestCardRankValue in fourthHighestCardRankValue - 1 downTo highValue((Rank.TWO))) {
                                val cardRankValues = arrayListOf(
                                    highCardRankValue,
                                    secondHighestCardRankValue,
                                    thirdHighestCardRankValue,
                                    fourthHighestCardRankValue,
                                    fifthHighestCardRankValue
                                )
                                val key: String = buildHandRankMapKey(cardRankValues,false)
                                val suitedKey: String = buildHandRankMapKey(cardRankValues,true)

                                if (key == FALSE_FIVE_HIGH_STRAIGHT_HAND_RANK_KEY) {
                                    continue
                                }

                                if (highCardRankValue - fifthHighestCardRankValue == 4) {
                                    handRankMap[key] = straightHandRank--
                                    handRankMap[suitedKey] = straightFlushHandRank--
                                } else {
                                    handRankMap[key] = highCardHandRank--
                                    handRankMap[suitedKey] = flushHandRank--
                                }
                            }
                        }
                    }
                }
            }

            handRankMap[TRUE_FIVE_HIGH_STRAIGHT_HAND_RANK_KEY] = MIN_STRAIGHT_RANK
            handRankMap[TRUE_FIVE_HIGH_STRAIGHT_FLUSH_HAND_RANK_KEY] = MIN_STRAIGHT_FLUSH_RANK
        }

        private fun highValue(rank: Rank): Int {
            return rank.value
        }

        private fun lowValue(rank: Rank): Int {
            return if (rank == Rank.ACE) Rank.TWO.value - 1 else rank.value
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