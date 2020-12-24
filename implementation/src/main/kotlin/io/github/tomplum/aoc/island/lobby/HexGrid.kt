package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.map.AdventMap3D
import io.github.tomplum.libs.math.point.Point3D

/**
 * https://www.redblobgames.com/grids/hexagons/
 */
class HexGrid(layout: List<List<Direction>>) : AdventMap3D<HexTile>() {
    init {
        layout.forEach { directions ->
            val position = directions.toPoint3D()
            val tile = if (hasRecorded(position)) getTile(position).flip() else HexTile.black()
            addTile(position, tile)
        }
    }

    fun getNextBlackTiles(): List<Point3D> {
        val currentlyWhite = filterTiles { it.isWhite() }
        return currentlyWhite.keys.filter { pos ->
            val adjacent = filterPoints(pos.getHexagonalAdjacent().toSet())
            val blackNeighbors = adjacent.count { (_, tile) -> tile.isBlack() }
            blackNeighbors == 2
        }
    }

    fun getNextWhiteTiles(): List<Point3D> {
        val currentlyBlack = filterTiles { it.isBlack() }
        return currentlyBlack.keys.filter { pos ->
            val adjacent = filterPoints(pos.getHexagonalAdjacent().toSet())
            val blackNeighbors = adjacent.count { (_, tile) -> tile.isBlack() }
            blackNeighbors == 0 || blackNeighbors > 2
        }
    }

    fun flip(positions: List<Point3D>) = positions.forEach { pos -> addTile(pos, getTile(pos).flip()) }

    //TODO: Add snapshot() to AdventMap3D and update in Pocked Dimension too.
    fun getFloorSnapshot(): HexGrid {
        val snapshot = HexGrid(emptyList())
        val tiles = filterTiles { true }
        tiles.forEach { (pos, tile) -> snapshot.addTile(pos, tile) }
        snapshot.addSurroundingLayer(tiles.keys)
        return snapshot
    }

    fun getBlackTileCount(): Int = filterTiles { it.isBlack() }.count()

    //TODO Move to Point3D in libs
    private fun Point3D.getHexagonalAdjacent(): List<Point3D> = listOf(
            Point3D(x + 1, y - 1, z), Point3D(x + 1, y, z - 1), Point3D(x, y + 1, z - 1),
            Point3D(x - 1, y + 1, z), Point3D(x - 1, y, z + 1), Point3D(x, y - 1, z + 1)
    )

    private fun addSurroundingLayer(positions: Set<Point3D>) {
        val surroundingPoints = filterPoints(positions).keys.flatMap { pos ->
            pos.getHexagonalAdjacent().filter { !hasRecorded(it) }
        }

        surroundingPoints.forEach { pos -> addTile(pos, HexTile.white()) }
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
