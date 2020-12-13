package io.github.tomplum.aoc.bus

class BusScheduler(private val notes: BusTimetable) {
    fun getEarliestBus(): Int {
        val buses = notes.getWorkingBuses()
        val firstAvailableTimes = buses.map { id -> id to id.getValue().getLastArrivalTime() + id.getValue() }.toMap()
        val waitingTimes = firstAvailableTimes.map { (id, time) -> id to time - notes.arrivalTime }.toMap()
        val best = waitingTimes.minByOrNull { (_, waitingTime) -> waitingTime } ?: throw IllegalArgumentException("No waiting times.")
        return best.key.getValue() * best.value
    }

    private fun Int.getLastArrivalTime() = notes.arrivalTime - (notes.arrivalTime % this)
}