package io.github.tomplum.aoc.bus

/**
 * A scheduler for the shuttle bus service.
 *
 * Bus schedules are defined based on a timestamp that measures the number of minutes since some fixed reference point
 * in the past. At timestamp 0, every bus simultaneously departed from the sea port. After that, each bus travels to
 * the airport, then various other locations, and finally returns to the sea port to repeat its journey forever.
 *
 * @param timetable A table of bus departure times
 */
class BusScheduler(private val timetable: BusTimetable) {
    /**
     * Finds the [Bus] which departs the shortest time after the [timetable] arrival time.
     * @return The product of the [Bus] ID and the waiting time.
     */
    fun getEarliestBus(): Int {
        val buses = timetable.getWorkingBuses()
        val firstAvailableTimes = buses.associateWith { bus -> bus.getID().getLastArrivalTime() + bus.getID() }
        val waitingTimes = firstAvailableTimes.map { (id, time) -> id to time - timetable.arrivalTime }.toMap()
        val best = waitingTimes.minByOrNull { (_, waitingTime) -> waitingTime }!!
        return best.key.getID() * best.value
    }

    /**
     * Finds the timestamp in which all the buses depart at their given offsets as defined in the [timetable].
     * @see [BusTimetable.getBusesWithOffsets].
     *
     * For each bus, the timestamp (t) should satisfy: t + offset % id == 0
     * Once the timestamp has been found for a bus, the step ratio can be increased by a factory of the previous id.
     * This means each of the previous equations will be satisfied and skip lots of unnecessary processing.
     *
     * @return The timestamp that satisfies all departures at their offsets.
     */
    fun getOffsetDepartureTime(): Long {
        val buses = timetable.getBusesWithOffsets()
        var step = buses.first().second.toLong()
        var time = 0L
        buses.drop(1).forEach { (offset, id) ->
            while ((time + offset) % id != 0L) {
                time += step
            }
            step *= id
        }
        return time
    }

    private fun Int.getLastArrivalTime() = timetable.arrivalTime - (timetable.arrivalTime % this)
}