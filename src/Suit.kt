enum class Suit(val abbreviation: String) {
    CLUBS("c"),
    DIAMONDS("d"),
    HEARTS("h"),
    SPADES("s");

    companion object {
        fun allSuits(): Array<Suit> {
            return arrayOf(CLUBS, DIAMONDS, HEARTS, SPADES)
        }
    }
}