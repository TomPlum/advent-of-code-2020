package io.github.tomplum.aoc.raft.cards

import io.github.tomplum.libs.logging.AdventLogger

/**
 * Then, the game consists of a series of rounds: both players draw their top card, and the player with the
 * higher-valued card wins the round.
 *
 * The winner keeps both cards, placing them on the bottom of their own deck so that the winner's card is above
 * the other card.
 *
 * If this causes a player to have all of the cards, they win, and the game ends.
 *
 * @param p1 Player 1's deck of cards.
 * @param p2 Player 2's deck of cards.
 */
class CombatGame(private val p1: SpaceCardDeck, private val p2: SpaceCardDeck) {
    /**
     * Simulates a complete game using the given [p1] and [p2] decks.
     * @return The deck score of the winning player.
     */
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