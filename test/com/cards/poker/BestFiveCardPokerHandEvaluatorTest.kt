package com.cards.poker

import com.cards.Rank
import com.cards.StandardCard
import com.cards.Suit
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BestFiveCardPokerHandEvaluatorTest {
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
            StandardCard(Rank.FIVE, Suit.CLUBS),
            StandardCard(Rank.TWO, Suit.DIAMONDS),
        )

        try {
            BestFiveCardPokerHandEvaluator(cards)
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
            BestFiveCardPokerHandEvaluator(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertFalse(validHand)
    }

    @Test
    fun duplicateCardsTest1() {
        var validHand = true
        val cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.TEN, Suit.CLUBS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.HEARTS),
            StandardCard(Rank.NINE, Suit.HEARTS),
        )

        try {
            BestFiveCardPokerHandEvaluator(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertTrue(validHand)
    }

    @Test
    fun duplicateCardsTest2() {
        var validHand = true
        val cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.TEN, Suit.CLUBS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.HEARTS),
            StandardCard(Rank.NINE, Suit.HEARTS),
        )

        try {
            BestFiveCardPokerHandEvaluator(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertTrue(validHand)
    }

    @Test
    fun duplicateCardsTest3() {
        var validHand = true
        val cards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.HEARTS),
            StandardCard(Rank.NINE, Suit.HEARTS),
        )

        try {
            BestFiveCardPokerHandEvaluator(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertFalse(validHand)
    }

    @Test
    fun sevenCardHandTest1() {
        val cardsForSevenCardHand = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.TEN, Suit.CLUBS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.FIVE, Suit.SPADES),
            StandardCard(Rank.NINE, Suit.HEARTS),
            StandardCard(Rank.EIGHT, Suit.SPADES),
        )
        val bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(cardsForSevenCardHand)

        val expectedBestFiveCardHandCards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.TEN, Suit.CLUBS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.FIVE, Suit.SPADES),
        )
        val expectedBestFiveCardHand = PokerHand(expectedBestFiveCardHandCards)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }

    @Test
    fun sevenCardHandTest2() {
        val cardsForSevenCardHand = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.TEN, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.NINE, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.CLUBS),
        )
        val bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(cardsForSevenCardHand)

        val expectedBestFiveCardHandCards = setOf(
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.TEN, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.NINE, Suit.DIAMONDS),
        )
        val expectedBestFiveCardHand = PokerHand(expectedBestFiveCardHandCards)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }

    @Test
    fun sevenCardHandTest3() {
        val cardsForSevenCardHand = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.SEVEN, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.SEVEN, Suit.CLUBS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.NINE, Suit.DIAMONDS),
            StandardCard(Rank.NINE, Suit.CLUBS),
        )
        val bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(cardsForSevenCardHand)

        val expectedBestFiveCardHandCards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.SEVEN, Suit.CLUBS),
            StandardCard(Rank.NINE, Suit.DIAMONDS),
            StandardCard(Rank.NINE, Suit.CLUBS),
        )
        val expectedBestFiveCardHand = PokerHand(expectedBestFiveCardHandCards)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }

    @Test
    fun fiveCardHandTest() {
        val fiveCardHand = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.SEVEN, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.SEVEN, Suit.CLUBS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
        )
        val bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(fiveCardHand)
        val expectedBestFiveCardHand = PokerHand(fiveCardHand)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }

    @Test
    fun sixCardHandTest() {
        val sixCardHand = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.SEVEN, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.SEVEN, Suit.CLUBS),
            StandardCard(Rank.FIVE, Suit.DIAMONDS),
            StandardCard(Rank.SEVEN, Suit.HEARTS),
        )
        val bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(sixCardHand)

        val expectedBestFiveCardPokerHandCards = setOf(
            StandardCard(Rank.ACE, Suit.SPADES),
            StandardCard(Rank.SEVEN, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.SEVEN, Suit.CLUBS),
            StandardCard(Rank.SEVEN, Suit.HEARTS),
        )
        val expectedBestFiveCardHand = PokerHand(expectedBestFiveCardPokerHandCards)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }
}