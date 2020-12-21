package io.github.tomplum.aoc.aircraft.energy

import io.github.tomplum.libs.logging.AdventLogger
import io.github.tomplum.libs.math.map.AdventMap3D
import io.github.tomplum.libs.math.point.Point
import io.github.tomplum.libs.math.point.Point3D

/**
 * A 3-Dimensional implementation of a [PocketDimension].
 */
class PocketDimension3D(initialState: List<String>) : AdventMap3D<ConwayCube>(), PocketDimension {
    init {
        var x = 0
        var y = 0
        initialState.forEach { row ->
            row.forEach { state ->
                addTile(Point3D(x, y, 0), ConwayCube(state))
                x++
            }
            x = 0
            y++
        }

        if (initialState.isNotEmpty()) {
            AdventLogger.info("Initial State:\n$this")
        }
    }

    /**
     * Determines which cubes will become active in the next time-cycle.
     * @return A list of all the positions of the to-be-active cubes.
     */
    override fun getNextActiveCubes(): List<Point3D> {
        val currentlyInactive = filterTiles { it.isInActive() }
        return currentlyInactive.keys.filter { pos ->
            val adjacent = filterPoints(pos.adjacent().toSet())
            adjacent.count { (_, cube) -> cube.isActive() } == 3
        }
    }

    /**
     * Determines which cubes will become in-active in the next time-cycle.
     * @return A list of all the positions of the to-be-active cubes.
     */
    override fun getNextInActiveCubes(): List<Point3D> {
        val currentlyActive = filterTiles { it.isActive() }
        return currentlyActive.keys.filter { pos ->
            val adjacent = filterPoints(pos.adjacent().toSet())
            val activeNeighbors = adjacent.count { (_, cube) -> cube.isActive() }
            activeNeighbors != 2 && activeNeighbors != 3
        }
    }

    /**
     * Activates all the cubes at the given [positions].
     * @param positions A list of in-active positions.
     */
    override fun activate(positions: List<Point>) {
        positions.forEach { addTile(it as Point3D, ConwayCube('#')) }
    }

    /**
     * De-activates all the cubes at the given [positions].
     * @param positions A list of active positions.
     */
    override fun deactivate(positions: List<Point>) {
        positions.forEach { addTile(it as Point3D, ConwayCube('.')) }
    }

    /**
     * Creates a snapshot of the current state of the dimension.
     * @return A copy of the current dimensional state.
     */
    override fun getSnapshot(): PocketDimension3D {
        val snapshot = PocketDimension3D(emptyList())
        filterTiles { true }.forEach { (pos, cube) -> snapshot.addTile(pos, cube) }
        snapshot.addNewSurroundingCells()
        return snapshot
    }

    /**
     * Finds all the cubes that are currently in an active state.
     * @return The sum of all active cubes.
     */
    override fun getActiveCubeQuantity(): Int = filterTiles { it.isActive() }.count()

    /**
     * Adds a new layer of in-active cells around the current perimeter.
     * This causes the dimension to expand every time cycle.
     */
    private fun addNewSurroundingCells() {
        val xMax = xMax()!!
        val xMin = xMin()!!
        val yMax = yMax()!!
        val yMin = yMin()!!
        val zMin = zMin()!!
        val zMax = zMax()!!

        (yMin - 1..yMax + 1).forEach { y ->
            (zMin..zMax).forEach { z ->
                //Add left and right column for all existing z-planes
                addTile(Point3D(xMin - 1, y, z), ConwayCube('.'))
                addTile(Point3D(xMax + 1, y, z), ConwayCube('.'))
            }

            //Add entire new z-layer that is 1 cell wider in each direction above and below the top and bottom layers
            (xMin - 1..xMax + 1).forEach { x ->
                addTile(Point3D(x, y, zMin - 1), ConwayCube('.'))
                addTile(Point3D(x, y, zMax + 1), ConwayCube('.'))
            }

        }

        (xMin..xMax).forEach { x ->
            (zMin..zMax).forEach { z ->
                addTile(Point3D(x, yMin - 1, z), ConwayCube('.'))
                addTile(Point3D(x, yMax + 1, z), ConwayCube('.'))
            }
        }
    }
}