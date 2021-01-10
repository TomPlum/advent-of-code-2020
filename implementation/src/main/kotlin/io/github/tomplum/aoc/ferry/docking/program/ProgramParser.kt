package io.github.tomplum.aoc.ferry.docking.program

/**
 * Parses a text representation of an [InitProgram].
 */
class ProgramParser private constructor() {
    companion object {
        /**
         * Parses the initialisation program instructions.
         *
         * mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
         * mem[8] = 11
         * mem[7] = 101
         * mem[8] = 0
         *
         * @param data A list of input data in the above format.
         * @return The docking initialisation program
         */
        fun parse(data: List<String>): InitProgram {
            val programData = mutableMapOf<BitMask, MutableList<Instruction>>()
            var mask = BitMask("")
            data.forEach { line ->
                if (line.take(4) == "mask") {
                    mask = BitMask(line.takeLast(36))
                    programData[mask] = mutableListOf()
                } else {
                    val info = line.split(" = ")
                    val address = info[0].removePrefix("mem[").removeSuffix("]").toInt()
                    val value = info[1].toInt()
                    programData[mask]?.add(Instruction(address, value))
                }
            }
            return InitProgram(programData)
        }
    }
}