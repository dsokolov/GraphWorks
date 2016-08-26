package me.ilich.graphworks.operations

import me.ilich.graphworks.Node2

class Sub : TwoArg() {

    override fun onCalc(a: Double, b: Double, paramSource: (String) -> Double): Double = a - b

    override fun onAsString(a: String, b: String) = "( $a - $b )"

    override fun toString(): String{
        return "Operation -"
    }

}

fun sub(init: Node2<Operation>.() -> Unit = {}): Node2<Operation> = node2(Sub() as Operation, init)

fun Node2<Operation>.sub(init: Node2<Operation>.() -> Unit = {}) = this.node2(Sub() as Operation, init)