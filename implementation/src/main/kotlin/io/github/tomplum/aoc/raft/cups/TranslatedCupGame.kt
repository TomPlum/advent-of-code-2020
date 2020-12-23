package io.github.tomplum.aoc.raft.cups

class TranslatedCupGame(label: String): CrabCupGame(label, 1_000_000) {
    fun simulate(moves: Int): Long {
        val prediction = play(moves)

        val firstStarCup = prediction.getClockwiseCup(1)
        val secondStarCup = prediction.getClockwiseCup(firstStarCup)
        return firstStarCup.toLong() * secondStarCup.toLong()
    }
}