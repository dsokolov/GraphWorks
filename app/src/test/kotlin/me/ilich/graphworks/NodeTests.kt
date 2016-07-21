package me.ilich.graphworks

import me.ilich.graphworks.nodes.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class NodeTests {

    @Test
    fun constNode() {
        val node = ConstNode(10.0)
        assertEquals(10.0, node.calc(), 0.1)
        assertEquals(node.asString(), "10.0")
        assertEquals(ConstNode(10.0), node)
        assertNotEquals(ConstNode(0.0), node)
        assertEquals("Node 10.0", node.toString())
    }


    @Test
    fun paramNode() {
        val source = object : ParamSource {
            override fun onParams(name: String): Double {
                if (name == "x") {
                    return 5.0
                } else {
                    return 0.0
                }
            }
        }
        val node = ParamNode("x")
        assertEquals(5.0, node.calc(paramSource = source), 0.1)
        assertEquals(node.asString(), "x")
        assertEquals(ParamNode("x"), node)
        assertEquals("Node param x", node.toString())
    }

    @Test
    fun absNode() {
        val node = AbsNode()
        assertEquals(10.0, node.calc(-10.0), 0.1)
        assertEquals(10.0, node.calc(10.0), 0.1)
        assertEquals(0.0, node.calc(0.0), 0.1)
        assertEquals(node.asString("10.0"), "| 10.0 |")
        assertEquals(AbsNode(), node)
        assertEquals("Node abs()", node.toString())
    }

    @Test
    fun addNode() {
        val node = AddNode()
        assertEquals(30.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 + 20.0 )")
        assertEquals(AddNode(), node)
        assertEquals("Node +", node.toString())
    }

    @Test
    fun subNode() {
        val node = SubNode()
        assertEquals(-10.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 - 20.0 )")
        assertEquals(SubNode(), node)
        assertEquals("Node -", node.toString())
    }

    @Test
    fun multNode() {
        val node = MultNode()
        assertEquals(200.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 * 20.0 )")
        assertEquals(MultNode(), node)
        assertEquals("Node *", node.toString())
    }

    @Test
    fun divNode() {
        val node = DivNode()
        assertEquals(5.0, node.calc(20.0, 4.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 / 20.0 )")
        assertEquals(DivNode(), node)
        assertEquals("Node /", node.toString())
    }

}
