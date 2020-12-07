package io.github.tomplum.aoc.airport.luggage

class LuggageProcessor(private val luggage: Set<LuggageNode>) {
    fun processBagColoursContaining(colour: String): Int = luggage
        .flatMap { node -> node.allChildren() }
        .find { it.colour == colour }!!
        .allParents().size

    fun processBagsContainedIn(colour: String): Int = luggage
        .flatMap { node -> node.allChildren() }
        .find { it.colour == colour }!!
        .getBagRequirement() - 1
}