package io.github.tomplum.aoc

import io.github.tomplum.aoc.input.InputReader
import io.github.tomplum.aoc.password.PasswordDatabase

class Day2 : Solution<Int> {
    override fun part1(): Int {
        val data = InputReader.read<String>(Day(2)).value
        val database = PasswordDatabase()
        database.import(data)
        return database.validate()
    }
}