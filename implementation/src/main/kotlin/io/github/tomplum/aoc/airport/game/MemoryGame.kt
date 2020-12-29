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
 * The sequence of spoken numbers is called the "Van Eck Sequence".
 * @see <a>https://www.youtube.com/watch?v=etMJxB-igrc</a>
 *
 * @param data The starting numbers.
 */
class MemoryGame(data: String) {
    private val numbers = data.split(",").map { it.toInt() }
    private lateinit var spoken: IntArray
    private var currentNumber = numbers.last()
    private var turn = numbers.size

    /**
     * Simulates the memory game up until the given [lastTurn].
     * @return The [currentNumber] spoken when the simulation ends.
     */
    fun simulate(lastTurn: Int): Int {
        spoken = IntArray(lastTurn) { -1 }
        numbers.forEachIndexed { i, number -> spoken[number] = i + 1 }
        while (turn < lastTurn) {
            when {
                spoken[currentNumber] == -1 -> speak(0)
                else -> speak(turn - spoken[currentNumber])
            }
        }
        return currentNumber
    }

    private fun speak(number: Int) {
        spoken[currentNumber] = turn++
        currentNumber = number
    }
}