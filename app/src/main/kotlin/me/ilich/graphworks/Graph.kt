package me.ilich.graphworks

import me.ilich.graphworks.nodes.Node

/**
 * Created by disokolov on 13.07.16.
 */
class Graph(val nodesCount: Int) {

    private val nodes = kotlin.arrayOfNulls<Node?>(nodesCount)
    private val matrix = Matrix<Int>(nodesCount)

    fun root(node: Node) {
        matrix.clear()
        for (i in 0..nodes.size - 1) {
            nodes[i] = null;
        }
        nodes[0] = node;
    }

    fun add(node: Node, pos: Int, parent: Int) {
        nodes[pos] = node;
        matrix[parent, pos] = 1
        matrix[pos, parent] = -1;
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
        return node!!.calc(*args.toDoubleArray());
    }

    fun subGraph(fromIndex: Int): Graph {
        val size = outgoingLinksRecursive(fromIndex)
        val g = Graph(size)
        val rootNode = nodes[fromIndex]
        g.root(rootNode!!)
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

}