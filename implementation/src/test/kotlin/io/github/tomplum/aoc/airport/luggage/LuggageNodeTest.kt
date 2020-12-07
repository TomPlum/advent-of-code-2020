package io.github.tomplum.aoc.airport.luggage

import assertk.assertThat
import assertk.assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LuggageNodeTest {
    @Nested
    inner class AddChild {

    }

    @Nested
    inner class GetNode {

    }

    @Nested
    inner class GetAncestors {
        @Test
        fun root() {
            assertThat(LuggageNode("shiny gold").getAncestors()).isEmpty()
        }

        @Test
        fun singleChild() {
            val root = LuggageNode("shiny gold")
            val child = LuggageNode("dull yellow")
            root.addChild(child, 3)
            assertThat(child.getAncestors()).containsOnly(root)
        }

        @Test
        fun multipleChildrenWithDepth() {
            val root = LuggageNode("shiny gold")
            val child = LuggageNode("dull yellow")
            val secondChild = LuggageNode("pale red")
            val thirdDeeperChild = LuggageNode("bright white")
            root.addChild(child, 3)
            root.addChild(secondChild, 5)
            secondChild.addChild(thirdDeeperChild, 3)
            assertThat(thirdDeeperChild.getAncestors()).containsOnly(secondChild, root)
        }
    }

    @Nested
    inner class GetBagRequirement {
        @Test
        fun leaf() {
            val root = LuggageNode("shiny gold")
            val child = LuggageNode("dull yellow")
            root.addChild(child, 3)
            assertThat(child.getBagRequirement()).isEqualTo(1)
        }

        @Test
        fun singleChild() {
            val root = LuggageNode("shiny gold")
            val child = LuggageNode("dull yellow")
            root.addChild(child, 3)
            assertThat(root.getBagRequirement()).isEqualTo(4)
        }

        @Test
        fun multipleChildren() {
            val root = LuggageNode("shiny gold")
            val child = LuggageNode("dull yellow")
            val secondChild = LuggageNode("pale red")
            root.addChild(child, 3)
            root.addChild(secondChild, 5)
            assertThat(root.getBagRequirement()).isEqualTo(9)
        }

        @Test
        fun multipleChildrenWithDepth() {
            val root = LuggageNode("shiny gold")
            val child = LuggageNode("dull yellow")
            val secondChild = LuggageNode("pale red")
            val thirdDeeperChild = LuggageNode("bright white")
            root.addChild(child, 3)
            root.addChild(secondChild, 5)
            secondChild.addChild(thirdDeeperChild, 3)
            assertThat(root.getBagRequirement()).isEqualTo(24)
        }
    }

    @Nested
    inner class IsRoot {
        @Test
        fun isRoot() {
            assertThat(LuggageNode("shiny gold").isRoot()).isTrue()
        }

        @Test
        fun isNotRoot() {
            val root = LuggageNode("shiny gold")
            val child = LuggageNode("dull yellow")
            root.addChild(child, 3)
            assertThat(child.isRoot()).isFalse()
        }
    }

    @Nested
    inner class Equality {
        @Test
        fun sameColour() {
            assertThat(LuggageNode("shiny gold")).isEqualTo(LuggageNode("shiny gold"))
        }

        @Test
        fun differentColour() {
            assertThat(LuggageNode("shiny gold")).isNotEqualTo(LuggageNode("pale yellow"))
        }
    }


}