package io.github.tomplum.aoc.raft.cups

class CupCircle(startingPositions: List<Int>) {

    private val values: IntArray = IntArray(startingPositions.size + 1) { -1 }

    init {
        startingPositions.zipWithNext { a, b ->
            values[a] = b
        }
        values[startingPositions.last()] = startingPositions.first()
    }

    fun pickClockwiseCups(current: Int): List<Int> {
        val first = values[current]
        val second = values[first]
        val third = values[second]
        return listOf(first, second, third)
    }

    fun getClockwiseCup(current: Int): Int {
        return values[current]
    }

    fun place(currentCup: Int, cups: List<Int>, destination: Int) {
        val clockwiseDestination = values[destination]
        values[currentCup] = values[cups.last()]
        values[destination] = cups.first()
        values[cups.last()] = clockwiseDestination
    }

    fun getDestinationCup(picked: List<Int>, currentCup: Int): Int {
        var candidate = currentCup - 1
        val smallestCupValue = 1
        while (picked.contains(candidate) || candidate < smallestCupValue) {
            if (candidate < smallestCupValue) {
                candidate = values.size - 1
            } else {
                candidate--
            }
        }
        return candidate
    }

    fun getCupOrder(): String {
        val order = StringBuilder()
        (2 until values.size).fold(1) { acc, _ ->
            val next = values[acc]
            order.append(next);
            next
        }
        return order.toString()
    }

    fun toString(currentCup: Int): String = values.drop(1).joinToString("  ") { cup -> if (cup == currentCup) "($cup)" else "$cup" }

    override fun equals(other: Any?): Boolean {
        if (other !is CupCircle) return false
        return values.contentEquals(other.values)
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }
}