package io.github.tomplum.aoc.raft.cups

class CupGame(label: String) : CrabCupGame(label, 0) {

    fun simulate(moves: Int): String {
        val prediction = play(moves)
        return prediction.getCupOrder()
    }
}