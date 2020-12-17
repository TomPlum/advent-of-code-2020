package io.github.tomplum.aoc.aircraft.energy

import io.github.tomplum.libs.logging.AdventLogger

class DimensionalSimulator(private val initialState: PocketDimension3D) {
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

    fun simulate4d(cycles: Int, startingState: PocketDimension4D): Int {
        var state = startingState.getSnapshot()

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