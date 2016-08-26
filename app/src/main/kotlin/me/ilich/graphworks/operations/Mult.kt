package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Mult : TwoArg() {

    override fun onCalc(a: Double, b: Double, paramSource: (String) -> Double): Double = a * b

    override fun onAsString(a: String, b: String) = "( $a * $b )"

    override fun toString(): String{
        return "Operation *"
    }

}

fun mult(init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Mult() as Operation, init)

fun Node<Operation>.mult(init: Node<Operation>.() -> Unit = {}) = this.node(Mult() as Operation, init)