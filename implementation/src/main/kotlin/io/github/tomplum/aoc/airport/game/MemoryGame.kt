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
class MemoryGame(data: String, private val turns: Int) {
    private val numbers = data.split(",").map { number -> number.toInt() }
    private val spoken = IntArray(turns) { -1 }
    private var currentNumber = numbers.last()
    private var turn = numbers.size

    init {
        numbers.forEachIndexed { i, number -> spoken[number] = i + 1 }
    }

    /**
     * Simulates the memory game up until the given [turns].
     * @return The [currentNumber] spoken when the simulation ends.
     */
    fun simulate(): Int {
        while (turn < turns) {
            when {
                spoken[currentNumber] == -1 -> speak(0)
                else -> speak(turn - spoken[currentNumber])
            }
        }
        return currentNumber
    }

    /**
     * Speaks the next [number] and increments the turn.
     */
    private fun speak(number: Int) {
        spoken[currentNumber] = turn++
        currentNumber = number
    }
}