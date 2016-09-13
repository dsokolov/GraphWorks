package me.ilich.graphworks

import me.ilich.graphworks.genetic.Genetic
import java.util.*

object App {

    private val random = Random()

    private val f: (x: Double) -> (Double) = {
        x ->
        x * x
    }

    private val learnTable = mapOf<Double, Double>(
            -5.0 to 25.0,
            -4.0 to 16.0,
            -3.0 to 9.0,
            -2.0 to 4.0,
            -1.0 to 2.0,
            0.0 to 0.0,
            1.0 to 1.0,
            2.0 to 4.0,
            3.0 to 9.0,
            4.0 to 16.0,
            5.0 to 25.0
    )

    private val examTable = mapOf<Double, Double>(
            -6.0 to 36.0,
            -5.0 to 25.0,
            0.0 to 0.0,
            5.0 to 25.0,
            6.0 to 36.0
    )

    private fun calcError(g: Graph): Double {
        var error = 0.0
        for (x in learnTable.keys) {
            val expected = learnTable[x] as Double
            val actual = g.calc({
                if (it == "x") {
                    x.toDouble()
                } else {
                    0.0
                }
            })
            error += (expected - actual) * (expected - actual)
        }
        return Math.sqrt(error)
    }

    @JvmStatic fun main(params: Array<String>) {
        println("genetic")

        val genetic = Genetic<Graph>(
                startPopulation = {
                    val l = mutableListOf<Graph>()
                    for (i in 0..9) {
                        l.add(GraphFactory.randomGraph())
                    }
                    l
                },
                errorFun = {
                    calcError(it)
                },
                crossFun = { a, b ->
                    val crossIndex = random.nextInt(a.nodesCount)
                    a.replaceNode(crossIndex, b)
                }
        )
        val best = genetic.execute(maxGenerationCount = 99, expectedError = 2.1) { generation, error, population ->
            println("*** generation $generation error = $error ***")
            population.forEach {
                // println("${it.error} ${it.item.asString()}")
            }
        }

        if (best != null) {
            println(best.item.asString())
            for (x in examTable.keys) {
                val y = best.item.calc({
                    if (it == "x") {
                        x.toDouble()
                    } else {
                        0.0
                    }
                })
                println("y($x) = $y, expected ${examTable[x]}")
            }
        } else {
            println("null")
        }
    }

}


