package com.cards.poker

import com.cards.Rank
import com.cards.Suit

class PokerHand(private val cards: ArrayList<PokerCard>) {
    val rank: Int get() = calculateRank()
    private val handRankOccurrencesMap = mutableMapOf<Rank, Int>()
    private val handSuitSet = mutableSetOf<Suit>()

    init {
        if (cards.size != 5) {
            throw Exception("Not a standard 5 card poker hand")
        }

        val cardSet = mutableSetOf<PokerCard>()
        cards.forEach { card ->
            cardSet.add(card)
        }

        if (cardSet.size < 5) {
            throw Exception("All 5 cards in hand must be unique")
        }

        buildHandRankOccurrencesMap()
        buildHandSuitSet()
    }

    private fun buildHandRankOccurrencesMap() {
        cards.forEach { card ->
            val rankOccurrences = handRankOccurrencesMap[card.rank] ?: 0
            handRankOccurrencesMap[card.rank] = rankOccurrences + 1
        }
    }

    private fun buildHandSuitSet() {
        cards.forEach { card ->
            handSuitSet.add(card.suit)
        }
    }

    private fun calculateRank(): Int {
        var result: Int = calculateHighCardRank()

        if (result >= 0) {
            return result
        }

        result = calculatePairRank()

        if (result >= 0) {
            return result
        }

        result = calculateTwoPairRank()

        if (result >= 0) {
            return result
        }

        result = calculateThreeOfAKindRank()

        if (result >= 0) {
            return result
        }

        result = calculateStraightRank()

        if (result >= 0) {
            return result
        }

        result = calculateFlushRank()

        if (result >= 0) {
            return result
        }

        result = calculateFullHouseRank()

        if (result >= 0) {
            return result
        }

        result = calculateFourOfAKindRank()

        if (result >= 0) {
            return result
        }

        result = calculateStraightFlushRank()

        if (result >= 0) {
            return result
        }

        throw Exception("Invalid poker hand")
    }

    private fun calculateStraightFlushRank(): Int {
        if (isFlush()) {
            val relativeStraightRank = relativeStraightRank()

            if (relativeStraightRank >= 0) {
                return MIN_STRAIGHT_FLUSH_RANK + relativeStraightRank
            }
        }

        return -1
    }

    private fun calculateFourOfAKindRank(): Int {
        if (handRankOccurrencesMap.size == 2) {
            var fourOfAKindRankValue = 0
            var kickerRankValue = 0

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 4) {
                    fourOfAKindRankValue = PokerCard.getHighValue(rank)
                } else if (handRankOccurrencesMap[rank] == 1) {
                    kickerRankValue = PokerCard.getHighValue(rank)
                }
            }

