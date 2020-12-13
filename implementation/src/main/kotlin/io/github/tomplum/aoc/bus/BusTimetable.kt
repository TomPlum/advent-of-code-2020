package io.github.tomplum.aoc.bus

data class BusTimetable(val arrivalTime: Int, val buses: List<Bus>) {

    fun getWorkingBuses(): List<Bus> = buses.filterNot { it.isOutOfService() }

    fun getBusesWithOffsets(): List<Pair<Int, Int>> = buses.mapIndexedNotNull { i, bus ->
        if (bus.isOutOfService()) null else i to bus.getID()
    }

    companion object {
        fun fromNotes(data: List<String>): BusTimetable {
            val earliestTimestamp = data[0].trim().toInt()
            val ids = data[1].split(",").map { Bus(it) }
            return BusTimetable(earliestTimestamp, ids)
        }
    }
}