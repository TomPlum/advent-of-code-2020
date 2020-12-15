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
        val floatingMask = applyIgnoreFloating(value)
        return getFloatingPermutations(floatingMask, mutableListOf())
    }

    private fun applyIgnoreFloating(value: Int): String {
        return mask.zip(value.to36bit()) { mask, bit ->
            when (mask) {
                'X', '1' -> mask
                else -> bit
            }
        }.joinToString("")
    }

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

    private fun Int.to36bit(): String = toString(2).padStart(36, '0')
}