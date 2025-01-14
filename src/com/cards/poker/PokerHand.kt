package com.cards.poker

import com.cards.Rank
import com.cards.Suit

class PokerHand(private val cards: ArrayList<PokerCard>) {
    val rank: Int get() = calculateHandRank()
    private val handCardRankOccurrencesMap = mutableMapOf<Rank, Int>()
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
            val handCardRankOccurrences = handCardRankOccurrencesMap[card.rank] ?: 0
            handCardRankOccurrencesMap[card.rank] = handCardRankOccurrences + 1
        }
    }

    private fun buildHandSuitSet() {
        cards.forEach { card ->
            handSuitSet.add(card.suit)
        }
    }

    private fun calculateHandRank(): Int {
        var result: Int = calculateHighCardHandRank()

        if (result >= 0) {
            return result
        }

        result = calculatePairHandRank()

        if (result >= 0) {
            return result
        }

        result = calculateTwoPairHandRank()

        if (result >= 0) {
            return result
        }

        result = calculateThreeOfAKindHandRank()

        if (result >= 0) {
            return result
        }

        result = calculateStraightHandRank()

        if (result >= 0) {
            return result
        }

        result = calculateFlushHandRank()

        if (result >= 0) {
            return result
        }

        result = calculateFullHouseHandRank()

        if (result >= 0) {
            return result
        }

        result = calculateFourOfAKindHandRank()

        if (result >= 0) {
            return result
        }

        result = calculateStraightFlushHandRank()

        if (result >= 0) {
            return result
        }

        throw Exception("Invalid poker hand")
    }

    private fun calculateStraightFlushHandRank(): Int {
        if (isFlush()) {
            val straightRelativeHandRank = straightRelativeHandRank()

            if (straightRelativeHandRank >= 0) {
                return MIN_STRAIGHT_FLUSH_RANK + straightRelativeHandRank
            }
        }

        return -1
    }

    private fun calculateFourOfAKindHandRank(): Int {
        if (handCardRankOccurrencesMap.size == 2) {
            var fourOfAKindCardRankValue = 0
            var kickerCardRankValue = 0

            handCardRankOccurrencesMap.keys.forEach { rank ->
                if (handCardRankOccurrencesMap[rank] == 4) {
                    fourOfAKindCardRankValue = PokerCard.getHighValue(rank)
                } else if (handCardRankOccurrencesMap[rank] == 1) {
                    kickerCardRankValue = PokerCard.getHighValue(rank)
                }
            }

            if ((fourOfAKindCardRankValue > 0) && (kickerCardRankValue > 0)) {
                val fourOfAKindRelativeHandRankValuesKey = arrayListOf(
                    fourOfAKindCardRankValue,
                    kickerCardRankValue
                ).joinToString(" ")

                fourOfAKindAndFullHouseRelativeHandRankMap[fourOfAKindRelativeHandRankValuesKey]?.let { relativeRank ->
                    return MIN_FOUR_OF_A_KIND_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculateFullHouseHandRank(): Int {
        if (handCardRankOccurrencesMap.size == 2) {
            var threeOfAKindCardRankValue = 0
            var pairCardRankValue = 0

            handCardRankOccurrencesMap.keys.forEach { rank ->
                if (handCardRankOccurrencesMap[rank] == 3) {
                    threeOfAKindCardRankValue = PokerCard.getHighValue(rank)
                } else if (handCardRankOccurrencesMap[rank] == 2) {
                    pairCardRankValue = PokerCard.getHighValue(rank)
                }
            }

            if ((threeOfAKindCardRankValue > 0) && (pairCardRankValue > 0)) {
                val fullHouseRelativeHandRankValuesKey = arrayListOf(
                    threeOfAKindCardRankValue,
                    pairCardRankValue
                ).joinToString(" ")

                fourOfAKindAndFullHouseRelativeHandRankMap[fullHouseRelativeHandRankValuesKey]?.let { relativeRank ->
                    return MIN_FULL_HOUSE_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculateFlushHandRank(): Int {
        if (isFlush()) {
            val handHighCardRankValuesKey = buildHandHighCardRankValuesKey()

            highCardRelativeHandRankMap[handHighCardRankValuesKey]?.let { relativeRank ->
                return MIN_FLUSH_RANK + relativeRank
            }
        }

        return -1
    }

    private fun calculateStraightHandRank(): Int {
        if (!isFlush()) {
            val straightRelativeHandRank = straightRelativeHandRank()

            if (straightRelativeHandRank >= 0) {
                return MIN_STRAIGHT_RANK + straightRelativeHandRank
            }
        }

        return -1
    }

    private fun calculateThreeOfAKindHandRank(): Int {
        if (handCardRankOccurrencesMap.size == 3) {
            var threeOfAKindCardRankValue = 0
            val kickerCardRankValues = arrayListOf<Int>()

            handCardRankOccurrencesMap.keys.forEach { rank ->
                if (handCardRankOccurrencesMap[rank] == 3) {
                    threeOfAKindCardRankValue = PokerCard.getHighValue(rank)
                } else if (handCardRankOccurrencesMap[rank] == 1) {
                    kickerCardRankValues.add(PokerCard.getHighValue(rank))
                }
            }

            if ((threeOfAKindCardRankValue > 0) && (kickerCardRankValues.size == 2)) {
                kickerCardRankValues.sortDescending()
                val threeOfAKindRelativeHandRankValuesKey = arrayListOf(threeOfAKindCardRankValue).plus(kickerCardRankValues).joinToString(" ")

                threeOfAKindRelativeHandRankMap[threeOfAKindRelativeHandRankValuesKey]?.let { relativeRank ->
                    return MIN_THREE_OF_A_KIND_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculateTwoPairHandRank(): Int {
        if (handCardRankOccurrencesMap.size == 3) {
            val pairCardRankValues = arrayListOf<Int>()
            var kickerCardRankValue = 0

            handCardRankOccurrencesMap.keys.forEach { rank ->
                if (handCardRankOccurrencesMap[rank] == 2) {
                    pairCardRankValues.add(PokerCard.getHighValue(rank))
                } else if (handCardRankOccurrencesMap[rank] == 1) {
                    kickerCardRankValue = PokerCard.getHighValue(rank)
                }
            }

            if ((kickerCardRankValue > 0) && (pairCardRankValues.size == 2)) {
                pairCardRankValues.sortDescending()
                val twoPairRelativeHandRankValuesKey = pairCardRankValues.plus(kickerCardRankValue).joinToString(" ")

                twoPairRelativeHandRankMap[twoPairRelativeHandRankValuesKey]?.let { relativeRank ->
                    return MIN_TWO_PAIR_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculatePairHandRank(): Int {
        if (handCardRankOccurrencesMap.size == 4) {
            var pairCardRankValue = 0
            val kickerCardRankValues = arrayListOf<Int>()

            handCardRankOccurrencesMap.keys.forEach { rank ->
                if (handCardRankOccurrencesMap[rank] == 2) {
                    pairCardRankValue = PokerCard.getHighValue(rank)
                } else if (handCardRankOccurrencesMap[rank] == 1) {
                    kickerCardRankValues.add(PokerCard.getHighValue(rank))
                }
            }

            if ((pairCardRankValue > 0) && (kickerCardRankValues.size == 3)) {
                kickerCardRankValues.sortDescending()
                val pairRelativeHandRankValuesKey = arrayListOf(pairCardRankValue).plus(kickerCardRankValues).joinToString(" ")

                pairRelativeHandRankMap[pairRelativeHandRankValuesKey]?.let { relativeRank ->
                    return MIN_PAIR_RANK + relativeRank
                }
            }
        }

        return -1
    }

    private fun calculateHighCardHandRank(): Int {
        if (!isFlush()) {
            val handHighCardRankValuesKey = buildHandHighCardRankValuesKey()

            highCardRelativeHandRankMap[handHighCardRankValuesKey]?.let { relativeRank ->
                return MIN_HIGH_CARD_RANK + relativeRank
            }
        }

        return -1
    }

    private fun isFlush(): Boolean {
        return handSuitSet.size == 1
    }

    private fun straightRelativeHandRank(): Int {
        val handHighCardRankValuesKey = buildHandHighCardRankValuesKey()

        straightRelativeHandRankMap[handHighCardRankValuesKey]?.let { relativeRank ->
            return relativeRank
        }

        val handLowCardRankValuesKey = buildHandLowCardRankValuesKey()

        straightRelativeHandRankMap[handLowCardRankValuesKey]?.let { relativeRank ->
            return relativeRank
        }

        return -1
    }

    private fun buildHandHighCardRankValuesKey(): String {
        val highCardRankValues = arrayListOf<Int>()

        cards.forEach { card ->
            highCardRankValues.add(card.highValue)
        }

        highCardRankValues.sortDescending()

        return highCardRankValues.joinToString(" ")
    }

    private fun buildHandLowCardRankValuesKey(): String {
        val lowCardRankValues = arrayListOf<Int>()

        cards.forEach { card ->
            lowCardRankValues.add(card.lowValue)
        }

        lowCardRankValues.sortDescending()

        return lowCardRankValues.joinToString(" ")
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

        private val straightRelativeHandRankMap = mutableMapOf<String, Int>()
        private val fourOfAKindAndFullHouseRelativeHandRankMap = mutableMapOf<String, Int>()
        private val threeOfAKindRelativeHandRankMap = mutableMapOf<String, Int>()
        private val twoPairRelativeHandRankMap = mutableMapOf<String, Int>()
        private val pairRelativeHandRankMap = mutableMapOf<String, Int>()
        private val highCardRelativeHandRankMap = mutableMapOf<String, Int>()

        private fun buildFourOfAKindAndFullHouseRelativeHandRankMap() {
            var relativeHandRank: Int = NUM_FOUR_OF_A_KIND_RANKS - 1

            for (fourOrThreeOfAKindCardRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                for (kickerOrPairCardRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                    if (fourOrThreeOfAKindCardRank == kickerOrPairCardRank) {
                        continue
                    }

                    val key: String = arrayListOf(
                        fourOrThreeOfAKindCardRank,
                        kickerOrPairCardRank
                    ).joinToString(" ")

//                    println("$key -> $rank")
                    fourOfAKindAndFullHouseRelativeHandRankMap[key] = relativeHandRank--
                }
            }
        }

        private fun buildThreeOfAKindRelativeHandRankMap() {
            var relativeHandRank: Int = NUM_THREE_OF_A_KIND_RANKS - 1

            for (threeOfAKindCardRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                for (topKickerCardRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.THREE)) {
                    for (bottomKickerCardRank in topKickerCardRank - 1 downTo PokerCard.getHighValue(Rank.TWO)) {
                        if ((threeOfAKindCardRank == topKickerCardRank) ||
                            (threeOfAKindCardRank == bottomKickerCardRank) ||
                            (topKickerCardRank == bottomKickerCardRank)) {
                            continue
                        }

                        val key: String = arrayListOf(
                            threeOfAKindCardRank,
                            topKickerCardRank,
                            bottomKickerCardRank
                        ).joinToString(" ")

//                        println("$key -> $rank")
                        threeOfAKindRelativeHandRankMap[key] = relativeHandRank--
                    }
                }
            }
        }

        private fun buildTwoPairRelativeHandRankMap() {
            var relativeHandRank: Int = NUM_TWO_PAIR_RANKS - 1

            for (topPairCardRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.THREE)) {
                for (bottomPairCardRank in topPairCardRank - 1 downTo PokerCard.getHighValue(Rank.TWO)) {
                    for (kickerCardRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                        if ((topPairCardRank == bottomPairCardRank) ||
                            (topPairCardRank == kickerCardRank) ||
                            (bottomPairCardRank == kickerCardRank)) {
                            continue
                        }

                        val key: String = arrayListOf(
                            topPairCardRank,
                            bottomPairCardRank,
                            kickerCardRank
                        ).joinToString(" ")

//                        println("$key -> $rank")
                        twoPairRelativeHandRankMap[key] = relativeHandRank--
                    }
                }
            }
        }

        private fun buildPairRelativeHandRankMap() {
            var relativeHandRank: Int = NUM_PAIR_RANKS - 1

            for (pairCardRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.TWO)) {
                for (topKickerCardRank in PokerCard.getHighValue(Rank.ACE) downTo PokerCard.getHighValue(Rank.FOUR)) {
                    for (middleKickerCardRank in topKickerCardRank - 1 downTo PokerCard.getHighValue(Rank.THREE)) {
                        for (bottomKickerCardRank in middleKickerCardRank - 1 downTo PokerCard.getHighValue(Rank.TWO)) {
                            if ((pairCardRank == topKickerCardRank) ||
                                (pairCardRank == middleKickerCardRank) ||
                                (pairCardRank == bottomKickerCardRank) ||
                                (topKickerCardRank == middleKickerCardRank) ||
                                (topKickerCardRank == bottomKickerCardRank) ||
                                (middleKickerCardRank == bottomKickerCardRank)) {
                                continue
                            }

                            val key: String = arrayListOf(
                                pairCardRank,
                                topKickerCardRank,
                                middleKickerCardRank,
                                bottomKickerCardRank
                            ).joinToString(" ")

//                            println("$key -> $rank")
                            pairRelativeHandRankMap[key] = relativeHandRank--
                        }
                    }
                }
            }
        }

        private fun buildHighCardAndStraightRelativeHandRankMaps() {
            var highCardRelativeHandRank: Int = NUM_HIGH_CARD_RANKS - 1
            var straightRelativeHandRank: Int = NUM_STRAIGHT_RANKS - 1

            val falseFiveHighStraightRelativeRankKey = arrayListOf(
                PokerCard.getHighValue(Rank.ACE),
                PokerCard.getHighValue(Rank.FIVE),
                PokerCard.getHighValue(Rank.FOUR),
                PokerCard.getHighValue(Rank.THREE),
                PokerCard.getHighValue(Rank.TWO),
            ).joinToString(" ")

            val trueFiveHighStraightRelativeRankKey = arrayListOf(
                PokerCard.getHighValue(Rank.FIVE),
                PokerCard.getHighValue(Rank.FOUR),
                PokerCard.getHighValue(Rank.THREE),
                PokerCard.getHighValue(Rank.TWO),
                PokerCard.getLowValue(Rank.ACE),
            ).joinToString(" ")

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
                                    straightRelativeHandRankMap[key] = straightRelativeHandRank--
                                } else if (key != falseFiveHighStraightRelativeRankKey) {
                                    highCardRelativeHandRankMap[key] = highCardRelativeHandRank--
                                }
                            }
                        }
                    }
                }
            }

            straightRelativeHandRankMap[trueFiveHighStraightRelativeRankKey] = straightRelativeHandRank
        }

        init {
            buildFourOfAKindAndFullHouseRelativeHandRankMap()
            buildThreeOfAKindRelativeHandRankMap()
            buildTwoPairRelativeHandRankMap()
            buildPairRelativeHandRankMap()
            buildHighCardAndStraightRelativeHandRankMaps()
        }
    }
}