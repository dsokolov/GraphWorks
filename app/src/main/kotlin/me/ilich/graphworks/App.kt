package me.ilich.graphworks

import me.ilich.graphworks.operations.Mult
import me.ilich.graphworks.operations.Param
import me.ilich.graphworks.operations.ParamSource

object App {

    @JvmStatic fun main(params: Array<String>) {
        println("graph")

        val g = Graph(
                Graph.Pos(Mult(), 0),
                Graph.Pos(Param("x"), 1, 0),
                Graph.Pos(Param("x"), 2, 0)
        )
        for (x in -10..10) {
            val y = g.calc(object : ParamSource {
                override fun onParams(name: String): Double {
                    if (name == "x") {
                        return x.toDouble()
                    } else {
                        return 0.0
                    }
                }
            })
            println("$x -> $y")
        }

    }

}
