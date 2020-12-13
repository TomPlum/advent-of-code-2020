package io.github.tomplum.aoc.bus

data class ScheduleNotes(val arrivalTime: Int, val buses: List<Int>) {
    companion object {
        fun fromString(data: List<String>): ScheduleNotes {
            val earliestTimestamp = data[0].trim().toInt()
            val ids = data[1].split(",").filterNot { it == "x" }.map { it.toInt() }
            return ScheduleNotes(earliestTimestamp, ids)
        }
    }
}