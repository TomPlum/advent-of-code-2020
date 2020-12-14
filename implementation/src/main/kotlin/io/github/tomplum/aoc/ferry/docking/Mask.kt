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