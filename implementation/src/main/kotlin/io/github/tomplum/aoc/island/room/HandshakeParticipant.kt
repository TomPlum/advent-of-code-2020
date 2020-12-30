package io.github.tomplum.aoc.island.room

/**
 * A single participant in a cryptographic network handshake.
 * @param publicKey The public key used to negotiate a network handshake.
 */
open class HandshakeParticipant(private val publicKey: Long) {
    /**
     * Calculates the devices' loop size based on its [publicKey].
     * @return The secret loop size value of the device.
     */
    fun getLoopSize(): Long {
        val subjectNumber = 7L
        var loopSize = 0L
        var value = 1L

        while (value != publicKey) {
            value = value.transform(subjectNumber)
            loopSize++
        }
        return loopSize
    }

    /**
     * Transforms the devices' [publicKey] based on its [loopSize].
     * @return The encryption key.
     */
    fun transformPublicKey(loopSize: Long): Long = (0 until loopSize).fold(1L) { key, _ -> key.transform(publicKey) }

    /**
     * Transforms the given [subjectNumber] using the cryptographic handshakes rules.
     * @param subjectNumber The number to be transformed.
     * @return The transformed value.
     */
    private fun Long.transform(subjectNumber: Long): Long = this * subjectNumber % 20201227L

}