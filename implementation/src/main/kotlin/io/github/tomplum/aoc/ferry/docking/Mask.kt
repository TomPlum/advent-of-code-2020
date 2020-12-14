package io.github.tomplum.aoc.ferry.docking

import io.github.tomplum.aoc.extensions.toBinary

data class Mask(private val size: Int) {
    private val bits: IntArray = IntArray(size)

    fun put(i: Int, bit: Int) {
        bits[i] = bit
    }

    fun applyTo(value: Int): IntArray {
        val binary = value.toBinary(size)
        bits.forEachIndexed { i, bit ->
            if (bit != 2) binary[i] = bit
        }
        return binary
    }

    fun applyFloatingTo(value: Int): List<IntArray> {
        val indexedBits = bits.mapIndexed { i, bit -> i to bit }
        val floatingBits = indexedBits.filter { (_, bit) -> bit == 2 }
        val regularBits = indexedBits.filter { (_, bit) -> bit == 1 }
        val indexedFloating = bits.foldIndexed(mutableListOf<Pair<Int, Int>>()) { i, acc, bit ->
            if (bit == 2) {
                acc.apply {
                    add(Pair(i, 0))
                    add(Pair(i, 1))
                }
            } else {
                acc
            }
        }
        val combinations = indexedFloating.powerSet().filter { it.size == bits.count { it == 2 } }.filter { it.first().first != it.last().first }
        return combinations.map { floating ->
            (floating + regularBits).maskBits(value.toBinary(36).clone())
        }
    }

    private fun Collection<Pair<Int, Int>>.maskBits(binary: IntArray) = forEach { (i, bit) -> binary[i] = bit }.let { binary }

    fun <T> Collection<T>.powerSet(): Set<Set<T>> = when {
        isEmpty() -> setOf(setOf())
        else -> drop(1).powerSet().let { outer -> outer + outer.map { inner -> inner + first() } }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Mask

        if (!bits.contentEquals(other.bits)) return false

        return true
    }

    override fun hashCode(): Int {
        return bits.contentHashCode()
    }

    override fun toString(): String = bits.joinToString()

}