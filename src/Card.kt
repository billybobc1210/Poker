data class Card(val rank: Rank, val suit: Suit) {
    val abbreviation: String get() = rank.abbreviation + suit.abbreviation

    override fun toString(): String {
        return rank.toString() + " of " + suit.toString()
    }
}