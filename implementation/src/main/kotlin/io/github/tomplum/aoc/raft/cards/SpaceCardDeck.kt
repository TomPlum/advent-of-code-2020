package io.github.tomplum.aoc.raft.cards

import java.util.*

/**
 * A deck of space cards used in a [CombatGame] or a [RecursiveCombatGame].
 * @param startingOrder The starting cards for the deck in order.
 */
class SpaceCardDeck(startingOrder: List<Int>) {
    private val cards = LinkedList<Int>()

    init {
        startingOrder.forEach { card -> cards.addLast(card) }
    }

    /**
     * Adds a pair of cards to the bottom of the deck.
     * The first card in the pair is added first, and the second last.
     * @param pair The cards to be added.
     */
    fun add(pair: Pair<Int, Int>) {
        cards.addLast(pair.first)
        cards.addLast(pair.second)
    }

    /**
     * Draws a single card from the deck.
     * The card is removed upon drawing.
     * @return The value of the card.
     */
    fun draw(): Int = cards.pollFirst()

    /**
     * Checks if the deck is empty.
     * @return true if empty, else false.
     */
    fun isEmpty(): Boolean = cards.isEmpty()

    /**
     * Checks how many cards are currently in the deck.
     * @return The number of cards.
     */
    fun size(): Int = cards.size

    /**
     * Creates a new deck by cutting the first [n] cards from the current deck.
     * The cut cards are removed from this deck.
     * @param n The number of cards to take.
     * @return A new deck containing only the cut cards.
     */
    fun getCards(n: Int) = SpaceCardDeck(cards.take(n))

    /**
     * Gets all the cards from the deck.
     * @return A list of all the card values, in order.
     */
    fun getCards(): List<Int> = cards.toList()

    /**
     * Calculates the score of the deck in its current state.
     * The bottom card in their deck is worth the value of the card multiplied by 1, the second-from-the-bottom
     * card is worth the value of the card multiplied by 2, and so on.
     * @return The score of the deck.
     */
    fun getDeckScore(): Int = cards.reversed().mapIndexed { i, card -> card * (i + 1) }.sum()

    override fun equals(other: Any?): Boolean {
        if (other !is SpaceCardDeck) return false
        return cards == other.cards
    }

    override fun hashCode(): Int {
        return cards.hashCode()
    }

    override fun toString(): String = cards.toString().removeSurrounding("[", "]")
}