package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Abs : OneArg() {

    override fun onCalc(a: Double, paramSource: ((String) -> (Double))?): Double = Math.abs(a)

    override fun onAsString(a: String): String = "| $a |"

    override fun toString(): String = "Operation abs()"

    override fun onToConst(a: Operation): Const? {
        if (a is Const) {
            return Const(Math.abs(a.value))
        } else {
            return null
        }
    }

    override fun copy(): Abs = Abs()

}

fun abs(init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Abs() as Operation, init)

fun Node<Operation>.abs(init: Node<Operation>.() -> Unit = {}) = this.node(Abs() as Operation, init)