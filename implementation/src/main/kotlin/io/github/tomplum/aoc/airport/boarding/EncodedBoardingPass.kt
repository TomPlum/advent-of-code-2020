package io.github.tomplum.aoc.airport.boarding

import io.github.tomplum.aoc.extensions.midpoint

data class EncodedBoardingPass(private val value: String) {

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