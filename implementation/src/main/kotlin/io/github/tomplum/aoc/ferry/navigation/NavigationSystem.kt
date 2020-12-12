package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction

/**
 * Responsible for navigating the [Ferry] across the seas.
 * @param instructions A list of instructions to guide the ferry to its destination.
 */
class NavigationSystem(private val instructions: List<Instruction<*>>) {
    private val ship = Ferry()

    /**
     * Follows the [instructions] directly and navigates the [ship] accordingly.
     * @return The total distance travelled during the journey.
     */
    fun navigate(): Int = instructions.forEach { (action, value) ->
        when (action) {
            is Direction -> ship.move(action, value)
            is Directive -> when (action) {
                Directive.LEFT -> ship.rotate(-value)
                Directive.RIGHT -> ship.rotate(value)
                Directive.FORWARD -> ship.advance(value)
            }
        }
    }.let { ship.distanceTravelled() }

    /**
     * Follows the [instructions] with respect to the [Waypoint] to better navigate the [ship].
     * @return The total distance travelled during the journey.
     */
    fun navigateViaWaypoint(): Int = instructions.forEach { (action, value) ->
        when (action) {
            is Direction -> ship.waypoint.move(action, value)
            is Directive -> when (action) {
                Directive.LEFT -> ship.rotateWaypoint(-value)
                Directive.RIGHT -> ship.rotateWaypoint(value)
                Directive.FORWARD -> ship.moveToWaypoint(value)
            }
        }
    }.let { ship.distanceTravelled() }

}