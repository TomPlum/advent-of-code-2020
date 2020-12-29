package io.github.tomplum.aoc.island.room

/**
 * Reverse-engineers the cryptographic handshake between the [Door] and its [RoomKey].
 * Calculates the secret loop size value from one of the participants based on its public key.
 * The loop size is used with the other participants public key to find the encryption key.
 *
 * @param devices The pair of devices performing the handshake.
 */
class HandshakeDecrypter(private val devices: Pair<HandshakeParticipant, HandshakeParticipant>) {
    /**
     * Injects command in the network handshake data-stream to decrypt it.
     * @return The encryption key the handshake is trying to establish.
     */
    fun getEncryptionKey(): Long {
        val firstDeviceLoopSize = devices.first.getLoopSize()
        return devices.second.transformPublicKey(firstDeviceLoopSize)
    }
}