package io.github.tomplum.aoc.aircraft.energy

import io.github.tomplum.libs.logging.AdventLogger

/**
 * Simulates the life-cycle of a [PocketDimension].
 * Inspired by 'Conways Game of Life'.
 * @param initialState The initial state of activity in the dimension.
 */
class DimensionalSimulator(private val initialState: PocketDimension) {
    /**
     * Simulates time for the given number of [cycles].
     * Each cycle causes all the cubes to evaluate their position relative to their neighbors.
     * The cube then either stays active for survival sake, or deactivates to prevent over-population.
     *
     * @param cycles The number of cycles to simulate.
     * @return The number of cubes still active and simulation is complete.
     */
    fun simulate(cycles: Int): Int {
        var state = initialState.getSnapshot()

        repeat(cycles) { cycle ->
            val activating = state.getNextActiveCubes()
            val deactivating = state.getNextInActiveCubes()

            state.activate(activating)
            state.deactivate(deactivating)

            AdventLogger.info("Generation ${cycle + 1} \n$state")

            state = state.getSnapshot()
        }

        return state.getActiveCubeQuantity()
    }
}