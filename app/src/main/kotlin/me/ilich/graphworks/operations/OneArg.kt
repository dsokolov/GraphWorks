package me.ilich.graphworks.operations

abstract class OneArg : Operation(1) {

    override fun onCalc(vararg arg: Double, paramSource: ParamSource?): Double = onCalc(arg[0], paramSource)

    protected abstract fun onCalc(a: Double, paramSource: ParamSource?): Double

    override fun onAsString(vararg arg: String): String = onAsString(arg[0])

    protected abstract fun onAsString(a: String): String

}