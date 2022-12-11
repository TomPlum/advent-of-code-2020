package io.github.tomplum.aoc.solutions

import io.github.tomplum.libs.solutions.Solution
import io.github.tomplum.aoc.airport.passport.PassportReader
import io.github.tomplum.aoc.airport.passport.PassportScanner
import io.github.tomplum.aoc.airport.passport.strategy.RelaxedValidation
import io.github.tomplum.aoc.airport.passport.strategy.StrictValidation
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day4 : Solution<Int, Int> {
    private val input = InputReader.read<String>(Day(4)).asSingleString()

    override fun part1(): Int {
        val passports = PassportReader.read(input)
        val scanner = PassportScanner(RelaxedValidation())
        return scanner.scan(passports)
    }

    override fun part2(): Int {
        val passports = PassportReader.read(input)
        val scanner = PassportScanner(StrictValidation())
        return scanner.scan(passports)
    }
}