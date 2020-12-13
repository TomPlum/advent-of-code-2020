package io.github.tomplum.aoc.bus

class BusScheduler(private val notes: ScheduleNotes) {
    fun getEarliestBus(): Int {
        val firstAvailableTimes = notes.buses.map { id -> id to id.getLastArrivalTime() + id }.toMap()
        val waitingTimes = firstAvailableTimes.map { (id, time) -> id to time - notes.arrivalTime }.toMap()
        val best = waitingTimes.minByOrNull { (_, waitingTime) -> waitingTime } ?: throw IllegalArgumentException("No waiting times.")
        return best.key * best.value
    }

    private fun Int.getLastArrivalTime() = notes.arrivalTime - (notes.arrivalTime % this)
}