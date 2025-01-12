class PokerHand(val cards: ArrayList<Card>) {
    val rank: Int get() = calculateRank()
    private val handRankOccurrencesMap = mutableMapOf<Rank, Int>()
    private val handSuitOccurrencesMap = mutableMapOf<Suit, Int>()
    private var relativeStraightRank: Int = -1

    init {
        if (cards.size != 5) {
            throw Exception("Not a standard 5 card poker hand")
        }

        val cardSet = mutableSetOf<Card>()
        cards.forEach { card ->
            cardSet.add(card)
        }

        if (cardSet.size < 5) {
            throw Exception("All 5 cards in hand must be unique")
        }

        buildHandRankOccurrencesMap()
        buildHandSuitOccurrencesMap()
    }

    private fun buildHandRankOccurrencesMap() {
        cards.forEach { card ->
            val rankOccurrences = handRankOccurrencesMap[card.rank] ?: 0
            handRankOccurrencesMap[card.rank] = rankOccurrences + 1
        }
    }

    private fun buildHandSuitOccurrencesMap() {
        cards.forEach { card ->
            val suitOccurrences = handSuitOccurrencesMap[card.suit] ?: 0
            handSuitOccurrencesMap[card.suit] = suitOccurrences + 1
        }
    }

    private fun calculateRank(): Int {
        var result: Int = calculateStraightFlushRank()

        if (result >= 0) {
            return result
        }

        result = calculateFourOfAKindRank()

        if (result >= 0) {
            return result
        }

        result = calculateFullHouseRank()

        if (result >= 0) {
            return result
        }

        result = calculateFlushRank()

        if (result >= 0) {
            return result
        }

        result = calculateStraightRank()

        if (result >= 0) {
            return result
        }

        result = calculateThreeOfAKindRank()

        if (result >= 0) {
            return result
        }

        result = calculateTwoPairRank()

        if (result >= 0) {
            return result
        }

        result = calculatePairRank()

        if (result >= 0) {
            return result
        }

        return calculateHighCardRank()
    }

    private fun calculateStraightFlushRank(): Int {
        if (isStraight() && isFlush()) {
            return MIN_STRAIGHT_FLUSH_RANK + relativeStraightRank()
        }

        return -1
    }

    private fun calculateFourOfAKindRank(): Int {
        if (handRankOccurrencesMap.size == 2) {
            var fourOfAKindRankValue: Int = 0
            var kickerRankValue: Int = 0

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 4) {
                    fourOfAKindRankValue = rank.highValue
                } else if (handRankOccurrencesMap[rank] == 1) {
                    kickerRankValue = rank.highValue
                }
            }

            if ((fourOfAKindRankValue > 0) && (kickerRankValue > 0)) {
                val fourOfAKindRankMapKey = arrayListOf<Int>(
                    fourOfAKindRankValue,
                    kickerRankValue
                ).joinToString(" ")

                return MIN_FOUR_OF_A_KIND_RANK + (fourOfAKindAndFullHouseRankMap[fourOfAKindRankMapKey] ?: 0)
            }
        }

        return -1
    }

    private fun calculateFullHouseRank(): Int {
        if (handRankOccurrencesMap.size == 2) {
            var threeOfAKindRankValue: Int = 0
            var pairRankValue: Int = 0

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 3) {
                    threeOfAKindRankValue = rank.highValue
                } else if (handRankOccurrencesMap[rank] == 2) {
                    pairRankValue = rank.highValue
                }
            }

            if ((threeOfAKindRankValue > 0) && (pairRankValue > 0)) {
                val fullHouseRankMapKey = arrayListOf<Int>(
                    threeOfAKindRankValue,
                    pairRankValue
                ).joinToString(" ")

                return MIN_FULL_HOUSE_RANK + (fourOfAKindAndFullHouseRankMap[fullHouseRankMapKey] ?: 0)
            }
        }

        return -1
    }

    private fun calculateFlushRank(): Int {
        if (isFlush()) {
            val key = buildHighCardRankKey()

            return MIN_FLUSH_RANK + (highCardRankMap[key] ?: 0)
        }

        return -1
    }

    private fun calculateStraightRank(): Int {
        if (isStraight() && !isFlush()) {
            return MIN_STRAIGHT_RANK + relativeStraightRank()
        }

        return -1
    }

    private fun calculateThreeOfAKindRank(): Int {
        if (handRankOccurrencesMap.size == 3) {
            var threeOfAKindRank: Int = 0
            val kickerRankValues = arrayListOf<Int>()

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 3) {
                    threeOfAKindRank = rank.highValue
                } else if (handRankOccurrencesMap[rank] == 1) {
                    kickerRankValues.add(rank.highValue)
                }
            }

            if ((threeOfAKindRank > 0) && (kickerRankValues.size == 2)) {
                kickerRankValues.sortDescending()
                val threeOfAKindRankMapKey = arrayListOf<Int>(threeOfAKindRank).plus(kickerRankValues).joinToString(" ")

                return MIN_THREE_OF_A_KIND_RANK + (threeOfAKindRankMap[threeOfAKindRankMapKey] ?: 0)
            }
        }

        return -1
    }

    private fun calculateTwoPairRank(): Int {
        if (handRankOccurrencesMap.size == 3) {
            val pairRankValues = arrayListOf<Int>()
            var kickerRankValue: Int = 0

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 2) {
                    pairRankValues.add(rank.highValue)
                } else if (handRankOccurrencesMap[rank] == 1) {
                    kickerRankValue = rank.highValue
                }
            }

            if ((kickerRankValue > 0) && (pairRankValues.size == 2)) {
                pairRankValues.sortDescending()
                val twoPairRankMapKey = pairRankValues.plus(kickerRankValue).joinToString(" ")

                return MIN_TWO_PAIR_RANK + (twoPairRankMap[twoPairRankMapKey] ?: 0)
            }
        }

        return -1
    }

    private fun calculatePairRank(): Int {
        if (handRankOccurrencesMap.size == 4) {
            var pairRankValue: Int = 0
            val kickerRankValues = arrayListOf<Int>()

            handRankOccurrencesMap.keys.forEach { rank ->
                if (handRankOccurrencesMap[rank] == 2) {
                    pairRankValue = rank.highValue
                } else if (handRankOccurrencesMap[rank] == 1) {
                    kickerRankValues.add(rank.highValue)
                }
            }

            if ((pairRankValue > 0) && (kickerRankValues.size == 3)) {
                kickerRankValues.sortDescending()
                val pairRankMapKey = arrayListOf(pairRankValue).plus(kickerRankValues).joinToString(" ")

                return MIN_PAIR_RANK + (pairRankMap[pairRankMapKey] ?: 0)
            }
        }

        return -1
    }

    private fun calculateHighCardRank(): Int {
        val key = buildHighCardRankKey()
        return highCardRankMap[key] ?: 0
    }

    private fun isStraight(): Boolean {
        return relativeStraightRank() != -1
    }

    private fun isFlush(): Boolean {
        return handSuitOccurrencesMap.size == 1
    }

    private fun relativeStraightRank(): Int {
        if (relativeStraightRank < 0) {
            if (handRankOccurrencesMap.size == 5) {
                val lowRankValues = arrayListOf<Int>()
                cards.forEach { card ->
                    lowRankValues.add(card.rank.lowValue)
                }
                lowRankValues.sort()

                if (lowRankValues[4] - lowRankValues[0] == 4) {
                    relativeStraightRank = lowRankValues[0] - Rank.ACE.lowValue
                }

                val highRankValues = arrayListOf<Int>()
                cards.forEach { card ->
                    highRankValues.add(card.rank.highValue)
                }
                highRankValues.sort()

                if (highRankValues[4] - highRankValues[0] == 4) {
                    relativeStraightRank = highRankValues[0] - Rank.ACE.lowValue
                }
            }
        }

        return relativeStraightRank
    }

    private fun buildHighCardRankKey(): String {
        val rankValues = arrayListOf<Int>()

        cards.forEach { card ->
            rankValues.add(card.rank.highValue)
        }

        rankValues.sortDescending()

        return rankValues.joinToString(" ")
    }

    override fun toString(): String {
        return cards.fold("",  { acc, card ->
            var prefix = if (acc.equals("")) "" else " "

            acc + prefix + card.abbreviation
        })
    }

    companion object {
        val NUM_HIGH_CARD_RANKS: Int = 1277
        val MIN_HIGH_CARD_RANK: Int = 0
        val MAX_HIGH_CARD_RANK: Int = MIN_HIGH_CARD_RANK + NUM_HIGH_CARD_RANKS - 1
        val NUM_PAIR_RANKS: Int = 2860
        val MIN_PAIR_RANK: Int = MAX_HIGH_CARD_RANK + 1
        val MAX_PAIR_RANK: Int = MIN_PAIR_RANK + NUM_PAIR_RANKS - 1
        val NUM_TWO_PAIR_RANKS = 858
        val MIN_TWO_PAIR_RANK: Int = MAX_PAIR_RANK + 1
        val MAX_TWO_PAIR_RANK: Int = MIN_TWO_PAIR_RANK + NUM_TWO_PAIR_RANKS - 1
        val NUM_THREE_OF_A_KIND_RANKS: Int = 858
        val MIN_THREE_OF_A_KIND_RANK: Int = MAX_TWO_PAIR_RANK + 1
        val MAX_THREE_OF_A_KIND_RANK: Int = MIN_THREE_OF_A_KIND_RANK + NUM_THREE_OF_A_KIND_RANKS - 1
        val NUM_STRAIGHT_RANKS: Int = 10
        val MIN_STRAIGHT_RANK: Int = MAX_THREE_OF_A_KIND_RANK + 1
        val MAX_STRAIGHT_RANK: Int = MIN_STRAIGHT_RANK + NUM_STRAIGHT_RANKS - 1
        val NUM_FLUSH_RANKS: Int = 1277
        val MIN_FLUSH_RANK: Int = MAX_STRAIGHT_RANK + 1
        val MAX_FLUSH_RANK: Int = MIN_FLUSH_RANK + NUM_FLUSH_RANKS - 1
        val NUM_FULL_HOUSE_RANKS: Int = 156
        val MIN_FULL_HOUSE_RANK: Int = MAX_FLUSH_RANK + 1
        val MAX_FULL_HOUSE_RANK: Int = MIN_FULL_HOUSE_RANK + NUM_FULL_HOUSE_RANKS - 1
        val NUM_FOUR_OF_A_KIND_RANKS = 156
        val MIN_FOUR_OF_A_KIND_RANK: Int = MAX_FULL_HOUSE_RANK + 1
        val MAX_FOUR_OF_A_KIND_RANK: Int = MIN_FOUR_OF_A_KIND_RANK + NUM_FOUR_OF_A_KIND_RANKS - 1
        val NUM_STRAIGHT_FLUSH_RANKS: Int = 10
        val MIN_STRAIGHT_FLUSH_RANK: Int = MAX_FOUR_OF_A_KIND_RANK + 1
        val MAX_STRAIGHT_FLUSH_RANK: Int = MIN_STRAIGHT_FLUSH_RANK + NUM_STRAIGHT_FLUSH_RANKS - 1

        private val fourOfAKindAndFullHouseRankMap = mutableMapOf<String, Int>()
        private val threeOfAKindRankMap = mutableMapOf<String, Int>()
        private val twoPairRankMap = mutableMapOf<String, Int>()
        private val pairRankMap = mutableMapOf<String, Int>()
        private val highCardRankMap = mutableMapOf<String, Int>()

        private fun buildFourOfAKindAndFullHouseRankMap() {
            var rank: Int = NUM_FOUR_OF_A_KIND_RANKS - 1

            for (fourOrThreeOfAKindRank in 14 downTo 2) {
                for (kickerOrPairRank in 14 downTo 2) {
                    if (fourOrThreeOfAKindRank == kickerOrPairRank) {
                        continue
                    }

                    val key: String = arrayListOf<Int>(
                        fourOrThreeOfAKindRank,
                        kickerOrPairRank
                    ).joinToString(" ")

//                    println("$key -> $rank")
                    fourOfAKindAndFullHouseRankMap[key] = rank--
                }
            }
        }

        private fun buildThreeOfAKindRankMap() {
            var rank: Int = NUM_THREE_OF_A_KIND_RANKS - 1

            for (threeOfAKindRank in 14 downTo 2) {
                for (topKickerRank in 14 downTo 3) {
                    for (bottomKickerRank in topKickerRank - 1 downTo 2) {
                        if ((threeOfAKindRank == topKickerRank) ||
                            (threeOfAKindRank == bottomKickerRank) ||
                            (topKickerRank == bottomKickerRank)) {
                            continue
                        }

                        val key: String = arrayListOf<Int>(
                            threeOfAKindRank,
                            topKickerRank,
                            bottomKickerRank
                        ).joinToString(" ")

//                        println("$key -> $rank")
                        threeOfAKindRankMap[key] = rank--
                    }
                }
            }
        }

        private fun buildTwoPairRankMap() {
            var rank: Int = NUM_TWO_PAIR_RANKS - 1

            for (topPairRank in 14 downTo 3) {
                for (bottomPairRank in topPairRank - 1 downTo 2) {
                    for (kickerRank in 14 downTo 2) {
                        if ((topPairRank == bottomPairRank) ||
                            (topPairRank == kickerRank) ||
                            (bottomPairRank == kickerRank)) {
                            continue
                        }

                        val key: String = arrayListOf<Int>(
                            topPairRank,
                            bottomPairRank,
                            kickerRank
                        ).joinToString(" ")

//                        println("$key -> $rank")
                        twoPairRankMap[key] = rank--
                    }
                }
            }
        }

        private fun buildPairRankMap() {
            var rank: Int = NUM_PAIR_RANKS - 1

            for (pairRank in 14 downTo 2) {
                for (topKickerRank in 14 downTo 4) {
                    for (middleKickerRank in topKickerRank - 1 downTo 3) {
                        for (bottomKickerRank in middleKickerRank - 1 downTo 2) {
                            if ((pairRank == topKickerRank) ||
                                (pairRank == middleKickerRank) ||
                                (pairRank == bottomKickerRank) ||
                                (topKickerRank == middleKickerRank) ||
                                (topKickerRank == bottomKickerRank) ||
                                (middleKickerRank == bottomKickerRank)) {
                                continue
                            }

                            val key: String = arrayListOf<Int>(
                                pairRank,
                                topKickerRank,
                                middleKickerRank,
                                bottomKickerRank
                            ).joinToString(" ")

//                            println("$key -> $rank")
                            pairRankMap[key] = rank--
                        }
                    }
                }
            }
        }

        private fun buildHighCardRankMap() {
            var rank: Int = NUM_FLUSH_RANKS - 1

            for (highCardRank in 14 downTo 6) {
                for (secondHighestCardRank in highCardRank - 1 downTo 5) {
                    for (thirdHighestCardRank in secondHighestCardRank - 1 downTo 4) {
                        for (fourthHighestCardRank in thirdHighestCardRank - 1 downTo 3) {
                            for (fifthHighestCardRank in fourthHighestCardRank - 1 downTo 2) {
                                val key: String = arrayListOf<Int>(
                                    highCardRank,
                                    secondHighestCardRank,
                                    thirdHighestCardRank,
                                    fourthHighestCardRank,
                                    fifthHighestCardRank
                                ).joinToString(" ")

                                if ((highCardRank-fifthHighestCardRank == 4) || key.equals("14 5 4 3 2")){
                                    // exclude straights
                                    continue
                                }

//                                println("$key -> $rank")
                                highCardRankMap[key] = rank--
                            }
                        }
                    }
                }
            }
        }

        init {
            buildFourOfAKindAndFullHouseRankMap()
            buildThreeOfAKindRankMap()
            buildTwoPairRankMap()
            buildPairRankMap()
            buildHighCardRankMap()
        }
    }
}