package io.github.tomplum.aoc.aircraft.homework

import io.github.tomplum.aoc.aircraft.homework.strategy.OperationOrderStrategy

data class Expression(val tokens: List<Token>): Term {

    override fun solve(strategy: OperationOrderStrategy): Number = strategy.resolve(tokens)

    override fun getLength(): Int = tokens.sumBy { it.getLength() }

    override fun toString(): String = tokens.joinToString(" ") {
        if (it is Expression) "($it)" else it.toString()
    }
}