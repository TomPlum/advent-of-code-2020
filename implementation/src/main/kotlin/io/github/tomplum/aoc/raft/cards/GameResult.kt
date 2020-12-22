package io.github.tomplum.aoc.raft.cards

/**
 * The result of a single [RecursiveCombatGame].
 * @param winner The winning player.
 * @param p1deck The state of Player 1's deck of cards as the game ended.
 * @param p2deck The state of Player 2's deck of cards as the game ended.
 */
data class GameResult(val winner: Player, val p1deck: SpaceCardDeck, val p2deck: SpaceCardDeck) {
    fun getWinningScore(): Int = when(winner) {
        Player.PLAYER_1 -> p1deck.getDeckScore()
        Player.PLAYER_2 -> p2deck.getDeckScore()
    }
}