package io.github.tomplum.aoc.ferry.docking.program

/**
 * A bit-mask consisting of either 0, 1 or X.
 *
 * An X indicates a 'Floating Bit' that is in a state of super-position and is therefore
 * simultaneously a 0 and a 1. This means that every [mask] with a floating-bit has
 * 2^N permutations wheres N is the number of floating-bits.
 *
 * @param mask The string of bits.
 */
data class BitMask(private val mask: String) {

    /**
     * Masks the given [value] with the [mask] bits.
     * Any floating-bits (X) are ignored.
     *
     * E.g;
     * [value]:  000000000000000000000000000000001011  (decimal 11)
     * [mask]:   XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
     * result:   000000000000000000000000000001001001  (decimal 73)
     *
     * @return The binary representation of the masked [value].
     */
    fun applyTo(value: Int): String {
        return mask.zip(value.to36bit()) { mask, bit ->
            when (mask) {
                '0', '1' -> mask
                else -> bit
            }
        }.joinToString("")
    }

    /**
     * Masks the given [value] with the [mask] bits.
     * Floating-bits are taken into account and so all mask permutations are applied.
     *
     * E.g;
     *
     * First, [applyIgnoreFloating] masks the [value] and ignores floating-bits.
     * [value]: 000000000000000000000000000000101010  (decimal 42)
     * [mask]:  000000000000000000000000000000X1001X
     * result:  000000000000000000000000000000X1101X
     *
     * Then, [getFloatingPermutations] generates all combinations of masks.
     * 000000000000000000000000000000011010  (decimal 26)
     * 000000000000000000000000000000011011  (decimal 27)
     * 000000000000000000000000000000111010  (decimal 58)
     * 000000000000000000000000000000111011  (decimal 59)
     *
     * @return The binary representations of all the masked values.
     */
    fun applyFloatingTo(value: Int): List<String> {
        val floatingMask = applyIgnoreFloating(value)
        return getFloatingPermutations(floatingMask, mutableListOf())
    }

    /**
     * Masks the given [value] with only literal floating and 1 bits.
     * 0 bits are ignored.
     *
     * E.g;
     * [value]: 000000000000000000000000000000101010  (decimal 42)
     * [mask]:  000000000000000000000000000000X1001X
     * result:  000000000000000000000000000000X1101X
     *
     * @return The masked value with floating bits intact.
     */
    private fun applyIgnoreFloating(value: Int): String {
        return mask.zip(value.to36bit()) { mask, bit ->
            when (mask) {
                'X', '1' -> mask
                else -> bit
            }
        }.joinToString("")
    }

    /**
     * Generates all the combinations of masks by resolving the floating-bits state of
     * super-position. For a [mask] with N floating-bits, 2^N masks will be produced.
     * @param mask The mask containing 'X' floating-bit values.
     * @param masks The list to collect the mask combinations in.
     * @return A list of all mask combinations.
     */
    private fun getFloatingPermutations(mask: String, masks: MutableList<String>): List<String> {
        if (mask.contains('X')) {
            val firstCandidate = mask.replaceFirst('X', '0')
            if (!masks.contains(firstCandidate)) {
                getFloatingPermutations(firstCandidate, masks)
            }

            val secondCandidate = mask.replaceFirst('X', '1')
            if (!masks.contains(secondCandidate)) {
                getFloatingPermutations(secondCandidate, masks)
            }
        }
        if (!mask.contains('X')) masks.add(mask)
        return masks
    }

    /**
     * Pads a binary string with 0s until it is 36 bits in length.
     * @return A 36-bit representation of the given binary string.
     */
    private fun Int.to36bit(): String = toString(2).padStart(36, '0')
}