package io.github.tomplum.aoc.extensions

fun Int.toBinary(bits: Int): IntArray = Integer.toBinaryString(this)
    .padStart(bits, '0')
    .foldIndexed(IntArray(bits)) { i, arr, value ->
        arr.apply { set(i, value.toString().toInt()) }
    }
