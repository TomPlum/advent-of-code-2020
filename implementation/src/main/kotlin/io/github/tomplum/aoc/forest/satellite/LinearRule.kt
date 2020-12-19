package io.github.tomplum.aoc.forest.satellite

data class LinearRule(override val number: Int, val rules: List<Int>): MessageRule(number)