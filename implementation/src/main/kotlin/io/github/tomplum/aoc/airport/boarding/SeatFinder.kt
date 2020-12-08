package io.github.tomplum.aoc.airport.boarding

class SeatFinder(private val passes: List<BoardingPass>) {
    fun sanityCheck(): Int = passes.maxOf { it.getSeatID() }

    fun getMissingSeat(): Int = passes
        .map { it.getSeatID() }.sorted()
        .drop(1).dropLast(1)
        .zipWithNext { a, b -> if (b - a != 1) return b - 1 else -1 }
        .filterNot { it == -1 }
        .firstOrNull() ?: throw IllegalStateException("There are no missing seats!")
}