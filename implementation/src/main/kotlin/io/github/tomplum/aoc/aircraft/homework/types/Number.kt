package io.github.tomplum.aoc.aircraft.homework.types

import io.github.tomplum.aoc.aircraft.homework.strategy.OperationOrderStrategy

data class Number(val value: Long): Term {
    override fun solve(strategy: OperationOrderStrategy): Number = this

    override fun getLength(): Int = 1

    override fun toString(): String = value.toString()
}