package me.ilich.graphworks

import me.ilich.graphworks.operations.Mult
import me.ilich.graphworks.operations.Param

object App {

    @JvmStatic fun main(params: Array<String>) {
        println("graph")

        val g = Graph(
                Graph.Pos(Mult(), 0),
                Graph.Pos(Param("x"), 1, 0),
                Graph.Pos(Param("x"), 2, 0)
        )
        for (x in -10..10) {
            val y = g.calc({
                if (it == "x") {
                    x.toDouble()
                } else {
                    0.0
                }

            })
            println("$x -> $y")
        }

    }

}
