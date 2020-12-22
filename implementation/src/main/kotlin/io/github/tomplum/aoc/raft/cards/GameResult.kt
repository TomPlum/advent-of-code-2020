package io.github.tomplum.aoc.raft.cards

data class GameResult(val status: GameStatus, val p1deck: SpaceCardDeck, val p2deck: SpaceCardDeck) {
    fun getWinningScore(): Int = when(status) {
        GameStatus.PLAYER_1_WIN -> p1deck.getDeckScore()
        GameStatus.PLAYER_2_WIN -> p2deck.getDeckScore()
    }

    fun getWinner(): Int = when(status) {
        GameStatus.PLAYER_1_WIN -> 1
        GameStatus.PLAYER_2_WIN -> 2
    }
}