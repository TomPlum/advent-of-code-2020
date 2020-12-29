package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.math.map.AdventMap3D
import io.github.tomplum.libs.math.map.MapTile

/**
 * A hexagonal grid of tiles.
 *
 * Uses a 3D cartesian to store the tiles.
 *
 * https://www.redblobgames.com/grids/hexagons/
 */
abstract class HexGrid<T: MapTile<*>>: AdventMap3D<T>()
