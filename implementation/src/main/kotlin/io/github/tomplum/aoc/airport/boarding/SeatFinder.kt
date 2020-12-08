package io.github.tomplum.aoc.airport.boarding

/**
 * Locates seats on the plane from the list of given [passes].
 */
class SeatFinder(private val passes: List<BoardingPass>) {
    /**
     * A sanity check to confirm the [EncodedBoardingPass] decoding worked correctly.
     * @return The highest seat id in the given [passes].
     */
    fun sanityCheck(): Int = passes.maxOf { it.getSeatID() }

    /**
     * Finds the missing seat in the given [passes].
     * Assumes the passes make up a full flight and that only a single [BoardingPass] is missing.
     * @throws IllegalStateException if the given [passes] do not contain any missing passes.
     * @return The seat id of the missing pass.
     */
    fun getMissingSeat(): Int = passes
        .map { it.getSeatID() }.sorted()
        .drop(1).dropLast(1)
        .zipWithNext { a, b -> if (b - a != 1) return b - 1 else -1 }
        .filterNot { it == -1 }
        .firstOrNull() ?: throw IllegalStateException("There are no missing seats!")
}