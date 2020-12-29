package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.point.Point3D

/**
 * The mid-renovation floor of the hotel lobby on the tropical island.
 * Consists of black and white hexagonal tiles.
 * @param layout A list of tile positions denoted by a sub-list of directions from the reference tile.
 */
class LobbyFloor(layout: List<List<Direction>>): HexGrid<FloorTile>() {
    init {
        layout.forEach { directions ->
            val position = directions.toPoint3D()
            val tile = if (hasRecorded(position)) getTile(position).flip() else FloorTile.black()
            addTile(position, tile)
        }
    }

    /**
     * Gets all the tiles that are currently white but will flip to black on the next day.
     * Any white tiles that have exactly 2 black-tile neighbors will flip to black.
     * @return The positions of the flipping-to-black tiles.
     */
    fun getNextBlackTiles(): List<Point3D> {
        val currentlyWhite = filterTiles { it.isWhite() }
        return currentlyWhite.keys.filter { pos ->
            val adjacent = filterPoints(pos.getHexagonalAdjacent().toSet())
            val blackNeighbors = adjacent.count { (_, tile) -> tile.isBlack() }
            blackNeighbors == 2
        }
    }

    /**
     * Gets all the tiles that are currently black but will flip to white on the next day.
     * Any black tiles that have 0 or greater than 2 black-tile neighbors will flip to black.
     * @return The positions of the the flipping-to-white tiles.
     */
    fun getNextWhiteTiles(): List<Point3D> {
        val currentlyBlack = filterTiles { it.isBlack() }
        return currentlyBlack.keys.filter { pos ->
            val adjacent = filterPoints(pos.getHexagonalAdjacent().toSet())
            val blackNeighbors = adjacent.count { (_, tile) -> tile.isBlack() }
            blackNeighbors == 0 || blackNeighbors > 2
        }
    }

    /**
     * Flips all the tiles for the given [positions].
     * @param positions The positions of the tiles to flip.
     */
    fun flip(positions: List<Point3D>) = positions.forEach { pos -> addTile(pos, getTile(pos).flip()) }

    //TODO: Add snapshot() to AdventMap3D and update in Pocked Dimension too.
    /**
     * Creates a snapshot of the floor in its current state.
     * @return A copy of the floor layout.
     */
    fun getFloorSnapshot(): LobbyFloor {
        val snapshot = LobbyFloor(emptyList())
        val tiles = filterTiles { true }
        tiles.forEach { (pos, tile) -> snapshot.addTile(pos, tile) }
        snapshot.addSurroundingLayer(tiles.keys)
        return snapshot
    }

    /**
     * Gets all the tiles that are currently black facing up.
     * @return The number of black tiles.
     */
    fun getBlackTileCount(): Int = filterTiles { it.isBlack() }.count()

    //TODO Move to Point3D in libs
    /**
     * Gets the 6 neighboring tiles assuming that the point is part of a hexagonal grid.
     * @return The 6 adjacent tiles.
     */
    private fun Point3D.getHexagonalAdjacent(): List<Point3D> = listOf(
            Point3D(x + 1, y - 1, z), Point3D(x + 1, y, z - 1), Point3D(x, y + 1, z - 1),
            Point3D(x - 1, y + 1, z), Point3D(x - 1, y, z + 1), Point3D(x, y - 1, z + 1)
    )

    private fun addSurroundingLayer(positions: Set<Point3D>) {
        val surroundingPoints = filterPoints(positions).keys.flatMap { pos ->
            pos.getHexagonalAdjacent().filter { !hasRecorded(it) }
        }

        surroundingPoints.forEach { pos -> addTile(pos, FloorTile.white()) }
    }

    private fun List<Direction>.toPoint3D() = fold(Point3D(0, 0, 0)) { p, direction ->
        when (direction) {
            Direction.TOP_RIGHT -> Point3D(p.x + 1, p.y, p.z - 1)
            Direction.RIGHT -> Point3D(p.x + 1, p.y - 1, p.z)
            Direction.BOTTOM_RIGHT -> Point3D(p.x, p.y - 1, p.z + 1)
            Direction.BOTTOM_LEFT -> Point3D(p.x - 1, p.y, p.z + 1)
            Direction.LEFT -> Point3D(p.x - 1, p.y + 1, p.z)
            Direction.TOP_LEFT -> Point3D(p.x, p.y + 1, p.z - 1)
            else -> throw IllegalArgumentException("$direction is not a valid hexagonal direction!")
        }
    }
}