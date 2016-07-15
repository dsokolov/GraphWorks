package me.ilich.graphworks.nodes

/**
 * Created by disokolov on 13.07.16.
 */
abstract class TwoArgNode : Node() {

    override fun onCalc(vararg arg: Double): Double = onCalc(arg[0], arg[1])

    protected abstract fun onCalc(a: Double, b: Double): Double

}