package org.zezutom.algorithms.java.sort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Computes the number of inversions in the provided array of integers.
 *
 * Assumptions about the data:
 * - distinct values (duplicate values yield unpredictable results!)
 * - don't need to be sorted, but will end up being sorted in the end
 *
 * This is a modified merge sort: runs in time O(n log n), where n is the length of the array
 */
public class InversionCounter {


    public int[] readFromFile(String path) {
        List<Integer> numList = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                numList.add(Integer.valueOf(br.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read from " + path);
        }

        int[] nums = new int[numList.size()];

        for (int i = 0; i < numList.size(); i++) {
            nums[i] = numList.get(i);
        }
        return nums;
    }

    private long mergeAndCount(int[] left, int[] right, int[] product) {
        int i = 0, j = 0, k = 0, count = 0;

        // Merge and count inversions
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j])
                product[k++] = left[i++];
            else {
                product[k++] = right[j++];

                // there is at least one inversion
                count += left.length - i;
            }
        }

        // Finish the merge
        if (i == left.length) {
            for (int m = j; m < right.length; m++)
                product[k++] = right[m];
        } else {
            for (int m = i; m < left.length; m++)
                product[k++] = left[m];
        }

        // Return the inversion count
        return count;

    }

    public long sortAndCount(int... nums) {
        // If there is at most a single number there is nothing to do
        if (nums == null || nums.length < 2) return 0;

        // Split the input array into halves
        int mid = nums.length / 2;
        int[] left = new int[mid];
        int[] right = new int[nums.length - mid];

        // Populate the two sub-arrays
        for (int i = 0; i < left.length; i++)
            left[i] = nums[i];
        for (int i = 0; i < right.length; i++)
            right[i] = nums[mid + i];

        // Recursively count inversions in both sub-arrays
        long countLeft = sortAndCount(left);
        long countRight = sortAndCount(right);

        // Merge the two sub-arrays together and count inversions
        // caused by the merge
        int[] product = new int[nums.length];
        long countMerge = mergeAndCount(left, right, product);

        // Now sort the original array to secure
        // correct results of the recursive calls
        for (int i = 0; i < nums.length; i++)
            nums[i] = product[i];

        // Return the sum of all collected counts
        return countLeft + countRight + countMerge;
    }
}