            if ((fourOfAKindRankValue > 0) && (kickerRankValue > 0)) {
                val fourOfAKindRankMapKey = arrayListOf(
                    fourOfAKindRankValue,
                    kickerRankValue
                ).joinToString(" ")

                fourOfAKindAndFullHouseRelativeRankMap[fourOfAKindRankMapKey]?.let { relativeRank ->
                    return MIN_FOUR_OF_A_KIND_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculateFullHouseRank(): Int {
        if (handRankOccurrencesMap.size == 2) {
            var threeOfAKindRankValue = 0
            var pairRankValue = 0

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 3) {
                    threeOfAKindRankValue = PokerCard.getHighValue(rank)
                } else if (handRankOccurrencesMap[rank] == 2) {
                    pairRankValue = PokerCard.getHighValue(rank)
                }
            }

            if ((threeOfAKindRankValue > 0) && (pairRankValue > 0)) {
                val fullHouseRankMapKey = arrayListOf(
                    threeOfAKindRankValue,
                    pairRankValue
                ).joinToString(" ")

                fourOfAKindAndFullHouseRelativeRankMap[fullHouseRankMapKey]?.let { relativeRank ->
                    return MIN_FULL_HOUSE_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculateFlushRank(): Int {
        if (isFlush()) {
            val key = buildHandHighRankValueKey()

            highCardRelativeRankMap[key]?.let { relativeRank ->
                return MIN_FLUSH_RANK + relativeRank
            }
        }

        return -1
    }

    private fun calculateStraightRank(): Int {
        if (!isFlush()) {
            val handHighRankValueKey = buildHandHighRankValueKey()

            straightRelativeRankMap[handHighRankValueKey]?.let { relativeRank ->
                return MIN_STRAIGHT_RANK + relativeRank
            }

            val handLowRankValueKey = buildHandLowRankValueKey()

            straightRelativeRankMap[handLowRankValueKey]?.let { relativeRank ->
                return MIN_STRAIGHT_RANK + relativeRank
            }
        }

        return -1
    }

    private fun calculateThreeOfAKindRank(): Int {
        if (handRankOccurrencesMap.size == 3) {
            var threeOfAKindRank = 0
            val kickerRankValues = arrayListOf<Int>()

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 3) {
                    threeOfAKindRank = PokerCard.getHighValue(rank)
                } else if (handRankOccurrencesMap[rank] == 1) {
                    kickerRankValues.add(PokerCard.getHighValue(rank))
                }
            }

            if ((threeOfAKindRank > 0) && (kickerRankValues.size == 2)) {
                kickerRankValues.sortDescending()
                val threeOfAKindRankMapKey = arrayListOf(threeOfAKindRank).plus(kickerRankValues).joinToString(" ")

                threeOfAKindRelativeRankMap[threeOfAKindRankMapKey]?.let { relativeRank ->
                    return MIN_THREE_OF_A_KIND_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculateTwoPairRank(): Int {
        if (handRankOccurrencesMap.size == 3) {
            val pairRankValues = arrayListOf<Int>()
            var kickerRankValue = 0

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 2) {
                    pairRankValues.add(PokerCard.getHighValue(rank))
                } else if (handRankOccurrencesMap[rank] == 1) {
                    kickerRankValue = PokerCard.getHighValue(rank)
                }
            }

            if ((kickerRankValue > 0) && (pairRankValues.size == 2)) {
                pairRankValues.sortDescending()
                val twoPairRankMapKey = pairRankValues.plus(kickerRankValue).joinToString(" ")

                twoPairRelativeRankMap[twoPairRankMapKey]?.let { relativeRank ->
                    return MIN_TWO_PAIR_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculatePairRank(): Int {
        if (handRankOccurrencesMap.size == 4) {
            var pairRankValue = 0
            val kickerRankValues = arrayListOf<Int>()

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 2) {
                    pairRankValue = PokerCard.getHighValue(rank)
                } else if (handRankOccurrencesMap[rank] == 1) {
                    kickerRankValues.add(PokerCard.getHighValue(rank))
                }
            }

            if ((pairRankValue > 0) && (kickerRankValues.size == 3)) {
                kickerRankValues.sortDescending()
                val pairRankMapKey = arrayListOf(pairRankValue).plus(kickerRankValues).joinToString(" ")

                pairRelativeRankMap[pairRankMapKey]?.let { relativeRank ->
                    return MIN_PAIR_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculateHighCardRank(): Int {
        if (!isFlush()) {
            val key = buildHandHighRankValueKey()
            highCardRelativeRankMap[key]?.let { relativeRank ->
                return MIN_HIGH_CARD_RANK + relativeRank
            }
        }

        return -1
    }

    private fun isFlush(): Boolean {
        return handSuitSet.size == 1
    }

    private fun relativeStraightRank(): Int {
        val handHighRankValueKey = buildHandHighRankValueKey()

        straightRelativeRankMap[handHighRankValueKey]?.let { relativeRank ->
            return relativeRank
        }

        val handLowRankValueKey = buildHandLowRankValueKey()

        straightRelativeRankMap[handLowRankValueKey]?.let { relativeRank ->
            return relativeRank
        }

        return -1
    }

    private fun buildHandHighRankValueKey(): String {
        val rankValues = arrayListOf<Int>()

        cards.forEach { card ->
            rankValues.add(card.highValue)
        }

        rankValues.sortDescending()

        return rankValues.joinToString(" ")
    }

    private fun buildHandLowRankValueKey(): String {
        val rankValues = arrayListOf<Int>()

        cards.forEach { card ->
            rankValues.add(card.lowValue)
        }

        rankValues.sortDescending()

        return rankValues.joinToString(" ")
    }

    override fun toString(): String {
        return cards.fold("") { acc, card ->
            val prefix = if (acc == "") "" else " "

            acc + prefix + card.abbreviation
        }
    }

    companion object {
        const val NUM_HIGH_CARD_RANKS: Int = 1277
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
        const val NUM_STRAIGHT_RANKS: Int = 10
        const val MIN_STRAIGHT_RANK: Int = MAX_THREE_OF_A_KIND_RANK + 1
        const val MAX_STRAIGHT_RANK: Int = MIN_STRAIGHT_RANK + NUM_STRAIGHT_RANKS - 1
        const val NUM_FLUSH_RANKS: Int = 1277
        const val MIN_FLUSH_RANK: Int = MAX_STRAIGHT_RANK + 1
        const val MAX_FLUSH_RANK: Int = MIN_FLUSH_RANK + NUM_FLUSH_RANKS - 1
        const val NUM_FULL_HOUSE_RANKS: Int = 156
        const val MIN_FULL_HOUSE_RANK: Int = MAX_FLUSH_RANK + 1
        const val MAX_FULL_HOUSE_RANK: Int = MIN_FULL_HOUSE_RANK + NUM_FULL_HOUSE_RANKS - 1
        const val NUM_FOUR_OF_A_KIND_RANKS = 156
        const val MIN_FOUR_OF_A_KIND_RANK: Int = MAX_FULL_HOUSE_RANK + 1
        const val MAX_FOUR_OF_A_KIND_RANK: Int = MIN_FOUR_OF_A_KIND_RANK + NUM_FOUR_OF_A_KIND_RANKS - 1
        const val NUM_STRAIGHT_FLUSH_RANKS: Int = 10
        const val MIN_STRAIGHT_FLUSH_RANK: Int = MAX_FOUR_OF_A_KIND_RANK + 1
        const val MAX_STRAIGHT_FLUSH_RANK: Int = MIN_STRAIGHT_FLUSH_RANK + NUM_STRAIGHT_FLUSH_RANKS - 1

        private val straightRelativeRankMap = mutableMapOf<String, Int>()
        private val fourOfAKindAndFullHouseRelativeRankMap = mutableMapOf<String, Int>()
        private val threeOfAKindRelativeRankMap = mutableMapOf<String, Int>()
        private val twoPairRelativeRankMap = mutableMapOf<String, Int>()
        private val pairRelativeRankMap = mutableMapOf<String, Int>()
        private val highCardRelativeRankMap = mutableMapOf<String, Int>()

        private fun buildFourOfAKindAndFullHouseRelativeRankMap() {
            var rank: Int = NUM_FOUR_OF_A_KIND_RANKS - 1

            for (fourOrThreeOfAKindRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                for (kickerOrPairRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                    if (fourOrThreeOfAKindRank == kickerOrPairRank) {
                        continue
                    }

                    val key: String = arrayListOf(
                        fourOrThreeOfAKindRank,
                        kickerOrPairRank
                    ).joinToString(" ")

//                    println("$key -> $rank")
                    fourOfAKindAndFullHouseRelativeRankMap[key] = rank--
                }
            }
        }

        private fun buildThreeOfAKindRelativeRankMap() {
            var rank: Int = NUM_THREE_OF_A_KIND_RANKS - 1

            for (threeOfAKindRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                for (topKickerRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.THREE)) {
                    for (bottomKickerRank in topKickerRank - 1 downTo PokerCard.getHighValue(Rank.TWO)) {
                        if ((threeOfAKindRank == topKickerRank) ||
                            (threeOfAKindRank == bottomKickerRank) ||
                            (topKickerRank == bottomKickerRank)) {
                            continue
                        }

                        val key: String = arrayListOf(
                            threeOfAKindRank,
                            topKickerRank,
                            bottomKickerRank
                        ).joinToString(" ")

//                        println("$key -> $rank")
                        threeOfAKindRelativeRankMap[key] = rank--
                    }
                }
            }
        }

        private fun buildTwoPairRelativeRankMap() {
            var rank: Int = NUM_TWO_PAIR_RANKS - 1

            for (topPairRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.THREE)) {
                for (bottomPairRank in topPairRank - 1 downTo PokerCard.getHighValue(Rank.TWO)) {
                    for (kickerRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                        if ((topPairRank == bottomPairRank) ||
                            (topPairRank == kickerRank) ||
                            (bottomPairRank == kickerRank)) {
                            continue
                        }

                        val key: String = arrayListOf(
                            topPairRank,
                            bottomPairRank,
                            kickerRank
                        ).joinToString(" ")

//                        println("$key -> $rank")
                        twoPairRelativeRankMap[key] = rank--
                    }
                }
            }
        }

        private fun buildPairRelativeRankMap() {
            var rank: Int = NUM_PAIR_RANKS - 1

            for (pairRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                for (topKickerRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.FOUR)) {
                    for (middleKickerRank in topKickerRank - 1 downTo PokerCard.getHighValue(Rank.THREE)) {
                        for (bottomKickerRank in middleKickerRank - 1 downTo PokerCard.getHighValue(Rank.TWO)) {
                            if ((pairRank == topKickerRank) ||
                                (pairRank == middleKickerRank) ||
                                (pairRank == bottomKickerRank) ||
                                (topKickerRank == middleKickerRank) ||
                                (topKickerRank == bottomKickerRank) ||
                                (middleKickerRank == bottomKickerRank)) {
                                continue
                            }

                            val key: String = arrayListOf(
                                pairRank,
                                topKickerRank,
                                middleKickerRank,
                                bottomKickerRank
                            ).joinToString(" ")

//                            println("$key -> $rank")
                            pairRelativeRankMap[key] = rank--
                        }
                    }
                }
            }
        }

        private fun buildHighCardAndStraightRelativeRankMaps() {
            var highCardRelativeRank: Int = NUM_HIGH_CARD_RANKS - 1
            var straightRelativeRank: Int = NUM_STRAIGHT_RANKS - 1

            for (highCardRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.SIX)) {
                for (secondHighestCardRank in highCardRank - 1 downTo PokerCard.getHighValue(Rank.FIVE)) {
                    for (thirdHighestCardRank in secondHighestCardRank - 1 downTo PokerCard.getHighValue(Rank.FOUR)) {
                        for (fourthHighestCardRank in thirdHighestCardRank - 1 downTo PokerCard.getHighValue(Rank.THREE)) {
                            for (fifthHighestCardRank in fourthHighestCardRank - 1 downTo PokerCard.getHighValue((Rank.TWO))) {
                                val key: String = arrayListOf(
                                    highCardRank,
                                    secondHighestCardRank,
                                    thirdHighestCardRank,
                                    fourthHighestCardRank,
                                    fifthHighestCardRank
                                ).joinToString(" ")

                                if (highCardRank - fifthHighestCardRank == 4) {
                                    straightRelativeRankMap[key] = straightRelativeRank--
                                } else if (key != "14 5 4 3 2") {
                                    highCardRelativeRankMap[key] = highCardRelativeRank--
                                }
                            }
                        }
                    }
                }
            }

            straightRelativeRankMap["5 4 3 2 1"] = straightRelativeRank
        }

        init {
            buildFourOfAKindAndFullHouseRelativeRankMap()
            buildThreeOfAKindRelativeRankMap()
            buildTwoPairRelativeRankMap()
            buildPairRelativeRankMap()
            buildHighCardAndStraightRelativeRankMaps()
        }
    }
}