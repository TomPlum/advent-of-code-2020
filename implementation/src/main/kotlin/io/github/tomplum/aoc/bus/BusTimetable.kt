package io.github.tomplum.aoc.bus

/**
 * A timetable recording the departure times of buses for the [BusScheduler].
 * @param arrivalTime The arrival time of the passengers.
 * @param buses A list of the buses for departure.
 */
data class BusTimetable(val arrivalTime: Int, val buses: List<Bus>) {

    /**
     * Gets a list of all the buses that are running and in-service.
     * @return A list of the running buses.
     */
    fun getWorkingBuses(): List<Bus> = buses.filterNot { bus -> bus.isOutOfService() }

    /**
     * Creates a list of running buses with their respective offset.
     * A buses offset is equal to its index in the list of [buses].
     * If a bus is out-of-service, it is omitted from the list, but its index is skipped, causing the next offset
     * to be relative to the previous index.
     * @return A list of offsets and the buses that depart on them.
     */
    fun getBusesWithOffsets(): List<Pair<Int, Int>> = buses.mapIndexedNotNull { i, bus ->
        if (bus.isOutOfService()) null else i to bus.getID()
    }

    companion object {
        /**
         * Parses the [data] read from the notes and creates a [BusTimetable].
         */
        fun fromNotes(data: List<String>): BusTimetable {
            val earliestTimestamp = data[0].trim().toInt()
            val ids = data[1].split(",").map { id -> Bus(id) }
            return BusTimetable(earliestTimestamp, ids)
        }
    }
}