package me.ilich.graphworks

import me.ilich.graphworks.nodes.ConstNode
import me.ilich.graphworks.nodes.Node
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by disokolov on 14.07.16.
 */
class MatrixTest {

    @Test
    fun emptyMatrix() {
        val m = Matrix<Node>(0)
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
        val m = Matrix<Node>(5)
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
        val m = Matrix<Node>(1)
        assertTrue(m[0, 0] == null)
        m[0, 0] = ConstNode(1.0)
        assertTrue(m[0, 0] == ConstNode(1.0))
    }

    @Test
    fun matrix2x2() {
        val m = Matrix<Node>(2)
        assertTrue(m[0, 1] == null)
        m[0, 1] = ConstNode(1.0)
        assertTrue(m[0, 1] == ConstNode(1.0))
    }

    @Test
    fun clear(){
        val m = Matrix<Node>(2)
        m[0, 1] = ConstNode(1.0)
        m.clear()
        assertTrue(m[0, 1] == null)
    }

    @Test
    fun equalsTest(){
        assertNotEquals(null, Matrix<Node>(0))
        assertNotEquals(null, Matrix<Node>(1))
        assertNotEquals(null, Matrix<Node>(10))

        assertEquals(Matrix<Node>(0), Matrix<Node>(0))
        assertEquals(Matrix<Node>(1), Matrix<Node>(1))
        assertEquals(Matrix<Node>(10), Matrix<Node>(10))

        assertNotEquals(Matrix<Node>(0), Matrix<Node>(1))
        assertNotEquals(Matrix<Node>(0), Matrix<Node>(10))
    }

}
