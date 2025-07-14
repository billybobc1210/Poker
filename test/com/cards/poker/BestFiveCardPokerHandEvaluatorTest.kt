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
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.SPADES)),
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.TWO, Suit.DIAMONDS)),
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
            PokerCard(StandardCard(Rank.QUEEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.JACK, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
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
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
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
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.TEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
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
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
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
        var cardsForSevenCardHand = setOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.EIGHT, Suit.SPADES)),
        )
        var bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(cardsForSevenCardHand)

        var expectedBestFiveCardHandCards = setOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
        )
        var expectedBestFiveCardHand = PokerHand(expectedBestFiveCardHandCards)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }

    @Test
    fun sevenCardHandTest2() {
        var cardsForSevenCardHand = setOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.CLUBS)),
        )
        var bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(cardsForSevenCardHand)

        var expectedBestFiveCardHandCards = setOf(
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
        )
        var expectedBestFiveCardHand = PokerHand(expectedBestFiveCardHandCards)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }

    @Test
    fun sevenCardHandTest3() {
        var cardsForSevenCardHand = setOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.CLUBS)),
        )
        var bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(cardsForSevenCardHand)

        var expectedBestFiveCardHandCards = setOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.CLUBS)),
        )
        var expectedBestFiveCardHand = PokerHand(expectedBestFiveCardHandCards)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }

    @Test
    fun fiveCardHandTest() {
        var fiveCardHand = setOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
        )
        var bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(fiveCardHand)
        var expectedBestFiveCardHand = PokerHand(fiveCardHand)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }

    @Test
    fun sixCardHandTest() {
        var sixCardHand = setOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.HEARTS)),
        )
        var bestFiveCardPokerHandEvaluator = BestFiveCardPokerHandEvaluator(sixCardHand)

        var expectedBestFiveCardPokerHandCards = setOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.HEARTS)),
        )
        var expectedBestFiveCardHand = PokerHand(expectedBestFiveCardPokerHandCards)

        assertEquals(expectedBestFiveCardHand.rank, bestFiveCardPokerHandEvaluator.bestFiveCardPokerHand.rank)
    }
}