package me.ilich.graphworks.operations

abstract class TwoArg : Operation(minArgCount = 2, maxArgCount = 2) {

    override fun onCalc(vararg arg: Double, paramSource: ((String) -> (Double))?): Double {
        return onCalc(arg[0], arg[1], paramSource)
    }

    protected abstract fun onCalc(a: Double, b: Double, paramSource: ((String) -> (Double))?): Double

    override fun onAsString(vararg arg: String): String = onAsString(arg[0], arg[1])

    override fun onToConst(vararg arg: Operation): Const? = onToConst(arg[0], arg[1])

    protected abstract fun onAsString(a: String, b: String): String

    protected abstract fun onToConst(a: Operation, b: Operation): Const?

}