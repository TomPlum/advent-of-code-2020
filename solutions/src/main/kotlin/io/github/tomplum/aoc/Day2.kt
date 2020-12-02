package io.github.tomplum.aoc

import io.github.tomplum.aoc.input.InputReader
import io.github.tomplum.aoc.password.PasswordDatabase
import io.github.tomplum.aoc.password.SledRentalPolicy
import io.github.tomplum.aoc.password.TobogganPolicy

class Day2 : Solution<Int> {
    private val data = InputReader.read<String>(Day(2)).value

    override fun part1(): Int {
        val database = PasswordDatabase()
        database.import(data, SledRentalPolicy::class.java)
        return database.validate()
    }

    override fun part2(): Int {
        val database = PasswordDatabase()
        database.import(data, TobogganPolicy::class.java)
        return database.validate()
    }
}