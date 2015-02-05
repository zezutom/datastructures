package org.zezutom.algorithms.java.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class InversionCounterTest {

    private InversionCounter counter = new InversionCounter();

    @Test
    public void small_scale_test() {
        int[] nums = new int[] {2, 4, 1, 3, 5};

        // 3 inversions: (2, 1), (4, 1), (4, 3)
        assertEquals(3, counter.sortAndCount(nums));

        // array is sorted
        assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(nums));
    }

    // TODO
    @Test
    public void large_scale_test() {
        String path = getClass().getClassLoader().getResource("IntegerArray.txt").getPath();
        int[] nums = counter.readFromFile(path);

        // The count of inversions is too large to reason about. Take it for a fact.
        assertEquals(2407905288L, counter.sortAndCount(nums));
    }

}
