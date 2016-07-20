package me.ilich.graphworks.nodes

/**
 * Created by disokolov on 13.07.16.
 */
abstract class OneArgNode : Node(1) {

    override fun onCalc(vararg arg: Double): Double = onCalc(arg[0])

    protected abstract fun onCalc(a: Double): Double

    override fun onAsString(vararg arg: String): String = onAsString(arg[0])

    protected abstract fun onAsString(a: String): String

}