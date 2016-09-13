package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Div : TwoArg() {

    override fun onCalc(a: Double, b: Double, paramSource: ((String) -> (Double))?): Double = a / b

    override fun onAsString(a: String, b: String) = "( $a / $b )"

    override fun onToConst(a: Operation, b: Operation): Const? {
        if (a is Const && b is Const) {
            val c = a.value / b.value
            return Const(c)
        } else {
            return null
        }
    }


    override fun toString() = "Operation /"

    override fun copy(): Div = Div()

}

fun div(init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Div() as Operation, init)

fun Node<Operation>.div(init: Node<Operation>.() -> Unit = {}) = this.node(Div() as Operation, init)