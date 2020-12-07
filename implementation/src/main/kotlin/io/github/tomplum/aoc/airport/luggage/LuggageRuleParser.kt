package io.github.tomplum.aoc.airport.luggage

class LuggageRuleParser private constructor() {
    companion object {
        fun parse(rules: List<String>): Set<LuggageNode> {
            val existing = mutableListOf<LuggageNode>()

            return rules.map { it.replace(" bags", "").replace(" bag", "") }.map { rule ->
                val info = rule.split(" contain ")
                val parentColour = info[0]
                val data = info[1].dropLast(1).split(", ").filter { it != "no other" }
                val existingChildren = existing.flatMap { it.allChildren() }.map { it as LuggageNode }.distinct()
                val potentialNode = LuggageNode(LuggageData(parentColour))
                val parentNode = existingChildren.find { it == potentialNode } ?: potentialNode

                val node = data.fold(parentNode) { parent, datum ->
                    parent.apply {
                        val childColour = datum.substring(2)
                        val childQuantity = datum.take(1).toInt()
                        val child = LuggageNode(LuggageData(childColour, childQuantity))
                        if (existingChildren.contains(child)) {
                            addChild(existingChildren.find { it == child }!!)
                        } else {
                            addChild(child)
                        }
                    }
                }
                if (node.parents.size == 0) existing.add(node)
                node
            }.filter { it.isRoot() }.toSet()
        }
    }
}