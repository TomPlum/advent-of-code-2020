package io.github.tomplum.aoc.airport.boarding

data class BoardingPass(private val row: Int, private val column: Int) {
    fun getSeatID(): Int = (row * 8) + column
}