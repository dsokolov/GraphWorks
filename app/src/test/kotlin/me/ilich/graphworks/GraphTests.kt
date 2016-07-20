package me.ilich.graphworks

import me.ilich.graphworks.nodes.ConstNode
import me.ilich.graphworks.nodes.PluseNode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GraphTests {

    @Test
    fun calcA() {
        val g = Graph(
                Graph.Pos(PluseNode(), 0),
                Graph.Pos(ConstNode(4.0), 1, 0),
                Graph.Pos(ConstNode(5.0), 2, 0)
        )
        assertEquals(9.0, g.calc(), 0.1)
        assertEquals("( 4.0 + 5.0 )", g.asString())
    }

    @Test
    fun calcB() {
        val g = Graph(
                Graph.Pos(ConstNode(10.0), 1)
        )
        assertEquals(g.calc(), 10.0, 0.1)
        assertEquals("10.0", g.asString())
    }

    @Test
    fun calcC() {
        val g = Graph(
                Graph.Pos(PluseNode(), 0),
                Graph.Pos(PluseNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        assertEquals(g.calc(), 6.0, 0.1)
        assertEquals("( ( 2.0 + 3.0 ) + 1.0 )", g.asString())
    }

    @Test
    fun outgoingLinks() {
        val g = Graph(
                Graph.Pos(PluseNode(), 0),
                Graph.Pos(PluseNode(), 1, 0),
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

    @Test
    fun subGraph() {
        val big = Graph(
                Graph.Pos(PluseNode(), 0),
                Graph.Pos(PluseNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        val sub0 = Graph(
                Graph.Pos(PluseNode(), 0),
                Graph.Pos(PluseNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        val sub1 = Graph(
                Graph.Pos(PluseNode(), 0),
                Graph.Pos(ConstNode(2.0), 1, 1),
                Graph.Pos(ConstNode(3.0), 2, 1)
        )
        val sub2 = Graph(
                Graph.Pos(ConstNode(1.0), 0)
        )
        val sub3 = Graph(
                Graph.Pos(ConstNode(2.0), 0)
        )
        val sub4 = Graph(
                Graph.Pos(ConstNode(3.0), 0)
        )

        assertEquals(sub0, big.subGraph(0))
        assertEquals(sub1, big.subGraph(1))
        assertEquals(sub2, big.subGraph(2))
        assertEquals(sub3, big.subGraph(3))
        assertEquals(sub4, big.subGraph(4))
    }

}
