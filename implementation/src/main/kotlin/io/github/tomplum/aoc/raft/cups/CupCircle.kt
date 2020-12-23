package io.github.tomplum.aoc.raft.cups

import java.util.*

class CupCircle(startingPositions: List<Int>) {

    private val values = LinkedList(startingPositions)

    fun first(): Int = values.peekFirst()

    fun pickClockwiseCups(current: Int, n: Int): List<Int> {
        val currentIndex = values.indexOf(current)
        val nextCupIndex = currentIndex + 1
        val lastCupIndex = values.lastIndex

        if (currentIndex <= (lastCupIndex - n)) {
            return getCups(nextCupIndex, nextCupIndex + n)
        }

        if (nextCupIndex > lastCupIndex) {
            return getCups(0, n)
        }

        val remaining = getCups(nextCupIndex, values.size)
        val wrapped = getCups(0, n - remaining.size)

        return remaining + wrapped
    }

    fun getClockwiseCup(current: Int): Int {
        val currentIndex = values.indexOf(current)
        if (currentIndex == values.lastIndex) {
            return values.first()
        }
        return values[currentIndex + 1]
    }

    fun get(value: Int): Int {
        val valueIndex = values.indexOf(value)
        values.removeAt(valueIndex)
        return valueIndex
    }

    fun place(cups: List<Int>, destination: Int) {
        val destinationIndex = values.indexOf(destination)
        val cupsRightDestination = getCups(destinationIndex + 1, values.size)
        cups.forEach { cup -> values.addLast(cup) }
        cupsRightDestination.forEach { cup -> values.addLast(cup) }
    }

    fun getDestinationCup(picked: List<Int>, currentCup: Int): Int {
        var candidate = currentCup - 1
        val smallestCupValue = values.minOrNull()!!
        while (picked.contains(candidate) || candidate < smallestCupValue) {
            if (candidate < smallestCupValue) {
                candidate = values.maxOrNull()!!
            } else {
                candidate--
            }
        }
        return candidate
        //val offset = picked.fold(1) { acc, cup -> if (currentCup - acc == cup) acc + 1 else acc }
    }

    fun getCupOrder(): String {
        return pickClockwiseCups(1, values.lastIndex).joinToString("")
    }

    fun toString(currentCup: Int): String =
        values.joinToString("  ") { cup -> if (cup == currentCup) "($cup)" else "$cup" }

    private fun getCups(start: Int, end: Int): List<Int> {
        val cups = mutableListOf<Int>()
        for (i in (end) - 1 downTo start) {
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