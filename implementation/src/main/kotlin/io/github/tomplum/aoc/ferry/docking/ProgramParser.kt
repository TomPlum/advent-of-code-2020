package io.github.tomplum.aoc.ferry.docking

class ProgramParser private constructor() {
    companion object {
        fun parse(data: List<String>): InitProgram {
            val programData = mutableMapOf<BitMask, MutableList<Instruction>>()
            var mask = BitMask("")
            data.forEach {
                if (it.take(4) == "mask") {
                    mask = BitMask(it.takeLast(36))
                    programData[mask] = mutableListOf()
                } else {
                    val info = it.split(" = ")
                    val address = info[0].removePrefix("mem[").removeSuffix("]").toInt()
                    val value = info[1].toInt()
                    programData[mask]?.add(Instruction(address, value))
                }
            }
            return InitProgram(programData)
        }
    }
}