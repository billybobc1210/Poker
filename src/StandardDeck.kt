class StandardDeck {
    private val cards: ArrayList<StandardCard> = arrayListOf(
        StandardCard(Rank.TWO, Suit.CLUBS),
        StandardCard(Rank.THREE, Suit.CLUBS),
        StandardCard(Rank.FOUR, Suit.CLUBS),
        StandardCard(Rank.FIVE, Suit.CLUBS),
        StandardCard(Rank.SIX, Suit.CLUBS),
        StandardCard(Rank.SEVEN, Suit.CLUBS),
        StandardCard(Rank.EIGHT, Suit.CLUBS),
        StandardCard(Rank.NINE, Suit.CLUBS),
        StandardCard(Rank.TEN, Suit.CLUBS),
        StandardCard(Rank.JACK, Suit.CLUBS),
        StandardCard(Rank.QUEEN, Suit.CLUBS),
        StandardCard(Rank.KING, Suit.CLUBS),
        StandardCard(Rank.ACE, Suit.CLUBS),
        StandardCard(Rank.TWO, Suit.DIAMONDS),
        StandardCard(Rank.THREE, Suit.DIAMONDS),
        StandardCard(Rank.FOUR, Suit.DIAMONDS),
        StandardCard(Rank.FIVE, Suit.DIAMONDS),
        StandardCard(Rank.SIX, Suit.DIAMONDS),
        StandardCard(Rank.SEVEN, Suit.DIAMONDS),
        StandardCard(Rank.EIGHT, Suit.DIAMONDS),
        StandardCard(Rank.NINE, Suit.DIAMONDS),
        StandardCard(Rank.TEN, Suit.DIAMONDS),
        StandardCard(Rank.JACK, Suit.DIAMONDS),
        StandardCard(Rank.QUEEN, Suit.DIAMONDS),
        StandardCard(Rank.KING, Suit.DIAMONDS),
        StandardCard(Rank.ACE, Suit.DIAMONDS),
        StandardCard(Rank.TWO, Suit.HEARTS),
        StandardCard(Rank.THREE, Suit.HEARTS),
        StandardCard(Rank.FOUR, Suit.HEARTS),
        StandardCard(Rank.FIVE, Suit.HEARTS),
        StandardCard(Rank.SIX, Suit.HEARTS),
        StandardCard(Rank.SEVEN, Suit.HEARTS),
        StandardCard(Rank.EIGHT, Suit.HEARTS),
        StandardCard(Rank.NINE, Suit.HEARTS),
        StandardCard(Rank.TEN, Suit.HEARTS),
        StandardCard(Rank.JACK, Suit.HEARTS),
        StandardCard(Rank.QUEEN, Suit.HEARTS),
        StandardCard(Rank.KING, Suit.HEARTS),
        StandardCard(Rank.ACE, Suit.HEARTS),
        StandardCard(Rank.TWO, Suit.SPADES),
        StandardCard(Rank.THREE, Suit.SPADES),
        StandardCard(Rank.FOUR, Suit.SPADES),
        StandardCard(Rank.FIVE, Suit.SPADES),
        StandardCard(Rank.SIX, Suit.SPADES),
        StandardCard(Rank.SEVEN, Suit.SPADES),
        StandardCard(Rank.EIGHT, Suit.SPADES),
        StandardCard(Rank.NINE, Suit.SPADES),
        StandardCard(Rank.TEN, Suit.SPADES),
        StandardCard(Rank.JACK, Suit.SPADES),
        StandardCard(Rank.QUEEN, Suit.SPADES),
        StandardCard(Rank.KING, Suit.SPADES),
        StandardCard(Rank.ACE, Suit.SPADES),
    )
    private var topCardIndex = 0

    fun shuffle() {
        topCardIndex = 0
        cards.forEachIndexed { i, card ->
            val r = (0..51).random()
            cards[i] = cards[r]
            cards[r] = card
        }
    }

    fun cardsRemaining(): Int {
        return cards.size - this.topCardIndex
    }

    fun dealTopCard(): StandardCard {
        return cards[this.topCardIndex++]
    }

    fun peekAtCard(i: Int): StandardCard {
        return cards[i]
    }
}