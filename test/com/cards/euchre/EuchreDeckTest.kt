package com.cards.euchre

import com.cards.Rank
import com.cards.StandardCard
import com.cards.Suit
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EuchreDeckTest {
    @Test
    fun shuffleTest() {
        val deck = EuchreDeck()
        deck.shuffle()
        assertEquals(24, deck.cardsRemaining())

        val cardsDealt = mutableMapOf<String, Boolean?>()

        for (i in 0..23) {
            val card = deck.dealTopCard()
            assertEquals(23-i, deck.cardsRemaining())
            cardsDealt["$card"] = true
        }

        val euchreRanks = arrayOf(
            Rank.NINE,
            Rank.TEN,
            Rank.JACK,
            Rank.QUEEN,
            Rank.KING,
            Rank.ACE,
        )

        euchreRanks.forEach { rank ->
            enumValues<Suit>().forEach { suit ->
                val card = StandardCard(rank, suit)
                val cardFound = cardsDealt["$card"] ?: false
                assertTrue(cardFound)
            }
        }

        val nonEuchreRanks = arrayOf(
            Rank.TWO,
            Rank.THREE,
            Rank.FOUR,
            Rank.FIVE,
            Rank.SIX,
            Rank.SEVEN,
            Rank.EIGHT,
        )

        nonEuchreRanks.forEach { rank ->
            enumValues<Suit>().forEach { suit ->
                val card = StandardCard(rank, suit)
                val cardFound = cardsDealt["$card"] ?: false
                assertFalse(cardFound)
            }
        }

    }
}