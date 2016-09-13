package me.ilich.graphworks

import me.ilich.graphworks.genetic.Genetic
import java.util.*

object App {

    private val random = Random()

    // sin(x)
    private val learnTable = mapOf<Double, Double>(
            Math.PI * 0.0 to Math.sin(Math.PI * 0.0),
            Math.PI * 0.25 to Math.sin(Math.PI * 0.25),
            Math.PI * 0.5 to Math.sin(Math.PI * 0.5),
            Math.PI * 0.75 to Math.sin(Math.PI * 0.75),
            Math.PI * 1.0 to Math.sin(Math.PI * 1.0),
            Math.PI * 1.25 to Math.sin(Math.PI * 1.25),
            Math.PI * 1.5 to Math.sin(Math.PI * 1.5),
            Math.PI * 1.75 to Math.sin(Math.PI * 1.75),
            Math.PI * 2.0 to Math.sin(Math.PI * 2.0)
    )

    private val examTable = mapOf<Double, Double>(
            Math.PI * 3.0 to Math.sin(Math.PI * 3.0)
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
            error += (expected - actual) * (expected - actual) * g.nodesCount
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
        val best = genetic.execute { generation, population ->
/*            println("*** generation $generation ***")
            population.forEach {
                println("${it.error} ${it.item.asString()}")
            }*/
        }

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
    }

}

