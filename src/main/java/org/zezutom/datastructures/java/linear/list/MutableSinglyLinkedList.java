package org.zezutom.datastructures.java.linear.list;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by tom on 21/09/2014.
 */
public class MutableSinglyLinkedList<T extends Object> implements Iterable<T> {

    private Node<T> head;

    private Node<T> tail;

    private int count = 0;

    public int length() { return count; }

    public boolean isEmpty() {
        return count <= 0;
    }

    public Node<T> head() { return this.head; }

    public static class Node<T>  {

        Node<T> next;

        T data;

        private Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T data() { return this.data; }
    }

    /**
     * Appends an node at the end of the list
     * @param data
     * @return an updated list
     */
    public MutableSinglyLinkedList<T> append(T data) {
        Node<T> node = new Node<T>(data, null);
        if (tail == null) {
            // The list is empty, therefore both head and tail point to the new node
            tail = node;
            head = tail;
        } else {
            // Add a new node to the tail and move the tail pointer to the new node
            tail.next = node;
            tail = tail.next;
        }
        increment();
        return this;
    }

    /**
     * Inserts an node at the beginning of the list (prepend)
     * @param data
     * @return an updated list
     */
    public MutableSinglyLinkedList<T> prepend(T data) {
        // Point the head to the new node which in turn points
        // to the node previously referred by head
        head = new Node<T>(data, head);
        if (tail == null) tail = head;
        increment();
        return this;
    }

    /**
     * Returns a value of a node defined by a specific position in the list
     * @param n
     * @return the found value
     */
    public T apply(int n) {
        if (n < 0 || n >= count) outOfBounds(n);
        return walk(n, head).data;
    }

    /**
     * Clears the list
     */
    public void clear() {
        head = tail = null;
        count = 0;
    }

    /**
     * Inserts the given collection to the list
     * @param n     a position in the list the collection should be inserted at
     * @param elems a collection of inserted elements
     */
    public void insertAll(int n, Collection<T> elems) {
        // Note that it is fine to insert at the position of the very last node
        if (n < 0 || n > count) outOfBounds(n);
        // What if we are inserting at the beginning of the list?
        if (n == 0) {
            if (!elems.isEmpty()) {
                // Make the head point to the beginning of the inserted list
                final Iterator<T> iterator = elems.iterator();
                head = new Node<T>(iterator.next(), head);
                increment();
                Node<T> walker = head;
                // Insert all of the provided nodes, but the head
                while (iterator.hasNext()) {
                    walker.next = new Node<T>(iterator.next(), walker.next);
                    walker =  walker.next;
                    increment();
                }
                // walker.next is actually guaranteed to be null in this case
                if (walker.next == null) tail = walker;
            }
        } else {
            // Get to the node preceding the one the insertion starts at
            Node<T> walker = walk(n - 1, head);
            // Insert all of the provided nodes
            for (T e : elems) {
                walker.next = new Node<T>(e, walker.next);
                walker = walker.next;
                increment();
            }
            // What if we are at the end of the list?
            if(walker.next == null) tail = walker;
        }
    }

    /**
     * Provides an efficient way of iterating over a list. Don't use apply(n), since that one has an internal loop.
     * Whereas an iterator maintains a pointer to the current position in the list.
     * @return a list iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> walker = head;

            @Override
            public boolean hasNext() {
                return walker != null;
            }

            @Override
            public T next() {
                final T data = walker.data;
                walker = walker.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Removing of nodes is not supported");
            }
        };
    }

    /**
     * Removes a node at a specified position and returns its value
     * @param n a position in the list
     * @return  a value of the removed node
     */
    public T remove(int n) {
        if (n < 0 || n >= count) outOfBounds(n);
        if (n == 0) {
            // Remove the head of the list
            final T data = head.data;
            head = head.next;
            // What if there was a single node on the list only?
            // Just make sure tail is cleaned up too
            if (head == null) tail = null;
            decrement();
            return data;
        } else {
            // Find the node preceding the one we want to remove
            Node<T> walker = walk(n - 1, head);
            // Capture the value of the node which is going to be removed
            final T data = walker.next.data;
            // 'Remove' the node by changing the pointer to its follower
            // The node is now subject to GC, since nothing is pointing to it
            walker.next = walker.next.next;
            // Make sure tail is adjusted properly in case a node at the very end was removed
            if (walker.next == null) tail = walker;
            decrement();
            // Return the data of the removed node
            return data;
        }
    }

    /**
     * Picks a node at a given position and replaces its value with a new one
     * @param n    a position in the list
     * @param data a new value the found node is to be updated with
     */
    public void update(int n, T data) {
        if (n < 0 || n >= count) outOfBounds(n);
        Node<T> walker = walk(n, head);
        walker.data = data;
    }

    private void increment() { count++; }

    private void decrement() { count--; }

    private Node<T> walk(int n, Node<T> node) {
        for (int i = 0; i < n; i++) node = node.next;
        return node;
    }

    private void outOfBounds(int n) {
        throw new IndexOutOfBoundsException("Requested " + n + " of " + count);
    }


}
