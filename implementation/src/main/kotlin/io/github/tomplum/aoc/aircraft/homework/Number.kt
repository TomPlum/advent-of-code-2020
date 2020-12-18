package io.github.tomplum.aoc.aircraft.homework

data class Number(val value: Int): Term {
    override fun solve(): Int = value

    override fun getLength(): Int = 1

    override fun toString(): String = value.toString()
}