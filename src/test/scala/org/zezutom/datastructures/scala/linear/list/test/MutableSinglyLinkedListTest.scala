package org.zezutom.datastructures.scala.linear.list.test

import org.junit.Assert._
import org.junit._
import org.zezutom.datastructures.scala.linear.list.MutableSinglyLinkedList

/**
 * Created by tom on 21/09/2014.
 */
class MutableSinglyLinkedListTest {

  var list:MutableSinglyLinkedList[Int] = null

  @Before def init {
    list = new MutableSinglyLinkedList[Int]();
  }

  @Test def emptyOnCreate {
    assertTrue(list.isEmpty)
    assertEquals(0, list.length)
  }

  @Test def addOne {
    list += 5
    assertFalse(list.isEmpty)
    assertEquals(1, list.length)
  }

  @Test def addSeveral {
    1 +=: 3 +=: list += 5 += 7
    assertEquals(4, list.length)
    assertEquals(1, list(0))
    assertEquals(3, list(1))
    assertEquals(5, list(2))
    assertEquals(7, list(3))
  }

  @Test def addAndRemove {
    list += 5 += 7 += 9
    assertEquals(3, list.length)
    assertEquals(5, list.remove(0))
    assertEquals(9, list.remove(1))
    assertEquals(7, list.remove(0))
    assertTrue(list.isEmpty)
    assertEquals(0, list.length)
  }

  @Test def clear {
    1 +=: 2 +=: list += 3 += 4
    assertEquals(4, list.length)
    list.clear()
    assertTrue(list.isEmpty)
    assertEquals(0, list.length)
  }

  @Test def update {
    1 +=: 2 +=: list += 3 += 4
    list(0) = 99
    assertEquals(99, list(0))
    assertEquals(99, list.head)

    list(2) = 98
    assertEquals(98, list(2))
  }

  @Test def iterator {
    1 +=: 8 +=: list += 5 += 7
    val iter = list.iterator
    assertTrue(iter.hasNext)
    assertEquals(1, iter.next)
    assertTrue(iter.hasNext)
    assertEquals(8, iter.next)
    assertTrue(iter.hasNext)
    assertEquals(5, iter.next)
    assertTrue(iter.hasNext)
    assertEquals(7, iter.next)
    assertFalse(iter.hasNext)
  }

  @Test def insertAll {
    list += 1
    list.insertAll(1, List(8, 5, 7))
    val iter = list.iterator
    assertTrue(iter.hasNext)
    assertEquals(1, iter.next)
    assertTrue(iter.hasNext)
    assertEquals(8, iter.next)
    assertTrue(iter.hasNext)
    assertEquals(5, iter.next)
    assertTrue(iter.hasNext)
    assertEquals(7, iter.next)
    assertFalse(iter.hasNext)
  }

  @Test def insertAllAsHead {
    list.insertAll(0, List(1, 8, 5, 7))
    val iter = list.iterator
    assertTrue(iter.hasNext)
    assertEquals(1, iter.next)
    assertTrue(iter.hasNext)
    assertEquals(8, iter.next)
    assertTrue(iter.hasNext)
    assertEquals(5, iter.next)
    assertTrue(iter.hasNext)
    assertEquals(7, iter.next)
    assertFalse(iter.hasNext)
  }

  // TODO test all boundaries / extremes
}
