package org.zezutom.util.java;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestUtil {

    private final StdInHandler stdInHandler = new StdInHandler();

    public static interface Sorter {
        int sort(int... nums);
    }

    public void assertSort(int expectedCount, Sorter sorter) {
        final int[] nums = new int[] {2, 4, 1, 3, 5};

        // total of comparisons
        assertEquals(expectedCount, sorter.sort(nums));

        // array is sorted
        assertArray(nums, 1, 2, 3, 4, 5);
    }

    public void assertArray(int[] actual, int... expected) {
        Arrays.equals(actual, expected);
    }


    public int[] readFromFile(String filename) {
        final String path = getClass().getClassLoader().getResource(filename).getPath();
        return stdInHandler.readFromFile(path);
    }
}
