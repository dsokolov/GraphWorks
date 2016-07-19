package me.ilich.graphworks.nodes

class SumNode : TwoArgNode() {

    override fun onCalc(a: Double, b: Double): Double = a + b

    override fun onAsString(a: Double, b: Double) = "$a + $b"

}