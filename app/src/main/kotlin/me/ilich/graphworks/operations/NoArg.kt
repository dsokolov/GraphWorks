package me.ilich.graphworks.operations

abstract class NoArg : Operation(minArgCount = 0, maxArgCount = 0) {

    override fun onCalc(vararg arg: Double, paramSource: ((String) -> (Double))?): Double = onCalc(paramSource)

    protected abstract fun onCalc(paramSource: ((String) -> (Double))?): Double

    override fun onAsString(vararg arg: String): String = onAsString()

    override fun onToConst(vararg arg: Operation): Const? = onToConst()

    protected abstract fun onAsString(): String

    protected abstract fun onToConst(): Const?

}