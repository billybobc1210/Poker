import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class PokerHandTest {
    @Test
    fun tooManyCardsTest() {
        var validHand = true
        val cards = arrayListOf<PokerCard>(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.SPADES)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
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
        val cards = arrayListOf(
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
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
        val cards = arrayListOf(
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
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
        var cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.SPADES)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
        )
        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK + 9, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.KING, Suit.SPADES)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-1, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+8, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.EIGHT, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-2, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+7, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.EIGHT, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-3, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+6, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.EIGHT, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SIX, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-4, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+5, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.NINE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.EIGHT, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SIX, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-5, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+4, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.EIGHT, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SIX, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-6, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+3, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.SEVEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SIX, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
            PokerCard(StandardCard(Rank.THREE, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-7, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+2, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.SIX, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
            PokerCard(StandardCard(Rank.THREE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-8, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
            PokerCard(StandardCard(Rank.THREE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-9, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_FOUR_OF_A_KIND_RANK+1, pokerHand.rank)
    }

    @Test
    fun fourOfAKindTest() {
        var cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.ACE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.KING, Suit.SPADES)),
        )
        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FOUR_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.ACE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FOUR_OF_A_KIND_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.TWO, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.TWO, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FOUR_OF_A_KIND_RANK+1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.TWO, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.TWO, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.THREE, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FOUR_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_FULL_HOUSE_RANK+1, pokerHand.rank)
    }

    @Test
    fun fullHouseTest() {
        var cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.ACE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.KING, Suit.SPADES)),
        )
        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FULL_HOUSE_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_FOUR_OF_A_KIND_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.ACE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FULL_HOUSE_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.TWO, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.FOUR, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FULL_HOUSE_RANK+1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.TWO, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.THREE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.THREE, Suit.SPADES)),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FULL_HOUSE_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_FLUSH_RANK+1, pokerHand.rank)
    }

    @Test
    fun flushTest() {
        var cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.SPADES)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.SPADES)),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_FULL_HOUSE_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.SPADES)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.EIGHT, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FLUSH_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.SEVEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
            PokerCard(StandardCard(Rank.THREE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_STRAIGHT_RANK+1, pokerHand.rank)
    }

    @Test
    fun straightTest() {
        var cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.JACK, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_FLUSH_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.JACK, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FIVE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FOUR, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SIX, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.THREE, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_STRAIGHT_RANK+1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FIVE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FOUR, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.THREE, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_STRAIGHT_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_THREE_OF_A_KIND_RANK+1, pokerHand.rank)
    }

    @Test
    fun threeOfAKindTest() {
        var cards = arrayListOf(
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_THREE_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_THREE_OF_A_KIND_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TWO, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.THREE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_THREE_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_TWO_PAIR_RANK+1, pokerHand.rank)
    }

    @Test
    fun twoPairTest() {
        var cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.KING, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_TWO_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_THREE_OF_A_KIND_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_TWO_PAIR_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.THREE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.THREE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_TWO_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_PAIR_RANK+1, pokerHand.rank)
    }

    @Test
    fun pairTest() {
        var cards = arrayListOf(
            PokerCard(StandardCard(Rank.QUEEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_TWO_PAIR_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.KING, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_PAIR_RANK - 1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.SIX, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.THREE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FOUR, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.DIAMONDS)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_PAIR_RANK + 1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.FOUR, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.THREE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TWO, Suit.DIAMONDS)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_HIGH_CARD_RANK+1, pokerHand.rank)
    }

    @Test
    fun highCardTest() {
        var cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.JACK, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.SPADES)),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_HIGH_CARD_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_PAIR_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.JACK, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.EIGHT, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_HIGH_CARD_RANK-1, pokerHand.rank)

        cards = arrayListOf(
            PokerCard(StandardCard(Rank.TWO, Suit.SPADES)),
            PokerCard(StandardCard(Rank.THREE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FOUR, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.SPADES)),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_HIGH_CARD_RANK, pokerHand.rank)
    }
}