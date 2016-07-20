package me.ilich.graphworks.nodes

class PluseNode : TwoArgNode() {

    override fun onCalc(a: Double, b: Double): Double = a + b

    override fun onAsString(a: String, b: String) = "( $a + $b )"

}