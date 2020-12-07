package io.github.tomplum.aoc.airport.luggage

class LuggageProcessor(private val luggage: Set<LuggageNode>) {
    fun getBagColoursContaining(colour: String): Int = luggage.mapNotNull { it.getNode(colour) }.first().allParents().size

    fun getBagRequirement(colour: String): Int = luggage.mapNotNull { it.getNode(colour) }.first().getBagRequirement() - 1
}