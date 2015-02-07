package org.zezutom.algorithms.scala.sort

/**
 * Implementation of a Quick Sort with 3 different kinds of pivot selection:
 * - pivot as the first element of an unsorted array
 * - pivot as the median of the unsorted array
 * - pivot as the last element of an unsorted array
 *
 * At best, the running time is of O(n log n), where n is the length of the input array
 *
 */
class QuickSort {

  /**
   * Sorts the array using the first element as a pivot.
   *
   * @param nums  array of integers
   * @return      the total number of comparisons
   */
  def sortUsingFirst(nums: Array[Int]): Int = {
    quickSort()(nums, 0, nums.length - 1)
  }

  /**
   * Sorts the array using the last element as a pivot. This is achieved
   * by continuous swapping of the first and the last element in the input array.
   *
   * @param nums  the input array
   * @return      the total number of comparisons
   */
  def sortUsingLast(nums: Array[Int]): Int = {
    quickSort((nums: Array[Int], left: Int, right: Int) => { swap(nums, left, right) })(nums, 0, nums.length - 1)
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
  def sortUsingMedian(nums: Array[Int]): Int = {
    quickSort((nums: Array[Int], left: Int, right: Int) => {
      val i = (left + right) / 2
      val (first, last, avg) = (nums(left), nums(right), nums(i))

      // #1 The approximated value fits within the range - the value becomes a pivot
      if ((first < avg && avg <= last) || (last <= avg && avg < first))
        swap(nums, left, i)

      // #2 The value falls out of the range - take the upper bounder as a pivot
      else if ((avg <= last && last < first) || (first < last && last <= avg))
        swap(nums, left, right)

    })(nums, 0, nums.length - 1)
  }

  /**
   * The provided array remains untouched by default.
   *
   * @param nums    the input array
   * @param left    range start
   * @param right   range end
   */
  private def defaultShuffle(nums: Array[Int], left: Int, right: Int) {}

  private def quickSort(shuffle:(Array[Int], Int, Int) => Unit = defaultShuffle)(nums: Array[Int], left: Int, right: Int): Int = {
    if (left >= right) return 0
    shuffle(nums, left, right)
    val q = partition(nums, left, right)
    return (right - left) + quickSort(shuffle)(nums, left, q - 1) + quickSort(shuffle)(nums, q + 1, right)
  }

  /**
   * Ensures that all elements lesser than a pivot precedes it and vice versa.
   *
   * @param nums    the input array
   * @param left    the leftmost index
   * @param right   the rightmost index, i.e. the upper boundary
   * @return        the new upper boundary
   */
  private def partition(nums: Array[Int], left: Int, right: Int):Int = {
    val pivot = left
    var i = left + 1

    for (j <- i to right) {
      if (nums(j) < nums(pivot)) {
        swap(nums, i, j)
        i += 1
      }
    }
    swap(nums, i - 1, pivot)
    return i - 1
  }

  /**
   * Swaps the provided elements within the given array.
   * 
   * @param nums    the input array of integers
   * @param from    to be replaced by 'to'
   * @param to      to be replaced by 'from'
   */
  private def swap(nums: Array[Int], from: Int, to: Int) {
    val tmp = nums(from)
    nums(from) = nums(to)
    nums(to) = tmp
  }
}
