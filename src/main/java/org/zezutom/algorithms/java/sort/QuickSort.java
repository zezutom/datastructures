package org.zezutom.algorithms.java.sort;

/**
 * Implementation of a Quick Sort with 3 different kinds of pivot selection:
 * - pivot as the first element of an unsorted array
 * - pivot as the median of the unsorted array
 * - pivot as the last element of an unsorted array
 *
 * At best, the running time is of O(n log n), where n is the length of the input array
 *
 */
public class QuickSort {
    /**
     * Sorts the array using the first element as a pivot.
     *
     * @param nums  array of integers
     * @return      the total number of comparisons
     */
    public int sortUsingFirst(int... nums) {
        return quickSort(new ShuffleStrategy() {
            @Override
            public void shuffle(int[] nums, int left, int right) {
                // Do nothing
            }
        }, nums, 0, nums.length - 1);
    }

    /**
     * Sorts the array using the last element as a pivot. This is achieved
     * by continuous swapping of the first and the last element in the input array.
     *
     * @param nums  the input array
     * @return      the total number of comparisons
     */
    public int sortUsingLast(int... nums) {
        return quickSort(new ShuffleStrategy() {
            @Override
            public void shuffle(int[] nums, int left, int right) {
                swap(nums, left, right);
            }
        }, nums, 0, nums.length - 1);
    }

    /**
     * Sorts the array using the median element as a pivot. This is achieved
     * by continuous evaluation of the central element in the input array. If the value
     * happens to fit within the boundaries, it becomes a new pivot. Otherwise, the rightmost
     * element is used as a median instead.
     *
     * @param nums  the input array
     * @return  the total number of comparisons
     */
    public int sortUsingMedian(int... nums) {
        return quickSort(new ShuffleStrategy() {
            @Override
            public void shuffle(int[] nums, int left, int right) {
                final int i = (left + right) / 2;
                final int first = nums[left], last = nums[right], avg = nums[i];

                // #1 The approximated value fits within the range - the value becomes a pivot
                if ((first < avg && avg <= last) || (last <= avg && avg < first))
                    swap(nums, left, i);

                    // #2 The value falls out of the range - take the upper bounder as a pivot
                else if ((avg <= last && last < first) || (first < last && last <= avg))
                    swap(nums, left, right);
            }
        }, nums, 0, nums.length - 1);
    }

    interface ShuffleStrategy {
        void shuffle(int[] nums, int left, int right);
    }

    private int quickSort(ShuffleStrategy shuffler, int[] nums, int left, int right) {
        if (left >= right) return 0;
        shuffler.shuffle(nums, left, right);
        final int q = partition(nums, left, right);
        return (right - left) + quickSort(shuffler, nums, left, q - 1) + quickSort(shuffler, nums, q + 1, right);
    }

    /**
     * Ensures that all elements lesser than a pivot precedes it and vice versa.
     *
     * @param nums    the input array
     * @param left    the leftmost index
     * @param right   the rightmost index, i.e. the upper boundary
     * @return        the new upper boundary
     */
    private int partition(int[] nums, int left, int right) {
        final int pivot = left;
        int i = left + 1;

        for (int j = i; j <= right; j++) {
            if (nums[j] < nums[pivot]) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i - 1, pivot);
        return i - 1;
    }

    /**
     * Swaps the provided elements within the given array.
     *
     * @param nums    the input array of integers
     * @param from    to be replaced by 'to'
     * @param to      to be replaced by 'from'
     */
    private void swap(int[] nums, int from, int to) {
        final int tmp = nums[from];
        nums[from] = nums[to];
        nums[to] = tmp;
    }
}
