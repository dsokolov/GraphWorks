package me.ilich.graphworks.nodes

/**
 * Created by disokolov on 13.07.16.
 */
abstract class Node {

    fun calc(vararg arg: Double): Double = onCalc(*arg)

    protected abstract fun onCalc(vararg arg: Double): Double

}