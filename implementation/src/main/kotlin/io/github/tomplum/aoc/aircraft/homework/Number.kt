package io.github.tomplum.aoc.aircraft.homework

data class Number(val value: Long): Term {
    override fun solve(): Number = this

    override fun getLength(): Int = 1

    override fun toString(): String = value.toString()
}