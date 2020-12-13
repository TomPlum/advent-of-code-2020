package io.github.tomplum.aoc.bus

data class BusID(private val value: String) {
    fun isOutOfService(): Boolean = value == "x"

    fun getValue(): Int = if (!isOutOfService()) value.toInt() else throw IllegalStateException("This bus is out of service!")
}