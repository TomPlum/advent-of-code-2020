package io.github.tomplum.aoc.raft.cards

data class GameResult(val winner: Player, val p1deck: SpaceCardDeck, val p2deck: SpaceCardDeck) {
    fun getWinningScore(): Int = when(winner) {
        Player.PLAYER_1 -> p1deck.getDeckScore()
        Player.PLAYER_2 -> p2deck.getDeckScore()
    }
}