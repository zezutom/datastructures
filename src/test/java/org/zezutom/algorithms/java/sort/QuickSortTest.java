package org.zezutom.algorithms.java.sort;

import org.junit.Test;
import org.zezutom.util.java.TestUtil;

import static org.junit.Assert.assertEquals;

public class QuickSortTest {

    final String filename = "QuickSort.txt";

    final QuickSort quickSort = new QuickSort();

    final TestUtil testUtil = new TestUtil();

    @Test
    public void comparison_count_pivot_as_first_small_scale() {
        testUtil.assertSort(6, new TestUtil.Sorter() {
            @Override
            public int sort(int... nums) {
                return quickSort.sortUsingFirst(nums);
            }
        });
    }

    @Test
    public void comparison_count_pivot_as_first_large_scale() {
        assertEquals(162085, quickSort.sortUsingFirst(testUtil.readFromFile(filename)));
    }

    @Test
    public void comparison_count_pivot_as_last_small_scale() {
        testUtil.assertSort(8, new TestUtil.Sorter() {
            @Override
            public int sort(int... nums) {
                return quickSort.sortUsingLast(nums);
            }
        });
    }

    @Test
    public void comparison_count_pivot_as_last_large_scale() {
        assertEquals(164123, quickSort.sortUsingLast(testUtil.readFromFile(filename)));
    }

    @Test
    public void comparison_count_pivot_as_median_small_scale() {
        testUtil.assertSort(6, new TestUtil.Sorter() {
            @Override
            public int sort(int... nums) {
                return quickSort.sortUsingMedian(nums);
            }
        });
    }

    @Test
    public void comparison_count_pivot_as_median_large_scale() {
        assertEquals(138382, quickSort.sortUsingMedian(testUtil.readFromFile(filename)));
    }

}
