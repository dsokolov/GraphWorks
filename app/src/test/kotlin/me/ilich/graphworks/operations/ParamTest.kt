package me.ilich.graphworks.operations

import me.ilich.graphworks.Node
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ParamTest {

    val source: (String) -> (Double) = {
        name ->
        when (name) {
            "x" -> 1.0
            else -> 0.0
        }
    }
    val operation = Param("x")

    @Test fun calc() {
        assertEquals(1.0, operation.calc(paramSource = source), 0.1)
        assertIndexOutOfBoundsException { operation.calc(1.0) }
        assertIndexOutOfBoundsException { operation.calc(1.0, 2.0) }
        assertIndexOutOfBoundsException { operation.calc(1.0, 2.0, 3.0) }
    }

    @Test fun asString() {
        assertEquals(operation.asString(), "x")
        assertIndexOutOfBoundsException { operation.asString("1.0") }
        assertIndexOutOfBoundsException { operation.asString("1.0", "2.0") }
        assertIndexOutOfBoundsException { operation.asString("1.0", "2.0", "3.0") }
    }

    @Test fun toConst() {
        assertNull(operation.toConst())
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0)) }
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0), Const(2.0)) }
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0), Const(2.0), Const(3.0)) }
    }

    @Test fun equalsTest() {
        assertEquals(Param("x"), operation)
        assertEquals(param("x") { }, Node<Operation>(operation))
    }

    @Test fun toStringTest() {
        assertEquals("Operation param x", operation.toString())
    }

}