package com.cards.poker

import com.cards.Rank
import com.cards.StandardCard
import com.cards.Suit
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PokerHandTest {
    @Test
    fun tooManyCardsTest() {
        var validHand = true
        val cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.SPADES),
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.DIAMONDS),
        )

        try {
            PokerHand(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertFalse(validHand)
    }

    @Test
    fun tooFewCardsTest() {
        var validHand = true
        val cards = setOf(
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.DIAMONDS),
        )

        try {
            PokerHand(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertFalse(validHand)
    }

    @Test
    fun duplicateCardsTest() {
        var validHand = true
        val cards = setOf(
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.DIAMONDS),
        )

        try {
            PokerHand(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertFalse(validHand)
    }

    @Test
    fun straightFlushTest() {
        var cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.SPADES),
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.TEN, Suit.SPADES),
        )
        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK + 9, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())

        cards = setOf(
            StandardCard(Rank.KING, Suit.SPADES),
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-1, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+8, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())

        cards = setOf(
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.SPADES),
            StandardCard(Rank.EIGHT, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-2, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+7, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())

        cards = setOf(
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.SPADES),
            StandardCard(Rank.EIGHT, Suit.SPADES),
            StandardCard(Rank.SEVEN, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-3, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+6, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())

        cards = setOf(
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.SPADES),
            StandardCard(Rank.EIGHT, Suit.SPADES),
            StandardCard(Rank.SEVEN, Suit.SPADES),
            StandardCard(Rank.SIX, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-4, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+5, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())

        cards = setOf(
            StandardCard(Rank.NINE, Suit.SPADES),
            StandardCard(Rank.EIGHT, Suit.SPADES),
            StandardCard(Rank.SEVEN, Suit.SPADES),
            StandardCard(Rank.SIX, Suit.SPADES),
            StandardCard(Rank.FIVE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-5, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+4, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())

        cards = setOf(
            StandardCard(Rank.EIGHT, Suit.SPADES),
            StandardCard(Rank.SEVEN, Suit.SPADES),
            StandardCard(Rank.SIX, Suit.SPADES),
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.FOUR, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-6, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+3, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())

        cards = setOf(
            StandardCard(Rank.SEVEN, Suit.SPADES),
            StandardCard(Rank.SIX, Suit.SPADES),
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.FOUR, Suit.SPADES),
            StandardCard(Rank.THREE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-7, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+2, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())

        cards = setOf(
            StandardCard(Rank.SIX, Suit.SPADES),
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.FOUR, Suit.SPADES),
            StandardCard(Rank.THREE, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-8, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())

        cards = setOf(
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.FOUR, Suit.SPADES),
            StandardCard(Rank.THREE, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_FLUSH_RANK-9, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_FOUR_OF_A_KIND_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isStraightFlush())
    }

    @Test
    fun fourOfAKindTest() {
        var cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.CLUBS),
            StandardCard(Rank.ACE, Suit.HEARTS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.KING, Suit.SPADES),
        )
        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FOUR_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_FLUSH_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isFourOfAKind())

        cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.CLUBS),
            StandardCard(Rank.ACE, Suit.HEARTS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.QUEEN, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FOUR_OF_A_KIND_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isFourOfAKind())

        cards = setOf(
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.CLUBS),
            StandardCard(Rank.TWO, Suit.HEARTS),
            StandardCard(Rank.TWO, Suit.DIAMONDS),
            StandardCard(Rank.FOUR, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FOUR_OF_A_KIND_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isFourOfAKind())

        cards = setOf(
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.CLUBS),
            StandardCard(Rank.TWO, Suit.HEARTS),
            StandardCard(Rank.TWO, Suit.DIAMONDS),
            StandardCard(Rank.THREE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FOUR_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_FULL_HOUSE_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isFourOfAKind())
    }

    @Test
    fun fullHouseTest() {
        var cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.CLUBS),
            StandardCard(Rank.ACE, Suit.HEARTS),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.KING, Suit.SPADES),
        )
        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FULL_HOUSE_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_FOUR_OF_A_KIND_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isFullHouse())

        cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.CLUBS),
            StandardCard(Rank.ACE, Suit.HEARTS),
            StandardCard(Rank.QUEEN, Suit.DIAMONDS),
            StandardCard(Rank.QUEEN, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FULL_HOUSE_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isFullHouse())

        cards = setOf(
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.CLUBS),
            StandardCard(Rank.TWO, Suit.HEARTS),
            StandardCard(Rank.FOUR, Suit.DIAMONDS),
            StandardCard(Rank.FOUR, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FULL_HOUSE_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isFullHouse())

        cards = setOf(
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.CLUBS),
            StandardCard(Rank.TWO, Suit.HEARTS),
            StandardCard(Rank.THREE, Suit.DIAMONDS),
            StandardCard(Rank.THREE, Suit.SPADES),
        )
        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FULL_HOUSE_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_FLUSH_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isFullHouse())
    }

    @Test
    fun flushTest() {
        var cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.SPADES),
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_FULL_HOUSE_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isFlush())

        cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.SPADES),
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.EIGHT, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_FLUSH_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isFlush())

        cards = setOf(
            StandardCard(Rank.SEVEN, Suit.SPADES),
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.FOUR, Suit.SPADES),
            StandardCard(Rank.THREE, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_FLUSH_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_STRAIGHT_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isFlush())
    }

    @Test
    fun straightTest() {
        var cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.QUEEN, Suit.CLUBS),
            StandardCard(Rank.JACK, Suit.DIAMONDS),
            StandardCard(Rank.TEN, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_FLUSH_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isStraight())

        cards = setOf(
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.QUEEN, Suit.CLUBS),
            StandardCard(Rank.JACK, Suit.DIAMONDS),
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_STRAIGHT_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isStraight())

        cards = setOf(
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.FIVE, Suit.CLUBS),
            StandardCard(Rank.FOUR, Suit.DIAMONDS),
            StandardCard(Rank.SIX, Suit.DIAMONDS),
            StandardCard(Rank.THREE, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_STRAIGHT_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isStraight())

        cards = setOf(
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.FIVE, Suit.CLUBS),
            StandardCard(Rank.FOUR, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.THREE, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_STRAIGHT_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_THREE_OF_A_KIND_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isStraight())
    }

    @Test
    fun threeOfAKindTest() {
        var cards = setOf(
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.CLUBS),
            StandardCard(Rank.ACE, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_THREE_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_STRAIGHT_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isThreeOfAKind())

        cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.CLUBS),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.JACK, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_THREE_OF_A_KIND_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isThreeOfAKind())

        cards = setOf(
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.DIAMONDS),
            StandardCard(Rank.TWO, Suit.CLUBS),
            StandardCard(Rank.THREE, Suit.DIAMONDS),
            StandardCard(Rank.FOUR, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_THREE_OF_A_KIND_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_TWO_PAIR_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isThreeOfAKind())
    }

    @Test
    fun twoPairTest() {
        var cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.KING, Suit.CLUBS),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.QUEEN, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_TWO_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_THREE_OF_A_KIND_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isTwoPair())

        cards = setOf(
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.CLUBS),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_TWO_PAIR_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isTwoPair())

        cards = setOf(
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.DIAMONDS),
            StandardCard(Rank.THREE, Suit.CLUBS),
            StandardCard(Rank.THREE, Suit.DIAMONDS),
            StandardCard(Rank.FOUR, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_TWO_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_PAIR_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isTwoPair())
    }

    @Test
    fun pairTest() {
        var cards = setOf(
            StandardCard(Rank.QUEEN, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.CLUBS),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_TWO_PAIR_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isPair())

        cards = setOf(
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.KING, Suit.CLUBS),
            StandardCard(Rank.QUEEN, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_PAIR_RANK - 1, pokerHand.rank)
        assertTrue(pokerHand.isPair())

        cards = setOf(
            StandardCard(Rank.SIX, Suit.DIAMONDS),
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.THREE, Suit.CLUBS),
            StandardCard(Rank.FOUR, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.DIAMONDS),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_PAIR_RANK + 1, pokerHand.rank)
        assertTrue(pokerHand.isPair())

        cards = setOf(
            StandardCard(Rank.FOUR, Suit.DIAMONDS),
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.THREE, Suit.CLUBS),
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.TWO, Suit.DIAMONDS),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_PAIR_RANK, pokerHand.rank)
        assertEquals(PokerHand.MAX_HIGH_CARD_RANK+1, pokerHand.rank)
        assertTrue(pokerHand.isPair())
    }

    @Test
    fun highCardTest() {
        var cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.QUEEN, Suit.CLUBS),
            StandardCard(Rank.JACK, Suit.DIAMONDS),
            StandardCard(Rank.NINE, Suit.SPADES),
        )

        var pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_HIGH_CARD_RANK, pokerHand.rank)
        assertEquals(PokerHand.MIN_PAIR_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isHighCardHand())

        cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.QUEEN, Suit.CLUBS),
            StandardCard(Rank.JACK, Suit.DIAMONDS),
            StandardCard(Rank.EIGHT, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MAX_HIGH_CARD_RANK-1, pokerHand.rank)
        assertTrue(pokerHand.isHighCardHand())

        cards = setOf(
            StandardCard(Rank.TWO, Suit.SPADES),
            StandardCard(Rank.THREE, Suit.DIAMONDS),
            StandardCard(Rank.FOUR, Suit.CLUBS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.SEVEN, Suit.SPADES),
        )

        pokerHand = PokerHand(cards)
        assertEquals(PokerHand.MIN_HIGH_CARD_RANK, pokerHand.rank)
        assertTrue(pokerHand.isHighCardHand())
    }
}