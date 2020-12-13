package io.github.tomplum.aoc.ferry.seating

import io.github.tomplum.aoc.ferry.seating.strategy.SeatingStrategy
import io.github.tomplum.libs.logging.AdventLogger
import io.github.tomplum.aoc.ferry.navigation.Ferry

/**
 * Simulates the process of people choosing (or abandoning) their seat in the [Ferry] waiting room.
 * @param layout The initial [SeatingLayout] on the [Ferry].
 */
class SeatingPlanSimulator(layout: SeatingLayout) {
    private val history = mutableListOf(layout)

    init {
        AdventLogger.info("Initial State:")
        AdventLogger.info(layout)
    }

    /**
     * Simulates [SeatingLayout] state changes according to the given [strategy] until all the passengers are
     * happily seated and subsequent application of the [strategy] rules causes no more seating changes.
     * @param strategy The rules that govern how people move seats.
     * @return The number of occupied seats after everybody is happily seated.
     */
    fun simulateUntilConsolidated(strategy: SeatingStrategy): Int {
        var next = simulate(strategy)
        while (history.last() != next) {
            AdventLogger.info(next)
            history.add(next)
            next = simulate(strategy)
        }
        return next.getOccupiedSeatCount()
    }

    /**
     * Simulates a single state change in the [SeatingLayout] according the rules defined in the given [strategy].
     * @param strategy The rules that govern how people move seats.
     * @return The [SeatingLayout] state after a single simulation.
     */
    private fun simulate(strategy: SeatingStrategy): SeatingLayout {
        val layout = history.last().snapshotCurrentState()
        val toOccupy = strategy.getOccupiedSeatPositions(layout)
        val toEvict = strategy.getEmptySeatPositions(layout)
        layout.occupy(toOccupy)
        layout.evict(toEvict)
        return layout
    }
}