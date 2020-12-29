package io.github.tomplum.aoc.island.room

/**
 * A door to a hotel room. Protected by an electronic lock.
 * Requires a [RoomKey] to open.
 * @param publicKey The public key used to negotiate a network handshake.
 */
class Door(publicKey: Long): HandshakeParticipant(publicKey)