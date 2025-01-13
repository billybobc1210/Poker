data class PokerCard(val standardCard: StandardCard) {
    val rank get() = standardCard.rank
    val suit get() = standardCard.suit
    val highValue get() = calculateHighValue()
    val lowValue get() = calculateLowValue()
    val abbreviation get() = standardCard.abbreviation

    private fun calculateHighValue(): Int {
        return getHighValue(rank)
    }

    private fun calculateLowValue(): Int {
        return getLowValue(rank)
    }

    companion object {
        fun getHighValue(rank: Rank): Int {
            return when (rank) {
                Rank.ACE -> 14
                Rank.KING -> 13
                Rank.QUEEN -> 12
                Rank.JACK -> 11
                Rank.TEN -> 10
                Rank.NINE -> 9
                Rank.EIGHT -> 8
                Rank.SEVEN -> 7
                Rank.SIX -> 6
                Rank.FIVE -> 5
                Rank.FOUR -> 4
                Rank.THREE -> 3
                else -> 2
            }
        }

        fun getLowValue(rank: Rank): Int {
            return if (rank == Rank.ACE) 1 else getHighValue(rank)
        }
    }
}