package io.github.tomplum.aoc.raft.cards

import io.github.tomplum.aoc.raft.cards.GameStatus.*
import io.github.tomplum.libs.logging.AdventLogger

class RecursiveCombatGame {

    fun simulate(gameNumber: Int, p1: SpaceCardDeck, p2: SpaceCardDeck): GameResult {
        var round = 1

        val playerOneDeckStates = mutableListOf<Int>()
        val playerTwoDeckStates = mutableListOf<Int>()

        while(!p1.isEmpty() && !p2.isEmpty()) {

            val p1draw = p1.draw()
            val p2draw = p2.draw()

            if (playerOneDeckStates.contains(p1.hashCode()) && playerTwoDeckStates.contains(p2.hashCode())) {
                AdventLogger.trace("Player 1 wins as their deck state has been seen before")
                return GameResult(PLAYER_1_WIN, p1, p2)
            }

            logRound(round, gameNumber, p1draw, p2draw, p1, p2)

            var subGameResult: GameResult? = null
            if (p1draw <= p1.size() && p2draw <= p2.size()) {
                AdventLogger.info("Playing a sub-game to determine the winner..\n")

                subGameResult = simulate(gameNumber + 1, p1.getCards(p1draw), p2.getCards(p2draw))

                val subGameWinner = if (subGameResult.getWinner() == 1) 1 else 2
                AdventLogger.info("The winner of game $gameNumber is player $subGameWinner\n")
                AdventLogger.info("...anyway, back to game ${gameNumber - 1}")
            }

            playerOneDeckStates.add(p1.hashCode())
            playerTwoDeckStates.add(p2.hashCode())

            when {
                subGameResult?.status == PLAYER_1_WIN -> p1.add(Pair(p1draw, p2draw))
                subGameResult?.status == PLAYER_2_WIN ->  p2.add(Pair(p2draw, p1draw))
                p1draw > p2draw -> p1.add(Pair(p1draw, p2draw))
                p2draw > p1draw -> p2.add(Pair(p2draw, p1draw))
            }

            round++
        }

        logPostGameResults(p1, p2)

        return when {
            p1.isEmpty() -> GameResult(PLAYER_2_WIN, p1, p2)
            p2.isEmpty() -> GameResult(PLAYER_1_WIN, p1, p2)
            else -> throw IllegalStateException("Neither deck is empty but the game has finished!")
        }
    }

    private fun logRound(round: Int, gameNumber: Int, p1draw: Int, p2draw: Int, p1: SpaceCardDeck, p2: SpaceCardDeck) {
        AdventLogger.info("-- Round $round (Game $gameNumber) --")
        AdventLogger.info("Player 1's deck: $p1draw, $p1")
        AdventLogger.info("Player 2's deck: $p2draw, $p2")
        AdventLogger.info("Player 1 plays: $p1draw")
        AdventLogger.info("Player 2 plays: $p2draw")
        val winner: Int = if (p1draw > p2draw) 1 else 2
        AdventLogger.info("Player $winner wins round $round of game $gameNumber!\n")
    }

    private fun logPostGameResults(p1: SpaceCardDeck, p2: SpaceCardDeck) {
        AdventLogger.info("== Post-game results ==")
        AdventLogger.info("Player 1's deck: $p1")
        AdventLogger.info("Player 2's deck: $p2")
    }
}