package io.github.tomplum.aoc.raft.cards

class CombatGameReader private constructor() {
    companion object {
        fun parse(data: String): CombatGame {
            val decks = data.split("\n\n")
            val p1 = decks[0].parseDeck()
            val p2 = decks[1].parseDeck()
            return CombatGame(p1, p2)
        }

        private fun String.parseDeck() = split("\n").drop(1).map { it.toInt() }.let { cards -> SpaceCardDeck(cards) }
    }
}