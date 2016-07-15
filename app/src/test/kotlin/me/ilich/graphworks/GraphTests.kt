package me.ilich.graphworks

import me.ilich.graphworks.nodes.ConstNode
import me.ilich.graphworks.nodes.SumNode
import org.junit.Assert.assertEquals
import org.junit.Test

class GraphTests {

    @Test
    fun calcA() {
        val g = Graph(3)
        g.root(SumNode())
        g.add(ConstNode(4.0), 1, 0)
        g.add(ConstNode(5.0), 2, 0)
        assertEquals(g.calc(), 9.0, 0.1)
    }

    @Test
    fun calcB() {
        val g = Graph(3)
        g.root(ConstNode(10.0))
        assertEquals(g.calc(), 10.0, 0.1)
    }

    @Test
    fun calcC() {
        val g = Graph(5)
        g.root(SumNode())
        g.add(SumNode(), 1, 0)
        g.add(ConstNode(1.0), 2, 0)
        g.add(ConstNode(2.0), 3, 1)
        g.add(ConstNode(3.0), 4, 1)
        assertEquals(g.calc(), 6.0, 0.1)
    }

    @Test
    fun outgoingLinks(){
        val g = Graph(5)
        g.root(SumNode())
        g.add(SumNode(), 1, 0)
        g.add(ConstNode(1.0), 2, 0)
        g.add(ConstNode(2.0), 3, 1)
        g.add(ConstNode(3.0), 4, 1)
        assertEquals(g.outgoingLinksRecursive(0), 4)
        assertEquals(g.outgoingLinksRecursive(1), 2)
        assertEquals(g.outgoingLinksRecursive(2), 0)
        assertEquals(g.outgoingLinksRecursive(3), 0)
        assertEquals(g.outgoingLinksRecursive(4), 0)
    }

}
