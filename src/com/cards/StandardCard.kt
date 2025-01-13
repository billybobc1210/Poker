package com.cards

data class StandardCard(val rank: Rank, val suit: Suit) {
    val abbreviation: String get() = rank.abbreviation + suit.abbreviation

    override fun toString(): String {
        return "$rank of $suit"
    }
}