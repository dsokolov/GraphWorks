package me.ilich.graphworks.operations

abstract class TwoArg : Operation(2) {

    override fun onCalc(vararg arg: Double, paramSource: (String) -> Double): Double = onCalc(arg[0], arg[1], paramSource)

    protected abstract fun onCalc(a: Double, b: Double, paramSource: (String) -> Double): Double

    override fun onAsString(vararg arg: String): String = onAsString(arg[0], arg[1])

    protected abstract fun onAsString(a: String, b: String): String

}