package io.github.tomplum.aoc.forest.satellite

data class OrRule(override val number: Int, val first: Pair<Int, Int>, val second: Pair<Int, Int>): MessageRule(number)