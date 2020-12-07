package io.github.tomplum.aoc.airport.luggage

import io.github.tomplum.aoc.type.TreeNode

class LuggageNode(val data: LuggageData) : TreeNode<LuggageData>(data) {
    override fun equals(other: Any?): Boolean {
        if (other !is LuggageNode) return false
        return data.colour == other.data.colour
    }
}