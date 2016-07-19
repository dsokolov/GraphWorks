package me.ilich.graphworks

import me.ilich.graphworks.nodes.ConstNode
import me.ilich.graphworks.nodes.SumNode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GraphTests {

    @Test
    fun calcA() {
        val g = Graph(
                Graph.Pos(SumNode(), 0),
                Graph.Pos(ConstNode(4.0), 1, 0),
                Graph.Pos(ConstNode(5.0), 2, 0)
        )
        assertEquals(g.calc(), 9.0, 0.1)
    }

    @Test
    fun calcB() {
        val g = Graph(
                Graph.Pos(ConstNode(10.0), 1)
        )
        assertEquals(g.calc(), 10.0, 0.1)
    }

    @Test
    fun calcC() {
        val g = Graph(
                Graph.Pos(SumNode(), 0),
                Graph.Pos(SumNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        assertEquals(g.calc(), 6.0, 0.1)
    }

    @Test
    fun outgoingLinks() {
        val g = Graph(
                Graph.Pos(SumNode(), 0),
                Graph.Pos(SumNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        assertEquals(g.outgoingLinksRecursive(0), 4)
        assertEquals(g.outgoingLinksRecursive(1), 2)
        assertEquals(g.outgoingLinksRecursive(2), 0)
        assertEquals(g.outgoingLinksRecursive(3), 0)
        assertEquals(g.outgoingLinksRecursive(4), 0)
    }

    @Test
    fun equalsTest() {
        assertNotEquals(null, Graph())
        assertEquals(Graph(), Graph())

        assertEquals(Graph(
                Graph.Pos(ConstNode(10.0), 0)
        ), Graph(
                Graph.Pos(ConstNode(10.0), 0)
        ))
        assertNotEquals(Graph(
                Graph.Pos(ConstNode(5.0), 0)
        ), Graph(
                Graph.Pos(ConstNode(10.0), 0)
        ))

        assertNotEquals(Graph(
                Graph.Pos(ConstNode(10.0), 0)
        ), Graph(
                Graph.Pos(ConstNode(10.0), 0),
                Graph.Pos(ConstNode(10.0), 1, 0)
        ))
    }

}
