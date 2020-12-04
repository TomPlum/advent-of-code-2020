package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.passport.PassportReader
import io.github.tomplum.aoc.passport.PassportScanner
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day4 : Solution<Int> {
    override fun part1(): Int {
        val data = InputReader.read<String>(Day(4))
        val passports = PassportReader.read(data.value)
        return PassportScanner().scan(passports)
    }
}