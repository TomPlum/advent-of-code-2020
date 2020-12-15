package io.github.tomplum.aoc.airport.game

import io.github.tomplum.libs.logging.AdventLogger
import java.util.*
import kotlin.math.abs

class MemoryGame(data: List<Int>) {
    private val numbers = LinkedList(data)
    private val turnAsked = mutableMapOf<Int, List<Int>>()
    private val timesAsked = mutableMapOf<Int, Int>()
    private var lastNumberAsked = 0
    private var turn = 1

    init {
        timesAsked.putAll(data.map { it to 1 })
        numbers.forEach { number ->
            AdventLogger.info(number)
            turnAsked[number] = turnAsked.getOrDefault(number, mutableListOf()) + turn
            lastNumberAsked = number
            turn++
        }
    }

    fun simulate(lastTurn: Int): Int {
        while (turn <= lastTurn) {
            if (timesAsked[lastNumberAsked] == 1) {
                AdventLogger.info(0)
                lastNumberAsked = 0
                turnAsked[0] = turnAsked.getOrDefault(0, mutableListOf()) + turn
                timesAsked.merge(0, 1, Int::plus)
            } else {
                val turnsAsked = turnAsked[lastNumberAsked]
                val diff = turnsAsked!!.takeLast(2).let { abs(it[0] - it[1]) }
                AdventLogger.info(diff)
                lastNumberAsked = diff
                turnAsked[diff] = turnAsked.getOrDefault(diff, mutableListOf()) + turn
                timesAsked.merge(diff, 1, Int::plus)
            }
            turn++
        }
        return lastNumberAsked
    }
}