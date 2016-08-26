package me.ilich.graphworks.operations

import me.ilich.graphworks.Node2

class Div : TwoArg() {

    override fun onCalc(a: Double, b: Double, paramSource: (String) -> Double): Double = a / b

    override fun onAsString(a: String, b: String) = "( $a / $b )"

    override fun toString(): String{
        return "Operation /"
    }

}

fun div(init: Node2<Operation>.() -> Unit = {}): Node2<Operation> = node2(Div() as Operation, init)

fun Node2<Operation>.div(init: Node2<Operation>.() -> Unit = {}) = this.node2(Div() as Operation, init)