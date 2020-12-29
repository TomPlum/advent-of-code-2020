package io.github.tomplum.aoc.island.room

/**
 * The room key is a small RFID card used to open the door.
 * @param publicKey The public key used to negotiate a network handshake.
 */
class RoomKey(publicKey: Long): HandshakeParticipant(publicKey)