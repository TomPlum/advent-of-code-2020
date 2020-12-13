package io.github.tomplum.aoc.bus

class BusScheduler(private val timetable: BusTimetable) {
    fun getEarliestBus(): Int {
        val buses = timetable.getWorkingBuses()
        val firstAvailableTimes = buses.map { bus -> bus to bus.getID().getLastArrivalTime() + bus.getID() }.toMap()
        val waitingTimes = firstAvailableTimes.map { (id, time) -> id to time - timetable.arrivalTime }.toMap()
        val best = waitingTimes.minByOrNull { (_, waitingTime) -> waitingTime }!!
        return best.key.getID() * best.value
    }

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