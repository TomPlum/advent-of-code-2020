package io.github.tomplum.aoc.raft.cards

import io.github.tomplum.libs.logging.AdventLogger

data class CombatGame(val p1: SpaceCardDeck, val p2: SpaceCardDeck) {
    fun simulate(): Int {
        var round = 1
        while(!p1.isEmpty() && !p2.isEmpty()) {
            val p1draw = p1.draw()
            val p2draw = p2.draw()

            logRound(round, p1draw, p2draw)

            when {
                p1draw > p2draw -> p1.add(Pair(p1draw, p2draw))
                p2draw > p1draw -> p2.add(Pair(p2draw, p1draw))
            }

            round++
        }

        logPostGameResults()

        return when {
            p1.isEmpty() -> p2.getDeckScore()
            p2.isEmpty() -> p1.getDeckScore()
            else -> throw IllegalStateException("Neither deck is empty but the game has finished!")
        }
    }

    private fun logRound(round: Int, p1draw: Int, p2draw: Int) {
        AdventLogger.info("-- Round $round --")
        AdventLogger.info("Player 1's deck: $p1")
        AdventLogger.info("Player 2's deck: $p2")
        AdventLogger.info("Player 1 plays: $p1draw")
        AdventLogger.info("Player 2 plays: $p2draw")
        val winner: Int = if (p1draw > p2draw) 1 else 2
        AdventLogger.info("Player $winner wins the round!\n")
    }

    private fun logPostGameResults() {
        AdventLogger.info("== Post-game results ==")
        AdventLogger.info("Player 1's deck: $p1")
        AdventLogger.info("Player 2's deck: $p2")
    }
}