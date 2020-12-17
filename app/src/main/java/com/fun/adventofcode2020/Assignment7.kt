package com.`fun`.adventofcode2020

import kotlin.collections.HashMap

class Assignment7 {

    fun count() {
        val inputs =
            ReadFileUtils.readFile("src/input7.txt").replace("bags", "bag").split("\n")

        val tree = HashMap<String, Node<String>>()

        for (input2 in inputs) {
            val input = input2.replace(".", "")
            val parts = input.split(Regex("contain|,"))

            val outerBag = parts[0].trim()
            if (tree[outerBag] == null) {
                tree[outerBag] = Node(outerBag)
            }
            for (i in 1 until parts.size) {
                var innerBag = parts[i]
                if (innerBag.contains("no other")) continue

                val count = Integer.parseInt(parts[i].substring(0, 3).replace(" ", ""))
                innerBag = parts[i].substring(3)
                var node = tree[innerBag]
                if (node == null) {
                    node = Node(innerBag)
                    tree[innerBag] = node
                    tree[outerBag]?.addChild(node, count)
                } else {
                    tree[outerBag]?.addChild(node, count)
                }
            }
        }

        tree["shiny gold bag"]?.let {
            val countParents = getParentCount(it, mutableSetOf())
            println("countParents = $countParents")
            val countChildren = getNestedBagsCount(it)
            println("countChildren = $countChildren")
        }
    }

    private fun getParentCount(node: Node<String>, counted: MutableSet<String>): Int {
        return if (node.parents.isEmpty()) {
            0
        } else node.parents.sumBy { getUniqueParent(it, counted) }
    }

    private fun getUniqueParent(node: Node<String>, counted: MutableSet<String>): Int {
        val count = if(!counted.contains(node.value)) 1 else 0
        counted.add(node.value)
        return count + getParentCount(node, counted)
    }

    private fun getNestedBagsCount(node: Node<String>): Int {
        return if (node.children.isEmpty()) {
            0
        } else node.children.sumBy { node.number[it.value]!! + node.number[it.value]!! * getNestedBagsCount(it) }
    }
}

class Node<T>(value: T) {

    var value: T = value
    var number = mutableMapOf<T, Int>()
    var parents: MutableList<Node<T>> = mutableListOf()

    var children: MutableList<Node<T>> = mutableListOf()

    fun addChild(child: Node<T>, bagsNumber: Int) {
        children.add(child)
        child.parents.add(this)

        number[child.value] = bagsNumber
    }
}