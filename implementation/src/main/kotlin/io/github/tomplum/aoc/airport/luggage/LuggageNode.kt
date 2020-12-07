package io.github.tomplum.aoc.airport.luggage

data class LuggageNode(val colour: String) {
    var parents: MutableList<LuggageNode> = mutableListOf()

    var children: MutableMap<LuggageNode, Int> = mutableMapOf()

    fun addChild(node: LuggageNode, quantity: Int) {
        children[node] = quantity
        node.parents.add(this)
    }

    fun allChildren(): Set<LuggageNode> = children.keys.toSet() + children.keys.flatMap { it.allChildren() } + this

    fun allParents(): Set<LuggageNode> = parents.toSet() + parents.flatMap { it.allParents() }

    fun getBagRequirement(): Int = children.map { (bag, quantity) -> bag.getBagRequirement() * quantity }.sum() + 1

    fun isRoot(): Boolean = parents.size == 0

    override fun toString(): String {
        var s = colour
        if (children.isNotEmpty()) {
            s += " {" + children.map { it.toString() } + " }"
        }
        return s
    }
}