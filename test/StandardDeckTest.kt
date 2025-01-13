import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StandardDeckTest {
    @Test
    fun shuffleTest() {
        val deck = StandardDeck()
        deck.shuffle();
        assertEquals(52, deck.cardsRemaining())

        val cardsDealt = mutableMapOf<String, Boolean?>()

        for (i in 0..51) {
            val card = deck.dealTopCard()
            assertEquals(51-i, deck.cardsRemaining())
            cardsDealt.put(card.toString(), true)
        }

        Rank.allRanks().forEach { rank ->
            Suit.allSuits().forEach { suit ->
                val cardFound = cardsDealt.get(StandardCard(rank, suit).toString()) ?: false
                assertTrue(cardFound)
            }
        }
    }
 }
