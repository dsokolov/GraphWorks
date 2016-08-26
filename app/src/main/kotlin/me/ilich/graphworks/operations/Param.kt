package me.ilich.graphworks.operations

import me.ilich.graphworks.Node2

class Param(val name: String) : NoArg() {

    override fun onCalc(paramSource: (String) -> Double): Double = paramSource.invoke(name)

    override fun onAsString(): String = "$name"

    override fun toString(): String {
        return "Operation param $name"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        if (!super.equals(other)) return false

        other as Param

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

}

fun param(name: String, init: Node2<Operation>.() -> Unit = {}): Node2<Operation> = node2(Param(name) as Operation, init)

fun Node2<Operation>.param(name: String, init: Node2<Operation>.() -> Unit = {}) = this.node2(Param(name) as Operation, init)