package me.ilich.graphworks.genetic

import java.util.*

class Genetic<T>(
        val startPopulation: () -> (List<T>),
        val errorFun: (T) -> (Double),
        val crossFun: (T, T) -> (T)
) {

    fun execute(
            onNewGeneration: (generation: Int, population: List<Being<T>>) -> Unit
    ): Being<T> {

        var generationNum = 0
        val population = mutableListOf<T>()
        val beings = mutableListOf<Being<T>>()

        population.addAll(startPopulation())
        onNewGeneration(generationNum, beings)
        while (generationNum < 10) {
            generationNum++
            val children = mutableListOf<T>()
            for (beingA in beings) {
                for (beingB in beings) {
                    val child = crossFun(beingA.item, beingB.item)
                    children.add(child)
                }
            }
            population.addAll(children)
            removeDuplicates(population)
            fillBeings(beings, population)
            washUpBeings(beings)
            population.clear()
            beings.forEach { population.add(it.item) }
            onNewGeneration(generationNum, beings)
        }
        return beings[0]
    }

    private fun fillBeings(beings: MutableList<Being<T>>, population: List<T>) {
        beings.clear()
        for (p in population) {
            val error = errorFun(p)
            val being = Being(p, error)
            beings.add(being)
        }
        beings.sortBy { it.error }
    }

    fun removeDuplicates(list: MutableList<T>) {
        val set = HashSet<T>()
        for (item in list) {
            set.add(item)
        }
        list.clear()
        list.addAll(set)
    }

    private fun washUpBeings(beings: MutableList<Being<T>>) {
        var errorSum = 0.0;
        beings.forEach { errorSum += it.error }
        val avgError = errorSum / beings.size
        val iterator = beings.iterator()
        while (iterator.hasNext()) {
            val being = iterator.next()
            if (being.error >= avgError) {
                iterator.remove()
            }
        }
        while (beings.size > 20) {
            beings.removeAt(beings.size - 1)
        }
    }

    class Being<T>(
            val item: T,
            val error: Double
    )

}