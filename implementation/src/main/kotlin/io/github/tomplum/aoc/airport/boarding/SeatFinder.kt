package io.github.tomplum.aoc.airport.boarding

class SeatFinder {
    fun getMissingSeats(passes: List<BoardingPass>): Int {
        val missing = mutableListOf<Int>()
        passes.map { it.getSeatID() }.sorted().zipWithNext { a, b -> if (b - a != 1) missing.add(b-1) }
        return missing.first()
    }
}