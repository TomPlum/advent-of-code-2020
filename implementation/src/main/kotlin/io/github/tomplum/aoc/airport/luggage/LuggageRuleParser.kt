package io.github.tomplum.aoc.airport.luggage

class LuggageRuleParser private constructor() {
    companion object {
        fun parse(rules: List<String>): Set<LuggageNode> {
            val parsedRules = rules.map { it.replace(" bags", "").replace(" bag", "") }

            val uniqueNodes = parsedRules.flatMap { rule ->
                val info = rule.split(" contain ")
                val parentColour = info[0]
                val data = info[1].dropLast(1).split(", ").filter { it != "no other" }
                val parent = LuggageNode(parentColour)
                val children = data.map { datum ->
                    LuggageNode(datum.substring(2))
                }
                children + parent
            }.distinct()

            parsedRules.forEach { rule ->
                val info = rule.split(" contain ")
                val parentColour = info[0]
                val data = info[1].dropLast(1).split(", ").filter { it != "no other" }
                val parentNode = uniqueNodes.find { it == LuggageNode(parentColour) }!!

                data.fold(parentNode) { parent, datum ->
                    parent.apply {
                        val childColour = datum.substring(2)
                        val childQuantity = datum.take(1).toInt()
                        val child = uniqueNodes.find { it == LuggageNode(childColour) }!!
                        addChild(child, childQuantity)
                    }
                }
            }

            return uniqueNodes.filter { it.isRoot() }.toSet()
        }
    }
}