package me.ilich.graphworks

import me.ilich.graphworks.nodes.Node

/**
 * Created by disokolov on 13.07.16.
 */
class Graph(vararg pos: Pos) {

    class Pos(val node: Node, val pos: Int, val parent: Int? = null)

    private val nodesCount = pos.size
    private val nodes: List<Node>;
    private val matrix = Matrix<Int>(nodesCount)

    init {
        val l: MutableList<Node> = mutableListOf()
        pos.forEach {
            l.add(it.node)
            if (it.parent != null) {
                matrix[it.parent, it.pos] = 1
                matrix[it.pos, it.parent] = -1
            }
        }
        nodes = l.toList()
    }

    fun calc(): Double = calcFromIndex(0)

    private fun calcFromIndex(fromIndex: Int): Double {
        val node = nodes[fromIndex];
        val args = mutableListOf<Double>()
        for (toIndex in 0..nodesCount - 1) {
            val link = matrix[fromIndex, toIndex]
            if (link == 1) {
                val v = calcFromIndex(toIndex)
                args.add(v)
            }
        }
        return node.calc(*args.toDoubleArray());
    }

    fun asString(): String {
        return stringFromIndex(0)
    }

    private fun stringFromIndex(fromIndex: Int): String {
        val node = nodes[fromIndex];
        val args = mutableListOf<String>()
        for (toIndex in 0..nodesCount - 1) {
            val link = matrix[fromIndex, toIndex]
            if (link == 1) {
                val v = stringFromIndex(toIndex)
                args.add(v)
            }
        }
        return node.asString(*args.toTypedArray());
    }

    fun subGraph(fromIndex: Int): Graph {
        val size = outgoingLinksRecursive(fromIndex)
        val g = Graph()
        return g
    }

    fun outgoingLinksRecursive(fromIndex: Int): Int {
        var size = 0
        for (toIndex in 0..nodesCount - 1) {
            val link = matrix[fromIndex, toIndex]
            if (link == 1) {
                size++
                size += outgoingLinksRecursive(toIndex)
            }
        }
        return size
    }

    override fun equals(other: Any?): Boolean{
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Graph

        if (nodesCount != other.nodesCount) return false
        if (nodes != other.nodes) return false
        if (matrix != other.matrix) return false

        return true
    }

    override fun hashCode(): Int{
        var result = nodesCount
        result = 31 * result + nodes.hashCode()
        result = 31 * result + matrix.hashCode()
        return result
    }

}