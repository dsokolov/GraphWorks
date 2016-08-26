package me.ilich

import me.ilich.graphworks.Node2
import me.ilich.graphworks.operations.*

fun main(args: Array<String>) {

    val c = add() {
        mult {
            param("x")
            param("x")
        }
        param("x")
    }
    val paramSource: (String) -> Double = {
        when (it) {
            "x" -> 2.0
            else -> 0.0
        }
    }
    val d = calc(c, paramSource)
    println(d)

}

fun calc(node: Node2<out Operation>, paramSource: (String) -> Double): Double {
    val args = mutableListOf<Double>()
    for (sub in node.children) {
        args.add(calc(sub, paramSource))
    }
    return node.value.calc(*args.toDoubleArray(), paramSource = paramSource)
}