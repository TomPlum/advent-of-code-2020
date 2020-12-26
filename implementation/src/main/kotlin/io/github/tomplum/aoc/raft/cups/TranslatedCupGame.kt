package io.github.tomplum.aoc.raft.cups

/**
 * The second, much larger, [CrabCupGame] played with the crab after translating properly.
 * @param label A string of the starting cup values in order.
 */
class TranslatedCupGame(label: String): CrabCupGame(label, 1_000_000) {
    /**
     * Simulates the game for the given number of [moves].
     * @param moves The number of moves to play.
     * @return The product of the values of the two cups immediately clockwise of cup 1.
     */
    fun simulate(moves: Int): Long {
        val prediction = play(moves)

        val firstStarCup = prediction.getClockwiseCup(1)
        val secondStarCup = prediction.getClockwiseCup(firstStarCup)
        return firstStarCup.toLong() * secondStarCup.toLong()
    }
}