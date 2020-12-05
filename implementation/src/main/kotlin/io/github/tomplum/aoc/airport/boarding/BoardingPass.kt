package io.github.tomplum.aoc.airport.boarding

data class BoardingPass(val row: Int, val column: Int) {
    fun getSeatID(): Int = (row * 8) + column
}