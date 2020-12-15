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
 * @param numbers The starting numbers.
 */
class MemoryGame(numbers: String) {
    private val turnAsked = mutableMapOf<Int, IntArray>()
    private val timesAsked = mutableMapOf<Int, Int>()
    private var lastNumber = 0
    private var turn = 1

    init {
        numbers.split(",").map { it.toInt() }.forEach { number -> speak(number) }
    }

    /**
     * Simulates the memory game up until the given [lastTurn].
     * @return The [lastNumber] spoken when the simulation ends.
     */
    fun simulate(lastTurn: Int): Int {
        while (turn <= lastTurn) {
            if (timesAsked[lastNumber] == 1) {
               speak(0)
            } else {
                val lastTwoTurns = turnAsked[lastNumber]!!.takeLast(2)
                val diff = lastTwoTurns[1] - lastTwoTurns[0]
                speak(diff)
            }
        }
        return lastNumber
    }

    private fun speak(number: Int) {
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