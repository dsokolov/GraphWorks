package me.ilich.graphworks.genetic

class Genetic<T>(
        val cross: (a: T, b: T) -> T,
        val errorMetric: (T) -> Double,
        val mutate: (T) -> T
) {

    inner class WithError<T>(val item: T, val error: Double){

        override fun equals(other: Any?): Boolean{
            if (this === other) return true
            if (other?.javaClass != javaClass) return false

            other as WithError<*>

            if (item != other.item) return false

            return true
        }

        override fun hashCode(): Int{
            return item?.hashCode() ?: 0
        }

    }

    fun generate(generation: List<T>): List<T> {
        var working = true
        var nextGeneration = mutableListOf<WithError<T>>()
        for (item in generation) {
            nextGeneration.add(WithError(item, errorMetric.invoke(item)))
        }
        while (working) {
            for (a in generation) {
                for (b in generation) {
                    if (a !== b) {
                        val c = cross.invoke(a, b)
                        nextGeneration.add(WithError(c, errorMetric.invoke(c)))
                    }
                }
            }
            val iterate = nextGeneration.listIterator()
            val mutated = mutableListOf<WithError<T>>()
            while (iterate.hasNext()) {
                val item = iterate.next()
                val t = mutate.invoke(item.item)
                mutated.add(WithError(t, errorMetric.invoke(t)))
            }
            nextGeneration.addAll(mutated)

            val set = mutableSetOf<WithError<T>>()
            set.addAll(nextGeneration)
            nextGeneration = mutableListOf<WithError<T>>()
            nextGeneration.addAll(set)

            nextGeneration.sortBy { it.error }
            nextGeneration = nextGeneration.subList(0, generation.size)

            println("****")
            println("${nextGeneration[0].item} ${nextGeneration[0].error}")
            println("${nextGeneration[1].item} ${nextGeneration[1].error}")
            working = nextGeneration[0].error > 10
        }
        val r = mutableListOf<T>()
        for (item in nextGeneration) {
            r.add(item.item)
        }
        return r
    }

}