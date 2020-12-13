package io.github.tomplum.aoc.bus

data class Bus(private val id: String) {
    fun isOutOfService(): Boolean = id == "x"

    fun getID(): Int = if (!isOutOfService()) id.toInt() else throw IllegalStateException("This bus is out of service!")
}