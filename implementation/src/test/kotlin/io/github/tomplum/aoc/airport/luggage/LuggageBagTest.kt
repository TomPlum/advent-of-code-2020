package io.github.tomplum.aoc.airport.luggage

import assertk.assertThat
import assertk.assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class LuggageBagTest {
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
            assertThat(LuggageBag("shiny gold").getEnclosingBags()).isEmpty()
        }

        @Test
        fun singleChild() {
            val root = LuggageBag("shiny gold")
            val child = LuggageBag("dull yellow")
            root.addChild(child, 3)
            assertThat(child.getEnclosingBags()).containsOnly(root)
        }

        @Test
        fun multipleChildrenWithDepth() {
            val root = LuggageBag("shiny gold")
            val child = LuggageBag("dull yellow")
            val secondChild = LuggageBag("pale red")
            val thirdDeeperChild = LuggageBag("bright white")
            root.addChild(child, 3)
            root.addChild(secondChild, 5)
            secondChild.addChild(thirdDeeperChild, 3)
            assertThat(thirdDeeperChild.getEnclosingBags()).containsOnly(secondChild, root)
        }
    }

    @Nested
    inner class GetBagRequirement {
        @Test
        fun leaf() {
            val root = LuggageBag("shiny gold")
            val child = LuggageBag("dull yellow")
            root.addChild(child, 3)
            assertThat(child.getBagRequirement()).isEqualTo(1)
        }

        @Test
        fun singleChild() {
            val root = LuggageBag("shiny gold")
            val child = LuggageBag("dull yellow")
            root.addChild(child, 3)
            assertThat(root.getBagRequirement()).isEqualTo(4)
        }

        @Test
        fun multipleChildren() {
            val root = LuggageBag("shiny gold")
            val child = LuggageBag("dull yellow")
            val secondChild = LuggageBag("pale red")
            root.addChild(child, 3)
            root.addChild(secondChild, 5)
            assertThat(root.getBagRequirement()).isEqualTo(9)
        }

        @Test
        fun multipleChildrenWithDepth() {
            val root = LuggageBag("shiny gold")
            val child = LuggageBag("dull yellow")
            val secondChild = LuggageBag("pale red")
            val thirdDeeperChild = LuggageBag("bright white")
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
            assertThat(LuggageBag("shiny gold").isRoot()).isTrue()
        }

        @Test
        fun isNotRoot() {
            val root = LuggageBag("shiny gold")
            val child = LuggageBag("dull yellow")
            root.addChild(child, 3)
            assertThat(child.isRoot()).isFalse()
        }
    }

    @Nested
    inner class Equality {
        @Test
        fun sameColour() {
            assertThat(LuggageBag("shiny gold")).isEqualTo(LuggageBag("shiny gold"))
        }

        @Test
        fun differentColour() {
            assertThat(LuggageBag("shiny gold")).isNotEqualTo(LuggageBag("pale yellow"))
        }
    }


}