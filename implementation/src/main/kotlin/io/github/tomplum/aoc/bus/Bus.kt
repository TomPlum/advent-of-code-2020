package io.github.tomplum.aoc.bus

/**
 * A shuttle bus for transporting passengers to the airport.
 * @param id The unique ID of the bus. Can be 'x' if out-of-service.
 */
data class Bus(private val id: String) {
    /**
     * Checks if the bus is out-of-service.
     * @return true if out-of-service, false if running.
     */
    fun isOutOfService(): Boolean = id == "x"

    /**
     * Gets the unique ID of the bus.
     * @throws IllegalStateException if the bus is out-of-service.
     * @return The ID
     */
    fun getID(): Int = if (!isOutOfService()) id.toInt() else throw IllegalStateException("This bus is out of service!")
}