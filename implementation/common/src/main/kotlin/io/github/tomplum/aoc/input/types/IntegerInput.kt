package io.github.tomplum.aoc.input.types

class IntegerInput(input: List<String>) : Input<Int>(input.map { it.toInt() })