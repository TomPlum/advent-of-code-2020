package io.github.tomplum.aoc.island.room

import io.github.tomplum.libs.logging.AdventLogger

class Decrypter {

    fun getEncryptionKey(cardKey: Int, doorKey: Int): Long {
        val cardLoopSize = findLoopSize(cardKey)
        val doorLoopSize = findLoopSize(doorKey)
        return transformSubjectNumber(cardKey, doorLoopSize)
    }

    private fun transformSubjectNumber(subjectNumber: Int, loopSize: Int): Long {
        var key = 1L

        repeat(loopSize) {
            key *= subjectNumber
            key %= 20201227
        }
        return key
    }

    private fun findLoopSize(key: Int): Int {
        val subjectNumber = 7
        var loopSize = 0
        var value = 1

        while (value != key) {
            value *= subjectNumber
            value %= 20201227

            AdventLogger.info("Value: $value")

            loopSize++
        }
        return loopSize
    }
}