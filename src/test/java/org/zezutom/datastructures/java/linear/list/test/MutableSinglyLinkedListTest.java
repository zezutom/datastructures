package org.zezutom.datastructures.java.linear.list.test;

import org.junit.Before;
import org.junit.Test;
import org.zezutom.datastructures.java.linear.list.MutableSinglyLinkedList;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by tom on 21/09/2014.
 */
public class MutableSinglyLinkedListTest {

    private MutableSinglyLinkedList<Integer> list;

    @Before public void init() {
        list = new MutableSinglyLinkedList<Integer>();
    }

    @Test
    public void emptyOnCreate() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.length());
    }

    @Test public void addOne() {
        list.append(5);
        assertFalse(list.isEmpty());
        assertEquals(1, list.length());
    }

    @Test public void addSeveral() {
        list.prepend(3).prepend(1).append(5).append(7);
        assertEquals(4, list.length());
        assertEquals(1, list.apply(0).intValue());
        assertEquals(3, list.apply(1).intValue());
        assertEquals(5, list.apply(2).intValue());
        assertEquals(7, list.apply(3).intValue());
    }

    @Test public void addAndRemove() {
        list.append(5).append(7).append(9);
        assertEquals(3, list.length());
        assertEquals(5, list.remove(0).intValue());
        assertEquals(9, list.remove(1).intValue());
        assertEquals(7, list.remove(0).intValue());
        assertTrue(list.isEmpty());
        assertEquals(0, list.length());
    }

    @Test public void clear() {
        list.prepend(2).prepend(1).append(3).append(4);
        assertEquals(4, list.length());
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.length());
    }

    @Test public void update() {
        list.prepend(2).prepend(1).append(3).append(4);
        list.update(0, 99);
        final Iterator<Integer> iter = list.iterator();
        assertEquals(99, iter.next().intValue());
        assertEquals(99, list.head().data().intValue());

        list.update(2, 98);
        iter.next();
        assertEquals(98, iter.next().intValue());
    }

    @Test public void iterator() {
        list.prepend(8).prepend(1).append(5).append(7);
        final Iterator<Integer> iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next().intValue());
        assertTrue(iter.hasNext());
        assertEquals(8, iter.next().intValue());
        assertTrue(iter.hasNext());
        assertEquals(5, iter.next().intValue());
        assertTrue(iter.hasNext());
        assertEquals(7, iter.next().intValue());
        assertFalse(iter.hasNext());
    }

    @Test public void insertAll() {
        list.append(1);
        list.insertAll(1, Arrays.asList(8, 5, 7));
        final Iterator<Integer> iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next().intValue());
        assertTrue(iter.hasNext());
        assertEquals(8, iter.next().intValue());
        assertTrue(iter.hasNext());
        assertEquals(5, iter.next().intValue());
        assertTrue(iter.hasNext());
        assertEquals(7, iter.next().intValue());
        assertFalse(iter.hasNext());
    }

    @Test public void insertAllAsHead() {
        list.insertAll(0, Arrays.asList(1, 8, 5, 7));
        final Iterator<Integer> iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(1, iter.next().intValue());
        assertTrue(iter.hasNext());
        assertEquals(8, iter.next().intValue());
        assertTrue(iter.hasNext());
        assertEquals(5, iter.next().intValue());
        assertTrue(iter.hasNext());
        assertEquals(7, iter.next().intValue());
        assertFalse(iter.hasNext());
    }
}
