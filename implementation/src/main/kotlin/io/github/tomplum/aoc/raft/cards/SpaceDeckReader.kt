package io.github.tomplum.aoc.raft.cards

/**
 * Reads the starting decks of [CombatGame] or [RecursiveCombatGame]
 * and produces a pair of [SpaceCardDeck] for each player.
 */
class SpaceDeckReader private constructor() {
    companion object {
        fun parse(data: String): Pair<SpaceCardDeck, SpaceCardDeck> {
            val decks = data.split("\n\n")
            val p1 = decks[0].parseDeck()
            val p2 = decks[1].parseDeck()
            return Pair(p1, p2)
        }

        private fun String.parseDeck() = split("\n").drop(1).map { it.toInt() }.let { cards -> SpaceCardDeck(cards) }
    }
}