package io.github.tomplum.aoc.ferry.navigation

import io.github.tomplum.libs.math.Direction

class NavigationSystem(private val instructions: List<Instruction<*>>) {
    private val ship = Ferry()

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