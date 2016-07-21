package me.ilich.graphworks

import me.ilich.graphworks.nodes.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GraphTests {

    @Test
    fun calcA() {
        val g = Graph(
                Graph.Pos(AddNode(), 0),
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
                Graph.Pos(AddNode(), 0),
                Graph.Pos(AddNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        assertEquals(g.calc(), 6.0, 0.1)
        assertEquals("( ( 2.0 + 3.0 ) + 1.0 )", g.asString())
    }

    @Test
    fun calcD() {
        val source = object : ParamSource {
            override fun onParams(name: String): Double {
                if (name == "x") {
                    return 2.0
                } else if (name == "y") {
                    return 3.0
                } else {
                    return 0.0
                }
            }
        }

        val g = Graph(
                Graph.Pos(AddNode(), 0),
                Graph.Pos(MultNode(), 1, 0),
                Graph.Pos(ParamNode("x"), 2, 1),
                Graph.Pos(ParamNode("y"), 3, 1),
                Graph.Pos(ConstNode(4.0), 4, 0)
        )
        assertEquals(10.0, g.calc(paramSource = source), 0.1)
        assertEquals("( ( x * y ) + 4.0 )", g.asString())
    }

    @Test
    fun outgoingLinks() {
        val g = Graph(
                Graph.Pos(AddNode(), 0),
                Graph.Pos(AddNode(), 1, 0),
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
                Graph.Pos(AddNode(), 0),
                Graph.Pos(AddNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        val sub0 = Graph(
                Graph.Pos(AddNode(), 0),
                Graph.Pos(AddNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        val sub1 = Graph(
                Graph.Pos(AddNode(), 0),
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

        assertEquals(sub0, big)
        assertEquals(sub2, big.subGraph(2))
        assertEquals(sub3, big.subGraph(3))
        assertEquals(sub4, big.subGraph(4))
        assertEquals(sub0, big.subGraph(0))
        assertEquals(sub1, big.subGraph(1))
    }

    @Test
    fun subGraph2() {
        val big = Graph(
                Graph.Pos(AddNode(), 0),
                Graph.Pos(AddNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        val sub0 = Graph(
                Graph.Pos(AddNode(), 0),
                Graph.Pos(AddNode(), 1, 0),
                Graph.Pos(ConstNode(1.0), 2, 0),
                Graph.Pos(ConstNode(2.0), 3, 1),
                Graph.Pos(ConstNode(3.0), 4, 1)
        )
        val sub1 = Graph(
                Graph.Pos(AddNode(), 0),
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

        assertEquals(sub0, big)
        assertEquals(sub2, big.subGraph(2))
        assertEquals(sub3, big.subGraph(3))
        assertEquals(sub4, big.subGraph(4))
        assertEquals(sub0, big.subGraph(0))
        assertEquals(sub1, big.subGraph(1))
    }

}
