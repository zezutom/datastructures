package org.zezutom.datastructures.scala.linear.list

import scala.collection.mutable

/**
 * Created by tom on 21/09/2014.
 */
class MutableSinglyLinkedList[A] extends mutable.Buffer[A] {

  private class Node(var data:A, var next:Node)

  // Cannot use the terms 'head' and 'tail' since those are already defined in the Buffer class
  private var hd:Node = null

  private var tl:Node = null

  private var count = 0

  /**
   * Appends an node at the end of the list
   * @param data
   * @return an updated list
   */
  def +=(data: A) = {
    val node = new Node(data, null)
    if (tl == null) {
      // The list is empty, therefore both head and tail point to the new node
      tl = node
      hd = tl
    } else {
      // Add a new node to the tail and move the tail pointer to the new node
      tl.next = node
      tl = tl.next
    }
    increment
    this
  }

  /**
   * Inserts an node at the beginning of the list (prepend)
   * @param data
   * @return an updated list
   */
  def +=:(data: A) = {
    // Point the head to the new node which in turn points
    // to the node previously referred by head
    hd = new Node(data, hd);
    if (tl == null) tl = hd
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
    walk(n, hd).data
  }

  /**
   * Clears the list
   */
  def clear() {
    hd = null
    tl = null
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
    if (n == 0) {
      if (elems.nonEmpty) {
        // Make the head point to the beginning of the inserted list
        hd = new Node(elems.head, hd)
        increment
        var walker = hd
        // Insert all of the provided nodes, but the head
        for (e <- elems.tail) {
          walker.next = new Node(e, walker.next)
          walker = walker.next
          increment
        }
        // walker.next is actually guaranteed to be null in this case
        if(walker.next == null) tl = walker
      }
    } else {
      // Get to the node preceding the one the insertion starts at
      var walker = walk(n - 1, hd)
      // Insert all of the provided nodes
      for (e <- elems) {
        walker.next = new Node(e, walker.next)
        walker = walker.next
        increment
      }
      // What if we are at the end of the list?
      if(walker.next == null) tl = walker
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
    def hasNext:Boolean = walker != null

    var walker = hd
  }

  def length: Int = count

  /**
   * Removes a node at a specified position and returns its value
   * @param n a position in the list
   * @return  a value of the removed node
   */
  def remove(n: Int): A = {
    if (n < 0 || n >= count) outOfBounds(n)
    if (n == 0) {
      // Remove the head of the list
      val data = hd.data
      hd = hd.next
      // What if there was a single node on the list only?
      // Just make sure tail is cleaned up too
      if (hd == null) tl = null
      decrement
      data
    } else {
      // Find the node preceding the one we want to remove
      val walker = walk(n - 1, hd)
      // Capture the value of the node which is going to be removed
      val data = walker.next.data
      // 'Remove' the node by changing the pointer to its follower
      // The node is now subject to GC, since nothing is pointing to it
      walker.next = walker.next.next
      // Make sure tail is adjusted properly in case a node at the very end was removed
      if (walker.next == null) tl = walker
      decrement
      // Return the data of the removed node
      data
    }
  }

  /**
   * Picks a node at a given position and replaces its value with a new one
   * @param n    a position in the list
   * @param data a new value the found node is to be updated with
   */
  def update(n: Int, data: A) {
    if (n < 0 || n >= count) outOfBounds(n)
    val walker = walk(n, hd)
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
