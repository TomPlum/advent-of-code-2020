package io.github.tomplum.aoc.airport.luggage

class LuggageRuleParser private constructor() {
    companion object {
        fun parse(rules: List<String>): Set<LuggageNode> {
            val uniqueNodes = rules.map { it.replace(" bags", "").replace(" bag", "") }.flatMap { rule ->
                val info = rule.split(" contain ")
                val parentColour = info[0]
                val data = info[1].dropLast(1).split(", ").filter { it != "no other" }
                val parent = LuggageNode(LuggageData(parentColour))
                val children = data.map { datum ->
                    LuggageNode(LuggageData(datum.substring(2), datum.take(1).toInt()))
                }
                children + parent
            }.distinctBy { it.value.colour }

            rules.map { it.replace(" bags", "").replace(" bag", "") }.forEach { rule ->
                val info = rule.split(" contain ")
                val parentColour = info[0]
                val data = info[1].dropLast(1).split(", ").filter { it != "no other" }
                val parentNode = uniqueNodes.find { it == LuggageNode(LuggageData(parentColour)) }!!

                data.fold(parentNode) { parent, datum ->
                    parent.apply {
                        val childColour = datum.substring(2)
                        val childQuantity = datum.take(1).toInt()
                        val child = uniqueNodes.find { it == LuggageNode(LuggageData(childColour, childQuantity)) }!!
                        addChild(child)
                    }
                }
            }

            return uniqueNodes.filter { it.isRoot() }.toSet()
        }
    }
}