package me.ilich.graphworks.operations

abstract class OneArg : Operation(minArgCount = 1, maxArgCount = 1) {

    override fun onCalc(vararg arg: Double, paramSource: (String) -> Double): Double = onCalc(arg[0], paramSource)

    protected abstract fun onCalc(a: Double, paramSource: (String) -> Double): Double

    override fun onAsString(vararg arg: String): String = onAsString(arg[0])

    protected abstract fun onAsString(a: String): String

}