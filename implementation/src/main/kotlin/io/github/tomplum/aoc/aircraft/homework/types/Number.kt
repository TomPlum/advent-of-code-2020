package io.github.tomplum.aoc.aircraft.homework.types

data class Number(val value: Long): Token {
    override fun getLength(): Int = 1
    override fun toString(): String = value.toString()
}