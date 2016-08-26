package me.ilich.graphworks

class Node<T>(val value: T) {

    val children = mutableListOf<Node<T>>()

    val size: Int
        get() = children.sumBy { it.size } + 1

    override fun toString(): String = "Operation $value (${children.size})"

}