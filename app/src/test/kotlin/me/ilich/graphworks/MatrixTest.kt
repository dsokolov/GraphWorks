package me.ilich.graphworks

import me.ilich.graphworks.operations.Const
import me.ilich.graphworks.operations.Operation
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by disokolov on 14.07.16.
 */
class MatrixTest {

    @Test
    fun emptyMatrix() {
        val m = Matrix<Operation>(0)
        try {
            m[0, 0]
            fail()
        } catch (e: ArrayIndexOutOfBoundsException) {
        } catch (e: Exception) {
            fail(e.message)
        }
    }

    @Test
    fun outOfBound() {
        val m = Matrix<Operation>(5)
        try {
            m[-1, 0]
            fail()
        } catch (e: ArrayIndexOutOfBoundsException) {
        } catch (e: Exception) {
            fail(e.message)
        }
        try {
            m[0, -1]
            fail()
        } catch (e: ArrayIndexOutOfBoundsException) {
        } catch (e: Exception) {
            fail(e.message)
        }
        try {
            m[5, 0]
            fail()
        } catch (e: ArrayIndexOutOfBoundsException) {
        } catch (e: Exception) {
            fail(e.message)
        }
        try {
            m[0, 5]
            fail()
        } catch (e: ArrayIndexOutOfBoundsException) {
        } catch (e: Exception) {
            fail(e.message)
        }
    }

    @Test
    fun matrix1x1() {
        val m = Matrix<Operation>(1)
        assertTrue(m[0, 0] == null)
        m[0, 0] = Const(1.0)
        assertTrue(m[0, 0] == Const(1.0))
    }

    @Test
    fun matrix2x2() {
        val m = Matrix<Operation>(2)
        assertTrue(m[0, 1] == null)
        m[0, 1] = Const(1.0)
        assertTrue(m[0, 1] == Const(1.0))
    }

    @Test
    fun clear() {
        val m = Matrix<Operation>(2)
        m[0, 1] = Const(1.0)
        m.clear()
        assertTrue(m[0, 1] == null)
    }

    @Test
    fun equalsTest() {
        assertNotEquals(null, Matrix<Operation>(0))
        assertNotEquals(null, Matrix<Operation>(1))
        assertNotEquals(null, Matrix<Operation>(10))

        assertEquals(Matrix<Operation>(0), Matrix<Operation>(0))
        assertEquals(Matrix<Operation>(1), Matrix<Operation>(1))
        assertEquals(Matrix<Operation>(10), Matrix<Operation>(10))

        assertNotEquals(Matrix<Operation>(0), Matrix<Operation>(1))
        assertNotEquals(Matrix<Operation>(0), Matrix<Operation>(10))
    }

    @Test
    fun toStr() {
        assertEquals("()", Matrix<Int>(0).toString())
        val m1 = Matrix<Int>(1)
        m1[0, 0] = 1
        assertEquals("( 1 )", m1.toString())
        val m2 = Matrix<Int>(2)
        m2[0, 0] = 0
        m2[0, 1] = 1
        m2[1, 0] = 2
        m2[1, 1] = 3
        assertEquals("( 0, 1 )\n( 2, 3 )", m2.toString())
    }

}
