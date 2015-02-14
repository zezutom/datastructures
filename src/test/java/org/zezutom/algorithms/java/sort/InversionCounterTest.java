package org.zezutom.algorithms.java.sort;

import org.junit.Test;
import org.zezutom.util.java.TestUtil;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class InversionCounterTest {

    private final InversionCounter counter = new InversionCounter();

    private final TestUtil testUtil = new TestUtil();

    @Test
    public void small_scale_test() {
        int[] nums = new int[] {2, 4, 1, 3, 5};

        // 3 inversions: (2, 1), (4, 1), (4, 3)
        assertEquals(3, counter.sortAndCount(nums));

        // array is sorted
        assertEquals("[1, 2, 3, 4, 5]", Arrays.toString(nums));
    }

    @Test
    public void large_scale_test() {
        assertEquals(2407905288L, counter.sortAndCount(testUtil.readFromFile("IntegerArray.txt")));
    }

}
