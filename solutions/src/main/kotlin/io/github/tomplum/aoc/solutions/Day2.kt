package io.github.tomplum.aoc.solutions

import io.github.tomplum.aoc.Solution
import io.github.tomplum.aoc.password.PasswordDatabase
import io.github.tomplum.aoc.password.strategy.SledRentalPolicy
import io.github.tomplum.aoc.password.strategy.TobogganPolicy
import io.github.tomplum.libs.input.Day
import io.github.tomplum.libs.input.InputReader

class Day2 : Solution<Int> {
    private val data = InputReader.read<String>(Day(2)).value

    override fun part1(): Int {
        val database = PasswordDatabase(SledRentalPolicy::class.java)
        database.import(data)
        return database.validate()
    }

    override fun part2(): Int {
        val database = PasswordDatabase(TobogganPolicy::class.java)
        database.import(data)
        return database.validate()
    }
}