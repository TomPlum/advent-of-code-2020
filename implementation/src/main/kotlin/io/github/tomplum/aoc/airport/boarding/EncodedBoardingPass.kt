package io.github.tomplum.aoc.airport.boarding

import io.github.tomplum.aoc.extensions.midpoint

/**
 * The airline uses "Binary Space Partitioning" to seat people, instead of traditional zones or groups.
 * This means that each [BoardingPass] is encoded in a format of 10 characters like "FBFBBFFRLR".
 *
 * The first 7 characters will either be "F" or "B"; these specify exactly one of the 128 rows on the plane.
 * Each letter tells you which half of a region the given seat is it. This region is repeatedly cut in-half until
 * only a single row value is left.
 *
 * The last 3 characters will either be "L" or "R"; these specify exactly one of the 8 columns of seats on the plane.
 * The same aforementioned splitting process happens again until the final remaining column value remains.
 *
 * @param value The encoded value of the boarding pass.
 */
data class EncodedBoardingPass(private val value: String) {

    /**
     * Decodes the pass [value].
     * @return The decoded [BoardingPass].
     */
    fun decode(): BoardingPass {
        val row = getValue(value.take(7), 0..127)
        val column = getValue(value.takeLast(3), 0..7)
        return BoardingPass(row, column)
    }

    private fun getValue(partitioning: String, range: IntRange): Int {
        var next = partitioning
        var scope = range
        while (next.isNotEmpty()) {
            val direction = next.first()
            if (direction == 'F' || direction == 'L') {
                scope = scope.first..scope.midpoint()
            }
            if (direction == 'B' || direction == 'R') {
                scope = scope.midpoint() + 1..scope.last
            }
            next = next.drop(1)
        }
        return scope.first
    }
}