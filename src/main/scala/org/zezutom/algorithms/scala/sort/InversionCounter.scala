package org.zezutom.algorithms.scala.sort

/**
 * Computes the number of inversions in the provided array of integers.
 *
 * Assumptions about the data:
 * - distinct values (duplicate values yield unpredictable results!)
 * - don't need to be sorted, but will end up being sorted in the end
 *
 * This is a modified merge sort: runs in time O(n log n), where n is the length of the array
 */
class InversionCounter {

  /*
   * Merge and count split inversions. Runs in time O(n)
   */
  def mergeAndCount(left: Array[Int], right: Array[Int], product: Array[Int]): Long = {
    var (i, j, k, count) = (0, 0, 0, 0)

    // Merge and count inversions
    while (i < left.length && j < right.length) {
      if (left(i) <= right(j)) {
        product(k) = left(i)
        i += 1
      } else {
        product(k) = right(j)
        j += 1

        // there is at least one inversion
        count += left.length - i
      }
      k += 1
    }

    // Finish the merge
    if (i == left.length) {
      for (m <- j until right.length) {
        product(k) = right(m)
        k += 1
      }
    } else {
      for (m <- i until left.length) {
        product(k) = left(m)
        k += 1
      }
    }

    // Return the inversion count
    count
  }

  def sortAndCount(nums: Array[Int]): Long = {
    // If there is at most a single number there is nothing to do
    if (nums.length < 2) return 0L

    // Split the input array into halves
    val mid = nums.length / 2
    val left = new Array[Int](mid)
    val right = new Array[Int](nums.length - mid)

    // Populate the two sub-arrays
    for (i <- 0 until left.length)
      left(i) = nums(i)
    for (i <- 0 until right.length)
      right(i) = nums(mid + i)

    // Recursively count inversions in both sub-arrays
    val countLeft = sortAndCount(left)
    val countRight = sortAndCount(right)

    // Merge the two sub-arrays together and count inversions
    // caused by the merge
    val product = new Array[Int](nums.length)
    val countMerge = mergeAndCount(left, right, product)

    // Now sort the original array to secure
    // correct results of the recursive calls
    for (i <- 0 until nums.length)
      nums(i) = product(i)

    // Return the sum of all collected counts
    countLeft + countRight + countMerge
  }


}
