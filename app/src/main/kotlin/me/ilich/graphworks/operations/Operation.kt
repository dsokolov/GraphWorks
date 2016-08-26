package me.ilich.graphworks.operations

import me.ilich.graphworks.Node2

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

fun <T> node2(v: T, init: Node2<T>.() -> Unit = {}): Node2<T> {
    val result = Node2(v)
    result.init()
    return result
}

fun <T> Node2<T>.node2(v: T, init: Node2<T>.() -> Unit = {}) {
    val result = Node2<T>(v)
    result.init()
    this.children.add(result)
}