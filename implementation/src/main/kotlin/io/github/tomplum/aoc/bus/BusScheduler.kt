package io.github.tomplum.aoc.bus

class BusScheduler(private val notes: BusTimetable) {
    fun getEarliestBus(): Int {
        val buses = notes.getWorkingBuses()
        val firstAvailableTimes = buses.map { id -> id to id.getValue().getLastArrivalTime() + id.getValue() }.toMap()
        val waitingTimes = firstAvailableTimes.map { (id, time) -> id to time - notes.arrivalTime }.toMap()
        val best = waitingTimes.minByOrNull { (_, waitingTime) -> waitingTime } ?: throw IllegalArgumentException("No waiting times.")
        return best.key.getValue() * best.value
    }

    fun getOffsetDepartureTime(): Long {
        val buses = notes.buses.mapIndexedNotNull { i, id -> if (id.isOutOfService()) null else i to id.getValue() }
        var step = buses.first().second
        var t = 0L
        buses.drop(1).forEach { (i, bus) ->
            while ((t + i) % bus != 0L) {
                t += step
            }
            step *= bus
        }
        return t
    }

    private fun Int.getLastArrivalTime() = notes.arrivalTime - (notes.arrivalTime % this)
}