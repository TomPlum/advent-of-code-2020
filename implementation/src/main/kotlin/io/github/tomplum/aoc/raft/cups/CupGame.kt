package io.github.tomplum.aoc.raft.cups

import io.github.tomplum.libs.logging.AdventLogger

class CupGame(private val label: String) {

    private val cups = CupCircle(label.map { cup -> cup.toString().toInt() })

    fun simulate(moves: Int): String {
        var currentCup = label.first().toString().toInt()

        repeat(moves) { move ->
            AdventLogger.info("-- Move ${move + 1} --")
            AdventLogger.info("Cups: ${cups.toString(currentCup)}")

            //Crab picks up three cups immediately clockwise of the current cup.
            val clockwiseCups = cups.pickClockwiseCups(currentCup)
            AdventLogger.info("Pick Up: ${clockwiseCups.joinToString(", ")}")

            //Crab selects a destination cup equal to the current cup value minus one.
            val destinationCup = cups.getDestinationCup(clockwiseCups, currentCup)
            AdventLogger.info("Destination: $destinationCup\n")

            //Crab places the 3 cups picked up and places them immediately clockwise of the destination cup
            cups.place(currentCup, clockwiseCups, destinationCup)

            //Crab selects a new current cup
            currentCup = cups.getClockwiseCup(currentCup)
        }

        return cups.getCupOrder()
    }
}