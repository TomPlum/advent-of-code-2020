package io.github.tomplum.aoc.aircraft.homework.types

import io.github.tomplum.aoc.aircraft.homework.strategy.OperationOrderStrategy

interface Term: Token {
    fun solve(strategy: OperationOrderStrategy): Number
}