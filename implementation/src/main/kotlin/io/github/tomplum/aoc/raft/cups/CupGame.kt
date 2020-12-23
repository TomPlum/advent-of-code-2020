package io.github.tomplum.aoc.raft.cups

class CupGame(label: String) {

    private val cups = CupCircle(label.map { cup -> cup.toString().toInt() })

    fun simulate(moves: Int): Int {
        var currentCup = cups.first()

        return 0
    }
}