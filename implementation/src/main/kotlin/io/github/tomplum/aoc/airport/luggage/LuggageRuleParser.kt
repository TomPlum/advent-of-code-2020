package io.github.tomplum.aoc.airport.luggage

class LuggageRuleParser private constructor() {
    companion object {
        fun parse(rules: List<String>): LuggageNode {
            val existing = mutableListOf<LuggageNode>()
            val nodes = rules.map { it.replace(" bags", "").replace(" bag", "") }.map { rule ->
                val info = rule.split(" contain ")
                val parentColour = info[0]
                val data = info[1].dropLast(1).split(", ").filter { it != "no other" }
                data.fold(LuggageNode(LuggageData(parentColour))) { parent, datum ->
                    parent.apply {
                        val childColour = datum.substring(2)
                        val childQuantity = datum.take(1).toInt()
                        val child = LuggageNode(LuggageData(childColour, childQuantity))
                        if (existing.contains(child)) {
                            addChild(existing.find { it == child }!!)
                        } else {
                            addChild(child)
                            existing.add(child)
                        }
                    }
                }
            }.toMutableList()

            val roots = nodes.fold(mutableListOf<LuggageNode>()) { roots, node ->
                roots.apply {
                    if (!nodes.flatMap { it.children.map { it.value.colour } }.contains(node.data.colour)) {
                        add(node)
                    }
                }
            }

            var next = roots
            val evaluated = mutableSetOf<LuggageNode>()

            while(next.isNotEmpty()) {
                val childrenAdded = mutableListOf<LuggageNode>()
                next.forEach { parentCandidate ->
                    val childColours = parentCandidate.children.map { it.value.colour }
                    val children = nodes.filter { candidate ->
                        childColours.contains(candidate.data.colour)
                    }
                    children.forEach { child ->
                        val grandChildren = child.children
                        parentCandidate.children.forEach { fakeChild ->
                            val actualChild = existing.find { it.value.colour == fakeChild.value.colour }!!
                            if (!evaluated.contains(actualChild)) {
                                grandChildren.forEach { actualChild.addChild(it) }
                                evaluated.add(actualChild as LuggageNode)
                                childrenAdded.add(actualChild)
                            }
                        }
                    }
                }
                next = childrenAdded.distinct().toMutableList()
            }

            return nodes.first()

        }
    }
}