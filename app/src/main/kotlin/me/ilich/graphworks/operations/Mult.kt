package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

class Mult : TwoAndMoreArg() {

    override fun onCalc(vararg arg: Double, paramSource: ((String) -> (Double))?): Double {
        var m: Double = 1.0
        for (element in arg) {
            m = m * element
        }
        return m
    }

    override fun onAsString(vararg arg: String) = arg.joinToString(separator = " * ", prefix = "( ", postfix = " )")

    override fun onToConst(vararg arg: Operation): Const {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun toString() = "Operation *"

    override fun copy(): Mult = Mult()

}

fun mult(init: Node<Operation>.() -> Unit = {}): Node<Operation> = node(Mult() as Operation, init)

fun Node<Operation>.mult(init: Node<Operation>.() -> Unit = {}) = this.node(Mult() as Operation, init)