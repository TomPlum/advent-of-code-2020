package io.github.tomplum.aoc.forest.satellite.rule

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.github.tomplum.aoc.forest.satellite.Message
import org.junit.jupiter.api.Test

class MatchRuleTest {
    @Test
    fun isMatch() {
        assertThat(MatchRule('a').isMatch(Message("aabaa"))).isTrue()
    }

    @Test
    fun isNotMatch() {
        assertThat(MatchRule('b').isMatch(Message("aabaa"))).isFalse()
    }
}