package io.github.tomplum.aoc.island.room

class HandshakeDecrypter(private val devices: Pair<HandshakeParticipant, HandshakeParticipant>) {

    fun getEncryptionKey(): Long {
        val firstDeviceLoopSize = devices.first.getLoopSize()
        return devices.second.transformPublicKey(firstDeviceLoopSize)
    }
}