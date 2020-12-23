package io.github.tomplum.aoc.raft.cups

/**
 * The small crab challenges you to a game! The crab is going to mix up some cups,
 * and you have to predict where they'll end up.
 *
 * The cups will be arranged in a circle and labeled clockwise. For example, if your labeling were 32415,
 * there would be five cups in the circle; going clockwise around the circle from the first cup,
 * the cups would be labeled 3, 2, 4, 1, 5, and then back to 3 again.
 *
 * Before the crab starts, it will designate the first cup in your list as the current cup.
 * The crab is then going to do N moves.
 *
 * Each move, the crab does the following actions:
 *
 * 1. The crab picks up the three cups that are immediately clockwise of the current cup.
 *    They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
 *
 * 2. The crab selects a destination cup: the cup with a label equal to the current cup's label minus one.
 *    If this would select one of the cups that was just picked up, the crab will keep subtracting one until it finds
 *    a cup that wasn't just picked up. If at any point in this process the value goes below the lowest value on any
 *    cup's label, it wraps around to the highest value on any cup's label instead.
 *
 * 3. The crab places the cups it just picked up so that they are immediately clockwise of the destination cup.
 *    They keep the same order as when they were picked up.
 *
 * 4. The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
 *
 * @param label A string of integers representing the order and values of the starting cup values.
 * @param logicalCupQuantity The number of extra cups to add to the game when there are lots more than the label.
 */
abstract class CrabCupGame(label: String, logicalCupQuantity: Int) {

    private val startingCups = label.map { cup -> cup.toString().toInt() }
    private val highestCupValue = startingCups.maxOrNull()
    private val extraCups = (1..(logicalCupQuantity - startingCups.size)).map { cup -> highestCupValue!! + cup }
    private val cups = CupCircle(startingCups + extraCups)

    /**
     * Plays the game for the given number of [moves].
     * @param moves The number of moves to simulate.
     * @return The state of the cup circle after play.
     */
    fun play(moves: Int): CupCircle {
        var currentCup = cups.startingCup

        repeat(moves) {
            val clockwiseCups = cups.pickClockwiseCups(currentCup)
            val destinationCup = cups.getDestinationCup(clockwiseCups, currentCup)
            cups.place(currentCup, clockwiseCups, destinationCup)
            currentCup = cups.getClockwiseCup(currentCup)
        }

        return cups
    }
}