package io.github.tomplum.aoc.bus

data class BusTimetable(val arrivalTime: Int, val buses: List<BusID>) {

    fun getWorkingBuses(): List<BusID> = buses.filterNot { it.isOutOfService() }

    companion object {
        fun fromNotes(data: List<String>): BusTimetable {
            val earliestTimestamp = data[0].trim().toInt()
            val ids = data[1].split(",").map { BusID(it) }
            return BusTimetable(earliestTimestamp, ids)
        }
    }
}