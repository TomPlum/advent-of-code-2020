package io.github.tomplum.aoc.raft.cups

import java.util.*

class CupCircle(startingPositions: List<Int>) {

    private val values = LinkedList(startingPositions)

    fun first(): Int = values.peekFirst()

    fun clockwiseCups(current: Int): List<Int> {
        val currentIndex = values.indexOf(current)
        val nextCupIndex = currentIndex + 1
        val lastCupIndex = values.lastIndex

        if (currentIndex <= (lastCupIndex - 3)) {
            return getCups(nextCupIndex, nextCupIndex + 3)
        }

        if (nextCupIndex > lastCupIndex) {
            return getCups(0, 3)
        }

        val remaining = getCups(nextCupIndex, values.size)
        val wrapped = getCups(0, 3 - remaining.size)

        return remaining + wrapped
    }

    fun toString(currentCup: Int): String =
        values.joinToString("  ") { cup -> if (cup == currentCup) "($cup)" else "$cup" }

    private fun getCups(start: Int, end: Int): List<Int> {
        val cups = mutableListOf<Int>()
        for (i in (end) - 1 downTo start) {
            println(i)
            cups.add(values.removeAt(i))
        }
        return cups.reversed()
    }


    override fun equals(other: Any?): Boolean {
        if (other !is CupCircle) return false
        return values == other.values
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }
}