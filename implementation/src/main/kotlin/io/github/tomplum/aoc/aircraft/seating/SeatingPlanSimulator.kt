package io.github.tomplum.aoc.aircraft.seating

import io.github.tomplum.libs.logging.AdventLogger

class SeatingPlanSimulator(layout: SeatingLayout) {
    private val history = mutableListOf(layout)

    init {
        AdventLogger.info("Initial State:")
        AdventLogger.info(layout)
    }

    fun simulateUntilConsolidated(): Int {
        var next = simulate()
        while (!history.contains(next)) {
            AdventLogger.info(next)
            history.add(next)
            next = simulate()
        }
        return next.getOccupiedSeatCount()
    }


    private fun simulate(): SeatingLayout {
        val layout = history.last().snapshot()
        val toOccupy = layout.getOccupiedSeatPositions()
        val toEvict = layout.getEmptySeatPositions()
        layout.occupy(toOccupy)
        layout.evict(toEvict)
        return layout
    }
}