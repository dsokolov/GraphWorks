package me.ilich.graphworks.operations

import me.ilich.graphworks.Node

abstract class Operation(val argCount: Int) {

    fun calc(vararg arg: Double, paramSource: (String) -> Double = { 0.0 }): Double {
        assert(arg.size == argCount)
        return onCalc(*arg, paramSource = paramSource)
    }

    protected abstract fun onCalc(vararg arg: Double, paramSource: (String) -> Double): Double

    fun asString(vararg arg: String): String {
        assert(arg.size == argCount)
        return onAsString(*arg)
    }

    protected abstract fun onAsString(vararg arg: String): String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Operation

        if (argCount != other.argCount) return false

        return true
    }

    override fun hashCode(): Int {
        return argCount
    }

}

fun <T> node(v: T, init: Node<T>.() -> Unit = {}): Node<T> {
    val result = Node(v)
    result.init()
    return result
}

fun <T> Node<T>.node(v: T, init: Node<T>.() -> Unit = {}) {
    val result = Node<T>(v)
    result.init()
    this.children.add(result)
}