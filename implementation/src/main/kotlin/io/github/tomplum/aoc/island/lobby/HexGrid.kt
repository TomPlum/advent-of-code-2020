package io.github.tomplum.aoc.island.lobby

import io.github.tomplum.libs.math.map.AdventMap3D

/**
 * A hexagonal grid of tiles.
 *
 * Uses a 3D cartesian to store the tiles.
 *
 * https://www.redblobgames.com/grids/hexagons/
 */
abstract class HexGrid<T>: AdventMap3D<T>()
