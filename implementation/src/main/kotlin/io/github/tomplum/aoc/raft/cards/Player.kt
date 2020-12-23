package io.github.tomplum.aoc.raft.cards

/**
 * A single player in a [RecursiveCombatGame].
 */
enum class Player(private val value: Int) {
    PLAYER_1(1), PLAYER_2(2);

    override fun toString(): String = value.toString()
}