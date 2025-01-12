import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class PokerHandTest {
    @Test
    fun tooManyCardsTest() {
        var validHand = true
        val cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.KING, Suit.SPADES),
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.TEN, Suit.SPADES),
            Card(Rank.NINE, Suit.DIAMONDS),
        )

        try {
            var pokerHand = PokerHand(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertFalse(validHand)
    }

    @Test
    fun tooFewCardsTest() {
        var validHand = true
        val cards = arrayListOf<Card>(
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.TEN, Suit.SPADES),
            Card(Rank.NINE, Suit.DIAMONDS),
        )

        try {
            var pokerHand = PokerHand(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertFalse(validHand)
    }

    @Test
    fun duplicateCardsTest() {
        var validHand = true
        val cards = arrayListOf<Card>(
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.TEN, Suit.SPADES),
            Card(Rank.NINE, Suit.DIAMONDS),
        )

        try {
            var pokerHand = PokerHand(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertFalse(validHand)
    }

    @Test
    fun straightFlushTest() {
        var cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.KING, Suit.SPADES),
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.TEN, Suit.SPADES),
        )
        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK + 9, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.KING, Suit.SPADES),
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.TEN, Suit.SPADES),
            Card(Rank.NINE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-1, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+8, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.TEN, Suit.SPADES),
            Card(Rank.NINE, Suit.SPADES),
            Card(Rank.EIGHT, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-2, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+7, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.TEN, Suit.SPADES),
            Card(Rank.NINE, Suit.SPADES),
            Card(Rank.EIGHT, Suit.SPADES),
            Card(Rank.SEVEN, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-3, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+6, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TEN, Suit.SPADES),
            Card(Rank.NINE, Suit.SPADES),
            Card(Rank.EIGHT, Suit.SPADES),
            Card(Rank.SEVEN, Suit.SPADES),
            Card(Rank.SIX, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-4, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+5, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.NINE, Suit.SPADES),
            Card(Rank.EIGHT, Suit.SPADES),
            Card(Rank.SEVEN, Suit.SPADES),
            Card(Rank.SIX, Suit.SPADES),
            Card(Rank.FIVE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-5, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+4, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.EIGHT, Suit.SPADES),
            Card(Rank.SEVEN, Suit.SPADES),
            Card(Rank.SIX, Suit.SPADES),
            Card(Rank.FIVE, Suit.SPADES),
            Card(Rank.FOUR, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-6, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+3, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.SEVEN, Suit.SPADES),
            Card(Rank.SIX, Suit.SPADES),
            Card(Rank.FIVE, Suit.SPADES),
            Card(Rank.FOUR, Suit.SPADES),
            Card(Rank.THREE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-7, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+2, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.SIX, Suit.SPADES),
            Card(Rank.FIVE, Suit.SPADES),
            Card(Rank.FOUR, Suit.SPADES),
            Card(Rank.THREE, Suit.SPADES),
            Card(Rank.TWO, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-8, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.FIVE, Suit.SPADES),
            Card(Rank.FOUR, Suit.SPADES),
            Card(Rank.THREE, Suit.SPADES),
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.ACE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-9, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_FOUR_OF_A_KIND_RANK+1, pokerHand.rank)
    }

    @Test
    fun fourOfAKindTEst() {
        var cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.ACE, Suit.CLUBS),
            Card(Rank.ACE, Suit.HEARTS),
            Card(Rank.ACE, Suit.DIAMONDS),
            Card(Rank.KING, Suit.SPADES),
        )
        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FOUR_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.ACE, Suit.CLUBS),
            Card(Rank.ACE, Suit.HEARTS),
            Card(Rank.ACE, Suit.DIAMONDS),
            Card(Rank.QUEEN, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FOUR_OF_A_KIND_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.TWO, Suit.CLUBS),
            Card(Rank.TWO, Suit.HEARTS),
            Card(Rank.TWO, Suit.DIAMONDS),
            Card(Rank.FOUR, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FOUR_OF_A_KIND_RANK+1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.TWO, Suit.CLUBS),
            Card(Rank.TWO, Suit.HEARTS),
            Card(Rank.TWO, Suit.DIAMONDS),
            Card(Rank.THREE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FOUR_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_FULL_HOUSE_RANK+1, pokerHand.rank)
    }

    @Test
    fun fullHouseTest() {
        var cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.ACE, Suit.CLUBS),
            Card(Rank.ACE, Suit.HEARTS),
            Card(Rank.KING, Suit.DIAMONDS),
            Card(Rank.KING, Suit.SPADES),
        )
        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FULL_HOUSE_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_FOUR_OF_A_KIND_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.ACE, Suit.CLUBS),
            Card(Rank.ACE, Suit.HEARTS),
            Card(Rank.QUEEN, Suit.DIAMONDS),
            Card(Rank.QUEEN, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FULL_HOUSE_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.TWO, Suit.CLUBS),
            Card(Rank.TWO, Suit.HEARTS),
            Card(Rank.FOUR, Suit.DIAMONDS),
            Card(Rank.FOUR, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FULL_HOUSE_RANK+1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.TWO, Suit.CLUBS),
            Card(Rank.TWO, Suit.HEARTS),
            Card(Rank.THREE, Suit.DIAMONDS),
            Card(Rank.THREE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FULL_HOUSE_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_FLUSH_RANK+1, pokerHand.rank)
    }

    @Test
    fun flushTest() {
        var cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.KING, Suit.SPADES),
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.NINE, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_FULL_HOUSE_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.KING, Suit.SPADES),
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.EIGHT, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FLUSH_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.SEVEN, Suit.SPADES),
            Card(Rank.FIVE, Suit.SPADES),
            Card(Rank.FOUR, Suit.SPADES),
            Card(Rank.THREE, Suit.SPADES),
            Card(Rank.TWO, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_STRAIGHT_RANK+1, pokerHand.rank)
    }

    @Test
    fun straightTest() {
        var cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.KING, Suit.DIAMONDS),
            Card(Rank.QUEEN, Suit.CLUBS),
            Card(Rank.JACK, Suit.DIAMONDS),
            Card(Rank.TEN, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_FLUSH_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.KING, Suit.DIAMONDS),
            Card(Rank.QUEEN, Suit.CLUBS),
            Card(Rank.JACK, Suit.DIAMONDS),
            Card(Rank.TEN, Suit.SPADES),
            Card(Rank.NINE, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.FIVE, Suit.CLUBS),
            Card(Rank.FOUR, Suit.DIAMONDS),
            Card(Rank.SIX, Suit.DIAMONDS),
            Card(Rank.THREE, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_STRAIGHT_RANK+1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.FIVE, Suit.CLUBS),
            Card(Rank.FOUR, Suit.DIAMONDS),
            Card(Rank.ACE, Suit.DIAMONDS),
            Card(Rank.THREE, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_STRAIGHT_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_THREE_OF_A_KIND_RANK+1, pokerHand.rank)
    }

    @Test
    fun threeOfAKindTest() {
        var cards = arrayListOf<Card>(
            Card(Rank.QUEEN, Suit.SPADES),
            Card(Rank.KING, Suit.DIAMONDS),
            Card(Rank.ACE, Suit.DIAMONDS),
            Card(Rank.ACE, Suit.CLUBS),
            Card(Rank.ACE, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_THREE_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.ACE, Suit.DIAMONDS),
            Card(Rank.ACE, Suit.CLUBS),
            Card(Rank.KING, Suit.DIAMONDS),
            Card(Rank.JACK, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_THREE_OF_A_KIND_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.TWO, Suit.DIAMONDS),
            Card(Rank.TWO, Suit.CLUBS),
            Card(Rank.THREE, Suit.DIAMONDS),
            Card(Rank.FOUR, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_THREE_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_TWO_PAIR_RANK+1, pokerHand.rank)
    }

    @Test
    fun twoPairTest() {
        var cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.ACE, Suit.DIAMONDS),
            Card(Rank.KING, Suit.CLUBS),
            Card(Rank.KING, Suit.DIAMONDS),
            Card(Rank.QUEEN, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_TWO_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_THREE_OF_A_KIND_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.KING, Suit.CLUBS),
            Card(Rank.KING, Suit.DIAMONDS),
            Card(Rank.ACE, Suit.DIAMONDS),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_TWO_PAIR_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.TWO, Suit.DIAMONDS),
            Card(Rank.THREE, Suit.CLUBS),
            Card(Rank.THREE, Suit.DIAMONDS),
            Card(Rank.FOUR, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_TWO_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_PAIR_RANK+1, pokerHand.rank)
    }

    @Test
    fun pairTest() {
        var cards = arrayListOf<Card>(
            Card(Rank.QUEEN, Suit.DIAMONDS),
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.KING, Suit.CLUBS),
            Card(Rank.JACK, Suit.SPADES),
            Card(Rank.ACE, Suit.DIAMONDS),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_TWO_PAIR_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TEN, Suit.SPADES),
            Card(Rank.ACE, Suit.DIAMONDS),
            Card(Rank.KING, Suit.CLUBS),
            Card(Rank.QUEEN, Suit.DIAMONDS),
            Card(Rank.ACE, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_PAIR_RANK - 1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.SIX, Suit.DIAMONDS),
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.THREE, Suit.CLUBS),
            Card(Rank.FOUR, Suit.SPADES),
            Card(Rank.TWO, Suit.DIAMONDS),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_PAIR_RANK + 1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.FOUR, Suit.DIAMONDS),
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.THREE, Suit.CLUBS),
            Card(Rank.FIVE, Suit.SPADES),
            Card(Rank.TWO, Suit.DIAMONDS),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_HIGH_CARD_RANK+1, pokerHand.rank)
    }

    @Test
    fun highCardTest() {
        var cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.KING, Suit.DIAMONDS),
            Card(Rank.QUEEN, Suit.CLUBS),
            Card(Rank.JACK, Suit.DIAMONDS),
            Card(Rank.NINE, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_HIGH_CARD_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_PAIR_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.ACE, Suit.SPADES),
            Card(Rank.KING, Suit.DIAMONDS),
            Card(Rank.QUEEN, Suit.CLUBS),
            Card(Rank.JACK, Suit.DIAMONDS),
            Card(Rank.EIGHT, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_HIGH_CARD_RANK-1, pokerHand.rank)

        cards = arrayListOf<Card>(
            Card(Rank.TWO, Suit.SPADES),
            Card(Rank.THREE, Suit.DIAMONDS),
            Card(Rank.FOUR, Suit.CLUBS),
            Card(Rank.FIVE, Suit.DIAMONDS),
            Card(Rank.SEVEN, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_HIGH_CARD_RANK, pokerHand.rank)
    }
}