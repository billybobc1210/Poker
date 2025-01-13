package com.cards//import java.io.Serializable

enum class Rank(val abbreviation: String) {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    companion object {
        fun allRanks(): Array<Rank> {
            return arrayOf(
                TWO,
                THREE,
                FOUR,
                FIVE,
                SIX,
                SEVEN,
                EIGHT,
                NINE,
                TEN,
                JACK,
                QUEEN,
                KING,
                ACE
            )
        }
    }
}