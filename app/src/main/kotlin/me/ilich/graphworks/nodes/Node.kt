package me.ilich.graphworks.nodes

abstract class Node(val argCount: Int) {

    fun calc(vararg arg: Double): Double {
        assert(arg.size == argCount)
        return onCalc(*arg)
    }

    protected abstract fun onCalc(vararg arg: Double): Double

    fun asString(vararg arg: String): String {
        assert(arg.size == argCount)
        return onAsString(*arg)
    }

    protected abstract fun onAsString(vararg arg: String): String

}