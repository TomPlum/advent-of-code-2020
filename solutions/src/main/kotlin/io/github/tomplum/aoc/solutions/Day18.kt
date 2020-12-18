package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.aircraft.homework.ExpressionEngine
import io.github.tomplum.aoc.aircraft.homework.Lexer
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day18 : Solution<Long, Int> {
    private val input = InputReader.read<String>(Day(18))

    override fun part1(): Long {
        val expressions = Lexer().read(input.value)
        return ExpressionEngine(expressions).solve()
    }
}