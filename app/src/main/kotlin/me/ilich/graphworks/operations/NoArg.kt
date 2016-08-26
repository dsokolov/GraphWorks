package me.ilich.graphworks.operations

abstract class NoArg : Operation(0) {

    override fun onCalc(vararg arg: Double, paramSource: (String) -> Double): Double = onCalc(paramSource)

    protected abstract fun onCalc(paramSource: (String) -> Double): Double

    override fun onAsString(vararg arg: String): String = onAsString()

    protected abstract fun onAsString(): String

}