package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.aircraft.homework.ExpressionEngine
import io.github.tomplum.aoc.aircraft.homework.Lexer
import io.github.tomplum.aoc.aircraft.homework.strategy.AdvancedMath
import io.github.tomplum.aoc.aircraft.homework.strategy.BasicMath
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day18 : Solution<Long, Long> {
    private val input = InputReader.read<String>(Day(18))

    override fun part1(): Long {
        val expressions = Lexer().read(input.value)
        return ExpressionEngine(BasicMath()).sum(expressions)
    }

    override fun part2(): Long {
        val expressions = Lexer().read(input.value)
        return ExpressionEngine(AdvancedMath()).sum(expressions)
    }
}