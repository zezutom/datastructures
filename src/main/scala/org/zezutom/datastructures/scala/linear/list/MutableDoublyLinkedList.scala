package org.zezutom.datastructures.scala.linear.list

import scala.collection.mutable

/**
 * Created by tom on 28/09/2014.
 */
class MutableDoublyLinkedList[A : Manifest] extends mutable.Buffer[A] {

  private class Node(var data:A, var prev:Node, var next:Node)

  // Makes the list circular, i.e. 'end' is predecessor of the head and successor to the tail
  // A slight challenge here is that we don't really know what the default value should be,
  // so we solve it by passing an array of size one and we pull off the first element as the default value
  private val end = new Node((new Array[A](1))(0), null, null)

  // Let's ensure the list is circular
  end.prev = end;
  end.next = end;

  private var count = 0

  /**
   * Appends an node at the end of the list
   * @param data
   * @return an updated list
   */
  def +=(data: A) = {
    val node = new Node(data, end.prev, end)

    // update the appropriate pointers
    end.prev.next = node
    end.prev = node
    increment
    this
  }

  /**
   * Inserts an node at the beginning of the list (prepend)
   * @param data
   * @return an updated list
   */
  def +=:(data: A) = {
    val node = new Node(data, end, end.next)

    // update the appropriate pointers
    end.next.prev = node
    end.next = node
    increment
    this
  }

  /**
   * Returns a value of a node defined by a specific position in the list
   * @param n
   * @return the found value
   */
  def apply(n: Int): A = {
    if (n < 0 || n >= count) outOfBounds(n)
    walk(n, end.next).data
  }

  /**
   * Clears the list
   */
  def clear() {
    end.prev = end
    end.next = end
    count = 0
  }

  /**
   * Inserts the given collection to the list
   * @param n     a position in the list the collection should be inserted at
   * @param elems a collection of inserted elements
   */
  def insertAll(n: Int, elems: collection.Traversable[A]) {
    // Note that it is fine to insert at the position of the very last node
    if (n < 0 || n > count) outOfBounds(n)
    // What if we are inserting at the beginning of the list?

    // Get to the node preceding the one the insertion starts at
    var walker = walk(n, end.next)
    // Insert all of the provided nodes
    for (e <- elems) {
      val node = new Node(e, walker.next, walker)
      walker.prev.next = node
      walker.prev = node
      increment
    }
  }

  /**
   * Provides an efficient way of iterating over a list. Don't use apply(n), since that one has an internal loop.
   * Whereas an iterator maintains a pointer to the current position in the list.
   * @return a list iterator
   */
  def iterator = new Iterator[A] {
    def next:A = {
      val data = walker.data
      walker = walker.next
      data
    }
    def hasNext:Boolean = walker != end

    var walker = end.next
  }

  def length: Int = count

  /**
   * Removes a node at a specified position and returns its value
   * @param n a position in the list
   * @return  a value of the removed node
   */
  def remove(n: Int): A = {
    if (n < 0 || n >= count) outOfBounds(n)

    // Pick the node which is to be removed
    val walker = walk(n, end.next)
    // Capture the value of the node which is going to be removed
    val data = walker.data
    // 'Remove' the node by changing the pointer to its follower
    // The node is now subject to GC, since nothing is pointing to it
    walker.next.prev = walker.prev
    walker.prev.next = walker.next
    decrement
    // Return the data of the removed node
    data
  }

  /**
   * Picks a node at a given position and replaces its value with a new one
   * @param n    a position in the list
   * @param data a new value the found node is to be updated with
   */
  def update(n: Int, data: A) {
    if (n < 0 || n >= count) outOfBounds(n)
    val walker = walk(n, end.next)
    walker.data = data
  }

  private def increment {
    count += 1
  }

  private def decrement {
    count -= 1
  }

  private def walk(n: Int, node: Node) = {
    var walker = node;
    for (i <- 0 until n) walker = walker.next
    walker
  }
  private def outOfBounds(n:Int) {
    throw new IndexOutOfBoundsException("Requested " + n + " of " + count)
  }
}
