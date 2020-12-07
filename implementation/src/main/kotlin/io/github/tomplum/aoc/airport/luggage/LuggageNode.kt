package io.github.tomplum.aoc.airport.luggage

data class LuggageNode(val colour: String) {
    private val parents: MutableList<LuggageNode> = mutableListOf()
    private val children: MutableMap<LuggageNode, Int> = mutableMapOf()

    fun addChild(node: LuggageNode, quantity: Int) {
        children[node] = quantity
        node.parents.add(this)
    }

    fun getNode(colour: String): LuggageNode? {
        if (this.colour == colour) return this
        return children.keys.map { it.getNode(colour) }.firstOrNull()
    }

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