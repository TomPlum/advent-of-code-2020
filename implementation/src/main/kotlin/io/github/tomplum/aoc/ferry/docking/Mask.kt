package io.github.tomplum.aoc.ferry.docking

data class Mask(private val bits: IntArray = IntArray(36)) {
    fun put(i: Int, bit: Int) {
        bits[i] = bit
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
}