package me.ilich.graphworks.operations

import me.ilich.graphworks.Node
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AbsTest {

    val operation = Abs()

    @Test fun calc() {
        assertIndexOutOfBoundsException { operation.calc() }
        assertEquals(1.0, operation.calc(-1.0), 0.1)
        assertEquals(0.0, operation.calc(-0.0), 0.1)
        assertEquals(1.0, operation.calc(1.0), 0.1)
        assertIndexOutOfBoundsException { operation.calc(1.0, 2.0) }
        assertIndexOutOfBoundsException { operation.calc(1.0, 2.0, 3.0) }
    }

    @Test fun asString() {
        assertIndexOutOfBoundsException { operation.asString() }
        assertEquals(operation.asString("1.0"), "| 1.0 |")
        assertIndexOutOfBoundsException { operation.asString("1.0", "2.0") }
        assertIndexOutOfBoundsException { operation.asString("1.0", "2.0", "3.0") }
    }

    @Test fun toConst() {
        assertIndexOutOfBoundsException { operation.toConst() }
        assertEquals(operation.toConst(Const(1.0)), Const(1.0))
        assertNull(operation.toConst(Param("x")))
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0), Const(2.0)) }
        assertIndexOutOfBoundsException { operation.toConst(Const(1.0), Const(2.0), Const(3.0)) }
    }

    @Test fun equalsTest() {
        assertEquals(Abs(), operation)
        assertEquals(abs { }, Node<Operation>(operation))
    }

    @Test fun toStringTest() {
        assertEquals("Operation abs()", operation.toString())
    }

}