package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Div : TwoArg() {

    override fun onCalc(a: Double, b: Double, paramSource: (String) -> Double): Double = a / b

    override fun onAsString(a: String, b: String) = "( $a / $b )"

    override fun toString(): String{
        return "Operation /"
    }

}

fun div(init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Div() as Operation, init)

fun Node<Operation>.div(init: Node<Operation>.() -> Unit = {}) = this.node(Div() as Operation, init)