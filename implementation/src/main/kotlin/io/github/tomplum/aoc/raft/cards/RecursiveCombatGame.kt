package io.github.tomplum.aoc.raft.cards

import io.github.tomplum.aoc.raft.cards.Player.*
import io.github.tomplum.libs.logging.AdventLogger

/**
 * Recursive Combat still starts by splitting the cards into two decks like the regular [CombatGame].
 *
 * Then, the game consists of a series of rounds with a few changes:
 *
 * - Before either player deals a card, if there was a previous round in this game that had exactly the same cards in
 *   the same order in the same players' decks, the game instantly ends in a win for player 1.
 *   Previous rounds from other games are not considered. (This prevents infinite games of Recursive Combat, which
 *   everyone agrees is a bad idea.)
 *
 * - Otherwise, this round's cards must be in a new configuration; the players begin the round by each drawing the top
 *   card of their deck as normal.
 *
 * - If both players have at least as many cards remaining in their deck as the value of the card they just drew,
 *   the winner of the round is determined by playing a new game of Recursive Combat (see below).
 *
 * - Otherwise, at least one player must not have enough cards left in their deck to recurse; the winner of the
 *   round is the player with the higher-value card.
 *
 * As in regular Combat, the winner of the round (even if they won the round by winning a sub-game) takes the two
 * cards dealt at the beginning of the round and places them on the bottom of their own deck (again so that the
 * winner's card is above the other card).
 *
 * Note that the winner's card might be the lower-valued of the two cards if they won the round due to winning a
 * sub-game. If collecting cards by winning the round causes a player to have all of the cards, they win,
 * and the game ends.
 *
 * @param enabledLogging Whether to log the game state. Incurs a large performance cost.
 */
class RecursiveCombatGame(private val enabledLogging: Boolean = false) {

    private var gameNumber = 1

    /**
     * Simulates a single game using the given decks of space cards.
     * @param p1 The state of Player 1's deck for the current game simulation.
     * @param p2 The state of Player 2's deck for the current game simulation.
     * @return The result after a winner is determined.
     */
    fun simulate(p1: SpaceCardDeck, p2: SpaceCardDeck): GameResult {
        AdventLogger.info("== Game {} ==\n", gameNumber)
        var round = 1

        val playerOneDeckStates = mutableListOf<Int>()
        val playerTwoDeckStates = mutableListOf<Int>()

        while(!p1.isEmpty() && !p2.isEmpty()) {

            val p1draw = p1.draw()
            val p2draw = p2.draw()

            if (playerOneDeckStates.contains(p1.hashCode()) && playerTwoDeckStates.contains(p2.hashCode())) {
                return GameResult(PLAYER_1, p1, p2)
            }

            logRound(round, gameNumber, p1draw, p2draw, p1, p2)

            var subGameResult: GameResult? = null
            if (p1draw <= p1.size() && p2draw <= p2.size()) {
                AdventLogger.info("Playing a sub-game to determine the winner..\n")

                gameNumber++
                subGameResult = simulate(p1.getCards(p1draw), p2.getCards(p2draw))

                logSubGameResult(subGameResult)
                gameNumber--
            }

            playerOneDeckStates.add(p1.hashCode())
            playerTwoDeckStates.add(p2.hashCode())

            when {
                subGameResult?.winner == PLAYER_1 -> p1.add(Pair(p1draw, p2draw))
                subGameResult?.winner == PLAYER_2 ->  p2.add(Pair(p2draw, p1draw))
                p1draw > p2draw -> p1.add(Pair(p1draw, p2draw))
                p2draw > p1draw -> p2.add(Pair(p2draw, p1draw))
            }

            round++
        }

        if (gameNumber == 1) logPostGameResults(p1, p2)

        return when {
            p1.isEmpty() -> GameResult(PLAYER_2, p1, p2)
            p2.isEmpty() -> GameResult(PLAYER_1, p1, p2)
            else -> throw IllegalStateException("Neither deck is empty but the game has finished!")
        }
    }

    private fun logRound(round: Int, gameNumber: Int, p1draw: Int, p2draw: Int, p1: SpaceCardDeck, p2: SpaceCardDeck) {
        if (enabledLogging) {
            AdventLogger.info("-- Round $round (Game $gameNumber) --")
            AdventLogger.info("Player 1's deck: $p1draw, $p1")
            AdventLogger.info("Player 2's deck: $p2draw, $p2")
            AdventLogger.info("Player 1 plays: $p1draw")
            AdventLogger.info("Player 2 plays: $p2draw")
            val winner: Int = if (p1draw > p2draw) 1 else 2
            AdventLogger.info("Player $winner wins round $round of game $gameNumber!\n")
        }
    }

    private fun logPostGameResults(p1: SpaceCardDeck, p2: SpaceCardDeck) {
        if (enabledLogging) {
            AdventLogger.info("== Post-game results ==")
            AdventLogger.info("Player 1's deck: $p1")
            AdventLogger.info("Player 2's deck: $p2")
        }
    }

    private fun logSubGameResult(subGameResult: GameResult) {
        if (enabledLogging) {
            AdventLogger.info("The winner of game $gameNumber is player ${subGameResult.winner}\n")
            AdventLogger.info("...anyway, back to game ${gameNumber - 1}")
        }
    }
}