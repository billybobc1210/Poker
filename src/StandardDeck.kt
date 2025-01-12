class StandardDeck {
    val cards: ArrayList<Card> = arrayListOf(
        Card(Rank.TWO, Suit.CLUBS),
        Card(Rank.THREE, Suit.CLUBS),
        Card(Rank.FOUR, Suit.CLUBS),
        Card(Rank.FIVE, Suit.CLUBS),
        Card(Rank.SIX, Suit.CLUBS),
        Card(Rank.SEVEN, Suit.CLUBS),
        Card(Rank.EIGHT, Suit.CLUBS),
        Card(Rank.NINE, Suit.CLUBS),
        Card(Rank.TEN, Suit.CLUBS),
        Card(Rank.JACK, Suit.CLUBS),
        Card(Rank.QUEEN, Suit.CLUBS),
        Card(Rank.KING, Suit.CLUBS),
        Card(Rank.ACE, Suit.CLUBS),
        Card(Rank.TWO, Suit.DIAMONDS),
        Card(Rank.THREE, Suit.DIAMONDS),
        Card(Rank.FOUR, Suit.DIAMONDS),
        Card(Rank.FIVE, Suit.DIAMONDS),
        Card(Rank.SIX, Suit.DIAMONDS),
        Card(Rank.SEVEN, Suit.DIAMONDS),
        Card(Rank.EIGHT, Suit.DIAMONDS),
        Card(Rank.NINE, Suit.DIAMONDS),
        Card(Rank.TEN, Suit.DIAMONDS),
        Card(Rank.JACK, Suit.DIAMONDS),
        Card(Rank.QUEEN, Suit.DIAMONDS),
        Card(Rank.KING, Suit.DIAMONDS),
        Card(Rank.ACE, Suit.DIAMONDS),
        Card(Rank.TWO, Suit.HEARTS),
        Card(Rank.THREE, Suit.HEARTS),
        Card(Rank.FOUR, Suit.HEARTS),
        Card(Rank.FIVE, Suit.HEARTS),
        Card(Rank.SIX, Suit.HEARTS),
        Card(Rank.SEVEN, Suit.HEARTS),
        Card(Rank.EIGHT, Suit.HEARTS),
        Card(Rank.NINE, Suit.HEARTS),
        Card(Rank.TEN, Suit.HEARTS),
        Card(Rank.JACK, Suit.HEARTS),
        Card(Rank.QUEEN, Suit.HEARTS),
        Card(Rank.KING, Suit.HEARTS),
        Card(Rank.ACE, Suit.HEARTS),
        Card(Rank.TWO, Suit.SPADES),
        Card(Rank.THREE, Suit.SPADES),
        Card(Rank.FOUR, Suit.SPADES),
        Card(Rank.FIVE, Suit.SPADES),
        Card(Rank.SIX, Suit.SPADES),
        Card(Rank.SEVEN, Suit.SPADES),
        Card(Rank.EIGHT, Suit.SPADES),
        Card(Rank.NINE, Suit.SPADES),
        Card(Rank.TEN, Suit.SPADES),
        Card(Rank.JACK, Suit.SPADES),
        Card(Rank.QUEEN, Suit.SPADES),
        Card(Rank.KING, Suit.SPADES),
        Card(Rank.ACE, Suit.SPADES),
    )
    var topCardIndex = 0;

    fun shuffle() {
        topCardIndex = 0;
        cards.forEachIndexed { i, card ->
            var r = (0..51).random()
            cards[i] = cards[r]
            cards[r] = card
        }
    }

    fun cardsRemaining(): Int {
        return cards.size - this.topCardIndex;
    }

    fun dealTopCard(): Card {
        return cards[this.topCardIndex++]
    }

    fun peekAtCard(i: Int): Card {
        return cards[i]
    }

    fun display() {
        cards.forEachIndexed { i, card ->
            println(card)
        }
    }
}