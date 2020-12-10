package io.github.tomplum.aoc.aircraft.adapter

class AdapterChain(private val ratings: List<Int>) {
    private val adapters = ratings.map { Adapter(it) }.toMutableList()

    fun getJoltageDelta(): Int {
        val chain = mutableListOf<Adapter>()
        var lastRating = adapters.first().rating
        while (adapters.isNotEmpty()) {
            val next = adapters
                .filter { (it.rating - 3) <= lastRating }
                .sortedBy { it.rating }
                .minByOrNull { it.rating } ?: break
            lastRating += next.rating
            adapters.remove(next)
            chain.add(next)
        }
        chain.apply {
            add(0, Adapter(0)) //Outlet
            add(Adapter(getDeviceAdapterRating())) //Device Built-In Adapter
        }
        val oneJoltDelta = chain.zipWithNext { a, b -> b.rating - a.rating == 1 }.count { it }
        val threeJoltDelta = chain.zipWithNext { a, b -> b.rating - a.rating == 3 }.count { it}
        return oneJoltDelta * threeJoltDelta
    }

    private fun getDeviceAdapterRating(): Int = ratings.maxOrNull()!! + 3
}