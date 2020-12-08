package io.github.tomplum.aoc.airport.boarding

/**
 * A pass for a single-passenger, granting them access to a given flight.
 *
 * @param row The unique id of the row the passengers seat is in.
 * @param column The unique id of the column the passengers seat is in.
 */
data class BoardingPass(private val row: Int, private val column: Int) {
    /**
     * Each seat on the plane is uniquely identified by a Seat ID.
     * It is calculated by multiplying the [row] by 8 and adding the [column].
     * @return The unique seat id for the pass.
     */
    fun getSeatID(): Int = (row * 8) + column
}