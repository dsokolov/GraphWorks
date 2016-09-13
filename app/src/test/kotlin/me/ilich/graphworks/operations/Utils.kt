package me.ilich.graphworks.operations

import org.junit.Assert

fun assertIndexOutOfBoundsException(action: () -> (Unit)) {
    try {
        action()
    } catch (e: Exception) {
        Assert.assertTrue(e is IndexOutOfBoundsException)
    }
}