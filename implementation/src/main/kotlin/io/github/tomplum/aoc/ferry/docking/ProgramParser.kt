package io.github.tomplum.aoc.ferry.docking

class ProgramParser private constructor() {
    companion object {
        fun parse(data: List<String>): InitialisationProgram {
            val programData = mutableMapOf<Mask, MutableList<Instruction>>()
            var mask = Mask()
            data.forEach {
                if (it.take(4) == "mask") {
                    mask = Mask()
                    it.takeLast(36).forEachIndexed { index, bit ->
                        if (bit.isDigit()) mask.put(index, bit.toString().toInt())
                    }
                    programData[mask] = mutableListOf()
                } else {
                    val info = it.split(" = ")
                    val address = info[0].removePrefix("mem[").removeSuffix("]").toInt()
                    val value = info[1].toInt()
                    programData[mask]?.add(Instruction(address, value))
                }
            }
            return InitialisationProgram(programData)
        }
    }
}