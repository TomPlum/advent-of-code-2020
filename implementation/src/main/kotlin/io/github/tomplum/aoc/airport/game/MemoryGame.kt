package io.github.tomplum.aoc.airport.game

/**
 * While you wait for your flight, you decide to check in with the Elves back at the North Pole.
 * They're playing a memory game and are ever so excited to explain the rules!
 *
 * In this game, the players take turns saying numbers.
 * They begin by taking turns reading from a list of starting numbers.
 * Then, each turn consists of considering the most recently spoken number:
 * - If that was the first time the number has been spoken, the current player says 0.
 * - Otherwise, the number had been spoken before; the current player announces how many turns apart the number is
 *   from when it was previously spoken.
 *
 * @param data The starting numbers.
 */
class MemoryGame(data: String) {
    private val spoken = HashMap<Int, Int>()
    private var currentNumber: Int
    private var turn: Int

    init {
        val numbers = data.split(",").map { it.toInt() }
        numbers.forEachIndexed { i, number -> spoken[number] = i + 1 }
        currentNumber = numbers.last()
        turn = numbers.size
    }

    /**
     * Simulates the memory game up until the given [lastTurn].
     * @return The [currentNumber] spoken when the simulation ends.
     */
    fun simulate(lastTurn: Int): Int {
        while (turn < lastTurn) {
            when {
                !spoken.containsKey(currentNumber) -> speak(0)
                else -> speak(turn - spoken[currentNumber]!!)
            }
        }
        return currentNumber
    }

    private fun speak(number: Int) {
        spoken[currentNumber] = turn++
        currentNumber = number
    }
}