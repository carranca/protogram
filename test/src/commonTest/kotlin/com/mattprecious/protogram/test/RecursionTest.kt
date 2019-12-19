package com.mattprecious.protogram.test

import com.mattprecious.tinsel.Node
import com.mattprecious.tinsel.Node.InternalNode
import com.mattprecious.tinsel.Node.LeafNode
import kotlin.test.Test
import kotlin.test.assertEquals

class RecursionTest {
  @Test fun recursiveTreeBuilder() {
    var actual = buildRecursiveTree(label = "2", leafValue = "v", depth = 1)
    var expected: Node = LeafNode("2", "v")
    assertEquals(expected, actual)

    actual = buildRecursiveTree("2", "v", 2)
    expected = InternalNode("2", listOf(LeafNode("2", "v")))
    assertEquals(expected, actual)

    actual = buildRecursiveTree(label = "2", leafValue = "v", depth = 5)
    expected = InternalNode("2", listOf(InternalNode("2", listOf(InternalNode("2", listOf(InternalNode("2", listOf(LeafNode("2", "v")))))))))
    assertEquals(expected, actual)
  }
}
