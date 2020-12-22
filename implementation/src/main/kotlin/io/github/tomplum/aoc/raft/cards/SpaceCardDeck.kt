package io.github.tomplum.aoc.raft.cards

import java.util.*

class SpaceCardDeck(startingOrder: List<Int>) {
    val cards = LinkedList<Int>()

    init {
        startingOrder.forEach { card -> cards.addLast(card) }
    }

    fun add(pair: Pair<Int, Int>) {
        cards.addLast(pair.first)
        cards.addLast(pair.second)
    }

    fun draw(): Int = cards.pollFirst()

    fun isEmpty(): Boolean = cards.isEmpty()

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