package me.ilich.graphworks.nodes

class SumNode : TwoArgNode() {

    override fun onCalc(a: Double, b: Double): Double = a + b

}