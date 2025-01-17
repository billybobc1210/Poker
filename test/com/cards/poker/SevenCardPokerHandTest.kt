package com.cards.poker

import com.cards.Rank
import com.cards.StandardCard
import com.cards.Suit
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SevenCardPokerHandTest {
    @Test
    fun tooManyCardsTest() {
        var validHand = true
        val cards = arrayListOf(
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
            SevenCardPokerHand(cards)
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
            PokerCard(StandardCard(Rank.FIVE, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.TWO, Suit.DIAMONDS)),
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
        val cards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
        )

        try {
            PokerHand(cards)
        } catch (e: Exception) {
            validHand = false
        }

        assertFalse(validHand)
    }

    @Test
    fun test1() {
        var cardsForSevenCardHand = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.NINE, Suit.HEARTS)),
            PokerCard(StandardCard(Rank.EIGHT, Suit.SPADES)),
        )
        var sevenCardPokerHand = SevenCardPokerHand(cardsForSevenCardHand)

        var expectedBestFiveCardHandCards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.SPADES)),
        )
        var expectedBestFiveCardHand = PokerHand(expectedBestFiveCardHandCards)

        assertEquals(expectedBestFiveCardHand.rank, sevenCardPokerHand.bestFiveCardPokerHand.rank)
    }

    @Test
    fun test2() {
        var cardsForSevenCardHand = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.CLUBS)),
        )
        var sevenCardPokerHand = SevenCardPokerHand(cardsForSevenCardHand)

        var expectedBestFiveCardHandCards = arrayListOf(
            PokerCard(StandardCard(Rank.KING, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.TEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
        )
        var expectedBestFiveCardHand = PokerHand(expectedBestFiveCardHandCards)

        assertEquals(expectedBestFiveCardHand.rank, sevenCardPokerHand.bestFiveCardPokerHand.rank)
    }

    @Test
    fun test3() {
        var cardsForSevenCardHand = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.FIVE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.CLUBS)),
        )
        var sevenCardPokerHand = SevenCardPokerHand(cardsForSevenCardHand)

        var expectedBestFiveCardHandCards = arrayListOf(
            PokerCard(StandardCard(Rank.ACE, Suit.SPADES)),
            PokerCard(StandardCard(Rank.ACE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.SEVEN, Suit.CLUBS)),
            PokerCard(StandardCard(Rank.NINE, Suit.DIAMONDS)),
            PokerCard(StandardCard(Rank.NINE, Suit.CLUBS)),
        )
        var expectedBestFiveCardHand = PokerHand(expectedBestFiveCardHandCards)

        assertEquals(expectedBestFiveCardHand.rank, sevenCardPokerHand.bestFiveCardPokerHand.rank)
    }
}