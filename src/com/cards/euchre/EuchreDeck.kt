package com.cards.euchre

import com.cards.Rank
import com.cards.StandardCard
import com.cards.StandardDeck
import com.cards.Suit

class EuchreDeck : StandardDeck() {
    override fun initDeck() {
        cards = arrayListOf(
            StandardCard(Rank.NINE, Suit.CLUBS),
            StandardCard(Rank.TEN, Suit.CLUBS),
            StandardCard(Rank.JACK, Suit.CLUBS),
            StandardCard(Rank.QUEEN, Suit.CLUBS),
            StandardCard(Rank.KING, Suit.CLUBS),
            StandardCard(Rank.ACE, Suit.CLUBS),
            StandardCard(Rank.NINE, Suit.DIAMONDS),
            StandardCard(Rank.TEN, Suit.DIAMONDS),
            StandardCard(Rank.JACK, Suit.DIAMONDS),
            StandardCard(Rank.QUEEN, Suit.DIAMONDS),
            StandardCard(Rank.KING, Suit.DIAMONDS),
            StandardCard(Rank.ACE, Suit.DIAMONDS),
            StandardCard(Rank.NINE, Suit.HEARTS),
            StandardCard(Rank.TEN, Suit.HEARTS),
            StandardCard(Rank.JACK, Suit.HEARTS),
            StandardCard(Rank.QUEEN, Suit.HEARTS),
            StandardCard(Rank.KING, Suit.HEARTS),
            StandardCard(Rank.ACE, Suit.HEARTS),
            StandardCard(Rank.NINE, Suit.SPADES),
            StandardCard(Rank.TEN, Suit.SPADES),
            StandardCard(Rank.JACK, Suit.SPADES),
            StandardCard(Rank.QUEEN, Suit.SPADES),
            StandardCard(Rank.KING, Suit.SPADES),
            StandardCard(Rank.ACE, Suit.SPADES),
        )
    }
}