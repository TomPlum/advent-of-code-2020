package io.github.tomplum.aoc.airport.game

import io.github.tomplum.libs.logging.AdventLogger
import java.util.*
import kotlin.math.abs

class MemoryGame(data: String) {
    private val numbers = LinkedList(data.split(",").map { it.toInt() })
    private val turnAsked = mutableMapOf<Int, List<Int>>()
    private val timesAsked = mutableMapOf<Int, Int>()
    private var lastNumberAsked = 0
    private var turn = 1

    init {
        numbers.forEach { number -> speakNumber(number) }
    }

    fun simulate(lastTurn: Int): Int {
        while (turn <= lastTurn) {
            if (timesAsked[lastNumberAsked] == 1) {
               speakNumber(0)
            } else {
                val turnsAsked = turnAsked[lastNumberAsked]
                val diff = turnsAsked!!.takeLast(2).let { abs(it[0] - it[1]) }
                speakNumber(diff)
            }
        }
        return lastNumberAsked
    }

    private fun speakNumber(number: Int) {
        AdventLogger.info(number)
        timesAsked.merge(number, 1, Int::plus)
        lastNumberAsked = number
        turnAsked[number] = turnAsked.getOrDefault(number, mutableListOf()) + turn
        turn++
    }
}