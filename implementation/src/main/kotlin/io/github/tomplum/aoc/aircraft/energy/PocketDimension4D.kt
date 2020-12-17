package io.github.tomplum.aoc.aircraft.energy

import io.github.tomplum.libs.logging.AdventLogger

class PocketDimension4D(initialState: List<String>) {
    private val data = mutableMapOf<Point4D, ConwayCube>()

    init {
        var x = 0
        var y = 0
        initialState.forEach { row ->
            row.forEach { state ->
                data[Point4D(x, y, 0, 0)] = ConwayCube(state)
                x++
            }
            x = 0
            y++
        }

        if (initialState.isNotEmpty()) {
            AdventLogger.info("Initial State:\n$this")
        }

    }

    private fun addNewSurroundingCells() {
        val xMin = data.keys.minByOrNull { it.x }!!.x
        val xMax = data.keys.maxByOrNull { it.x }!!.x
        val yMin = data.keys.minByOrNull { it.y }!!.y
        val yMax = data.keys.maxByOrNull { it.y }!!.y
        val zMin = data.keys.minByOrNull { it.z }!!.z
        val zMax = data.keys.maxByOrNull { it.z }!!.z
        val wMin = data.keys.minByOrNull { it.w }!!.w
        val wMax = data.keys.maxByOrNull { it.w }!!.w

        (yMin - 1..yMax + 1).forEach { y ->
            (zMin..zMax).forEach { z ->
                (wMin..wMax).forEach { w ->
                    //Add left and right column for all existing z-planes
                    data[Point4D(xMin - 1, y, z, w)] = ConwayCube('.')
                    data[Point4D(xMax + 1, y, z, w)] = ConwayCube('.')
                }

            }

            //Add entire new z-layer that is 1 cell wider in each direction above and below the top and bottom layers
            (xMin - 1..xMax + 1).forEach { x ->
                (wMin - 1..wMax + 1).forEach { w ->
                    data[Point4D(x, y, zMin - 1, w)] = ConwayCube('.')
                    data[Point4D(x, y, zMax + 1, w)] = ConwayCube('.')
                }
            }

            //Add new w-layers
            (xMin - 1..xMax + 1).forEach { x ->
                (zMin - 1..zMax + 1).forEach { z ->
                    data[Point4D(x, y, z, wMin - 1)] = ConwayCube('.')
                    data[Point4D(x, y, z, wMax + 1)] = ConwayCube('.')
                }
            }

        }

        //Add top and bottom row for all existing z planes
        (xMin..xMax).forEach { x ->
            (zMin..zMax).forEach { z ->
                (wMin..wMax).forEach { w ->
                    data[Point4D(x, yMin - 1, z, w)] = ConwayCube('.')
                    data[Point4D(x, yMax + 1, z, w)] = ConwayCube('.')
                }
            }
        }
    }

    fun getNextActiveCubes(): List<Point4D> {
        val currentlyInactive = data.filterValues { it.isInActive() }
        return currentlyInactive.keys.filter { pos ->
            val adjacent = pos.adjacent().filter { data.containsKey(it) }.associateWith { data[it] }
            adjacent.count { (_, cube) -> cube!!.isActive() } == 3
        }
    }

    fun getNextInActiveCubes(): List<Point4D> {
        val currentlyActive = data.filterValues { it.isActive() }
        return currentlyActive.keys.filter { pos ->
            val adjacent = pos.adjacent().filter { data.containsKey(it) }.associateWith { data[it] }
            val activeNeighbors = adjacent.count { (_, cube) -> cube!!.isActive() }
            activeNeighbors != 2 && activeNeighbors != 3
        }
    }

    fun activate(positions: List<Point4D>) {
        positions.forEach { addTile(it, ConwayCube('#')) }
    }

    fun deactivate(positions: List<Point4D>) {
        positions.forEach { addTile(it, ConwayCube('.')) }
    }

    fun getSnapshot(): PocketDimension4D {
        val snapshot = PocketDimension4D(emptyList())
        data.filterValues { true }.forEach { (pos, cube) -> snapshot.addTile(pos, cube) }
        snapshot.addNewSurroundingCells()
        return snapshot
    }

    fun getActiveCubeQuantity(): Int = data.filterValues { it.isActive() }.count()

    private fun addTile(position: Point4D, cube: ConwayCube) {
        data[position] = cube
    }

    override fun toString(): String {
        val xMin = data.keys.minByOrNull { it.x }!!.x
        val xMax = data.keys.maxByOrNull { it.x }!!.x
        val yMin = data.keys.minByOrNull { it.y }!!.y
        val yMax = data.keys.maxByOrNull { it.y }!!.y
        val zMin = data.keys.minByOrNull { it.z }!!.z
        val zMax = data.keys.maxByOrNull { it.z }!!.z
        val wMin = data.keys.minByOrNull { it.w }!!.w
        val wMax = data.keys.maxByOrNull { it.w }!!.w

        return (wMin..wMax).joinToString("\n") { w ->
            (zMin..zMax).joinToString("\n") { z ->
                (yMin..yMax).joinToString("\n") { y ->
                    (xMin..xMax).joinToString(" ") { x ->
                        data.getOrDefault(Point4D(x, y, z, w), " ").toString()
                    }
                }.plus("\n")
            }
        }
    }
}