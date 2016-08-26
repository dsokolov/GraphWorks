package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Sub : TwoArg() {

    override fun onCalc(a: Double, b: Double, paramSource: (String) -> Double): Double = a - b

    override fun onAsString(a: String, b: String) = "( $a - $b )"

    override fun toString(): String{
        return "Operation -"
    }

}

fun sub(init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Sub() as Operation, init)

fun Node<Operation>.sub(init: Node<Operation>.() -> Unit = {}) = this.node(Sub() as Operation, init)