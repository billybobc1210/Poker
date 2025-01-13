package com.cards

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class StandardCardTest {
    @Test
    fun cardEqualityTest() {
        val c1 = StandardCard(Rank.THREE, Suit.DIAMONDS)
        val c2 = StandardCard(Rank.THREE, Suit.DIAMONDS)
        assertEquals(c1, c2)
    }

    @Test
    fun cardInequalityTest() {
        val c1 = StandardCard(Rank.THREE, Suit.DIAMONDS)
        val c2 = StandardCard(Rank.FIVE, Suit.CLUBS)
        assertNotEquals(c1, c2)
    }
}