package io.github.tomplum.aoc.ferry.seating

import io.github.tomplum.aoc.ferry.seating.strategy.SeatingStrategy
import io.github.tomplum.libs.logging.AdventLogger

class SeatingPlanSimulator(layout: SeatingLayout) {
    private val history = mutableListOf(layout)

    init {
        AdventLogger.info("Initial State:")
        AdventLogger.info(layout)
    }

    fun simulateUntilConsolidated(strategy: SeatingStrategy): Int {
        var next = simulate(strategy)
        while (history.last() != next) {
            AdventLogger.info(next)
            history.add(next)
            next = simulate(strategy)
        }
        return next.getOccupiedSeatCount()
    }

    private fun simulate(strategy: SeatingStrategy): SeatingLayout {
        val layout = history.last().snapshotCurrentState()
        val toOccupy = strategy.getOccupiedSeatPositions(layout)
        val toEvict = strategy.getEmptySeatPositions(layout)
        layout.occupy(toOccupy)
        layout.evict(toEvict)
        return layout
    }
}