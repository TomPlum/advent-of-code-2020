package io.github.tomplum.aoc.island.room

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class HandshakeDecrypterTest {
    @Test
    fun example() {
        val devices = Pair(KeyCard(5764801), Door(17807724))
        val encryptionKey = HandshakeDecrypter(devices).getEncryptionKey()
        assertThat(encryptionKey).isEqualTo(14897079)
    }
}