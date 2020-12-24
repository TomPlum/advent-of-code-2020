package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.logging.AdventLogger
import io.github.tomplum.libs.math.Direction
import io.github.tomplum.libs.math.map.AdventMap3D
import io.github.tomplum.libs.math.point.Point3D

class HexGrid(layout: List<List<Direction>>): AdventMap3D<HexTile>() {
    init {
        layout.forEach { directions ->
            val position = directions.toPoint3D()
            val tile = if (hasRecorded(position)) getTile(position).flip() else HexTile.black()
            addTile(position, tile)
        }
        AdventLogger.info(this)
    }

    fun getBlackTileCount(): Int = filterTiles { it.isBlack() }.count()

    private fun List<Direction>.toPoint3D() = fold(Point3D(0,0,0)) { p, direction ->
        when(direction) {
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
