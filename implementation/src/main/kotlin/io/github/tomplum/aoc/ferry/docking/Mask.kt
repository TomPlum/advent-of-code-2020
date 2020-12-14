package io.github.tomplum.aoc.ferry.docking

data class Mask(private val mask: String) {

    fun applyTo(value: Int): String {
        return mask.zip(value.to36bit()) { mask, bit ->
            when (mask) {
                '0', '1' -> mask
                else -> bit
            }
        }.joinToString("")
    }

    fun applyFloatingTo(value: Int): List<String> {
        val indexedBits = mask.mapIndexed { i, bit -> i to bit }
        val regularBits = indexedBits.filter { (_, bit) -> bit == '1' }
        val indexedFloating = mask.foldIndexed(mutableListOf<Pair<Int, Char>>()) { i, acc, bit ->
            if (bit == 'X') {
                acc.apply {
                    add(Pair(i, '0'))
                    add(Pair(i, '1'))
                }
            } else {
                acc
            }
        }
        val combinations = indexedFloating.powerSet()
            .filter { it.size == mask.count { it == 'X' } }
            .filter { it.first().first != it.last().first }

        return combinations.map { floating ->
            (floating + regularBits).maskBits(value.to36bit())
        }
    }

    fun <T> Collection<T>.powerSet(): Set<Set<T>> = when {
        isEmpty() -> setOf(setOf())
        else -> drop(1).powerSet().let { outer -> outer + outer.map { inner -> inner + first() } }
    }

    private fun Int.to36bit(): String = toString(2).padStart(36, '0')

    private fun Collection<Pair<Int, Char>>.maskBits(binary: String): String {
        val bits = binary.toCharArray()
        this.forEach { (i, bit) ->
            bits[i] = bit
        }
        return bits.joinToString("")
    }
}