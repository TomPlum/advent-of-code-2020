package io.github.tomplum.aoc.raft.cups

/**
 * The first, smaller, [CrabCupGame] played with the crab.
 * @param label A string of the starting cup values in order.
 */
class CupGame(label: String) : CrabCupGame(label, 0) {
    /**
     * Simulates the game for the given number of [moves].
     * @param moves The number of moves to play.
     * @return A string of the order of cups after cup 1, after the simulated moves.
     */
    fun simulate(moves: Int): String {
        val prediction = play(moves)
        return prediction.getCupOrder()
    }
}