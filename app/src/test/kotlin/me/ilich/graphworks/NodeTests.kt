package me.ilich.graphworks

import me.ilich.graphworks.operations.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class NodeTests {

    @Test
    fun constNode() {
        val node = Const(10.0)
        assertEquals(10.0, node.calc(), 0.1)
        assertEquals(node.asString(), "10.0")
        assertEquals(Const(10.0), node)
        assertNotEquals(Const(0.0), node)
        assertEquals("Operation 10.0", node.toString())
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
        val node = Param("x")
        assertEquals(5.0, node.calc(paramSource = source), 0.1)
        assertEquals(node.asString(), "x")
        assertEquals(Param("x"), node)
        assertEquals("Operation param x", node.toString())
    }

    @Test
    fun absNode() {
        val node = Abs()
        assertEquals(10.0, node.calc(-10.0), 0.1)
        assertEquals(10.0, node.calc(10.0), 0.1)
        assertEquals(0.0, node.calc(0.0), 0.1)
        assertEquals(node.asString("10.0"), "| 10.0 |")
        assertEquals(Abs(), node)
        assertEquals("Operation abs()", node.toString())
    }

    @Test
    fun addNode() {
        val node = Add()
        assertEquals(30.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 + 20.0 )")
        assertEquals(Add(), node)
        assertEquals("Operation +", node.toString())
    }

    @Test
    fun subNode() {
        val node = Sub()
        assertEquals(-10.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 - 20.0 )")
        assertEquals(Sub(), node)
        assertEquals("Operation -", node.toString())
    }

    @Test
    fun multNode() {
        val node = Mult()
        assertEquals(200.0, node.calc(10.0, 20.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 * 20.0 )")
        assertEquals(Mult(), node)
        assertEquals("Operation *", node.toString())
    }

    @Test
    fun divNode() {
        val node = Div()
        assertEquals(5.0, node.calc(20.0, 4.0), 0.1)
        assertEquals(node.asString("10.0", "20.0"), "( 10.0 / 20.0 )")
        assertEquals(Div(), node)
        assertEquals("Operation /", node.toString())
    }

}
