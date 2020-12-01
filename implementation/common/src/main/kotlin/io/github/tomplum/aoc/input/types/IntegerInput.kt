package com.aoc.input.types

import io.github.tomplum.aoc.input.types.Input

class IntegerInput(input: List<String>) : Input<Int>(input.map { it.toInt() })