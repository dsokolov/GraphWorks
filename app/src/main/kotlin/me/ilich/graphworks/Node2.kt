package me.ilich.graphworks

class Node2<T>(val value: T) {

    val children = mutableListOf<Node2<T>>()

    val size: Int
        get() = children.sumBy { it.size } + 1

    override fun toString(): String = "Operation $value (${children.size})"

}