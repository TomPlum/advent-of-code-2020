package io.github.tomplum.aoc.airport.game

class MemoryGame(numbers: String) {
    private val turnAsked = mutableMapOf<Int, IntArray>()
    private val timesAsked = mutableMapOf<Int, Int>()
    private var lastNumber = 0
    private var turn = 1

    init {
        numbers.split(",").map { it.toInt() }.forEach { number -> speakNumber(number) }
    }

    fun simulate(lastTurn: Int): Int {
        while (turn <= lastTurn) {
            if (timesAsked[lastNumber] == 1) {
               speakNumber(0)
            } else {
                val lastTwoTurns = turnAsked[lastNumber]!!.takeLast(2)
                val diff = lastTwoTurns[1] - lastTwoTurns[0]
                speakNumber(diff)
            }
        }
        return lastNumber
    }

    private fun speakNumber(number: Int) {
        //AdventLogger.info(number)
        timesAsked[number] = timesAsked.getOrDefault(number, 0) + 1
        lastNumber = number
        setTurnAsked(number)
        turn++
    }

    private fun setTurnAsked(number: Int) {
        val turns = turnAsked.getOrDefault(number, IntArray(2) { 0 })
        turns[0] = turns[1]
        turns[1] = turn
        turnAsked[number] = turns
    }
}