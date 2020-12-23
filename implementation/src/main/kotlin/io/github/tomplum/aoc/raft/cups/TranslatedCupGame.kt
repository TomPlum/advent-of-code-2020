package io.github.tomplum.aoc.raft.cups

class TranslatedCupGame(private val label: String) {
    private val startingCups = label.map { cup -> cup.toString().toInt() }
    private val highestCupValue = startingCups.maxOrNull()
    private val extraCups = (1..(1_000_000 - startingCups.size)).map { cup -> highestCupValue!! + cup }

    private val cups = CupCircle(startingCups + extraCups)

    fun simulate(moves: Int): Long {
        var currentCup = label.first().toString().toInt()

        repeat(moves) {
            //Crab picks up three cups immediately clockwise of the current cup.
            val clockwiseCups = cups.pickClockwiseCups(currentCup)

            //Crab selects a destination cup equal to the current cup value minus one.
            val destinationCup = cups.getDestinationCup(clockwiseCups, currentCup)

            //Crab places the 3 cups picked up and places them immediately clockwise of the destination cup
            cups.place(currentCup, clockwiseCups, destinationCup)

            //Crab selects a new current cup
            currentCup = cups.getClockwiseCup(currentCup)
        }

        val firstStarCup = cups.getClockwiseCup(1)
        val secondStarCup = cups.getClockwiseCup(firstStarCup)
        return firstStarCup.toLong() * secondStarCup.toLong()
    }
}