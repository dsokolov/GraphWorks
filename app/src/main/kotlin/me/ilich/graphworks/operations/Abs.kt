package me.ilich.graphworks.operations

import me.ilich.graphworks.Node2

class Abs : OneArg() {

    override fun onCalc(a: Double, paramSource: (String) -> Double): Double = Math.abs(a)

    override fun onAsString(a: String): String = "| $a |"

    override fun toString(): String {
        return "Operation abs()"
    }

}

fun abs(init: Node2<Operation>.() -> Unit = {}): Node2<Operation> = node2(Abs() as Operation, init)

fun Node2<Operation>.abs(init: Node2<Operation>.() -> Unit = {}) = this.node2(Abs() as Operation, init)