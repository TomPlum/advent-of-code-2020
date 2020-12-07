package io.github.tomplum.aoc.airport.luggage

class LuggageProcessor(private val luggage: Set<LuggageNode>) {
    fun process(colour: String): Int = luggage
        .flatMap { node -> node.allChildren() }
        .find { it.value.colour == colour }!!
        .allParents().size
}