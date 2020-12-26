package io.github.tomplum.aoc.island.room

import io.github.tomplum.libs.logging.AdventLogger

abstract class HandshakeParticipant(private val publicKey: Long) {
    fun getLoopSize(): Long {
        val subjectNumber = 7L
        var loopSize = 0L
        var value = 1L

        while (value != publicKey) {
            value = value.transform(subjectNumber)
            AdventLogger.info("Value: $value")
            loopSize++
        }
        return loopSize
    }

    fun transformPublicKey(loopSize: Long): Long = (0 until loopSize).fold(1L) { key, _ -> key.transform(publicKey) }

    private fun Long.transform(subjectNumber: Long): Long = this * subjectNumber % 20201227L

}