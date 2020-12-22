package io.github.tomplum.aoc.raft.cards

enum class Player(val value: Int) {
    PLAYER_1(1), PLAYER_2(2);

    override fun toString(): String = value.toString()
}