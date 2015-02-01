package org.zezutom.algorithms.scala.sort

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

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

  def readStdIn(): Array[Int] = {
    val nums = ArrayBuffer[Int]()

    for (line <- io.Source.stdin.getLines) {
      nums += line.toInt
    }
    nums.toArray
  }

  def readFromFile(path:String): Array[Int] = {
    readLines(Source.fromFile(path).getLines())
  }

  private def readLines(lines:Iterator[String]): Array[Int] = {
    val nums = ArrayBuffer[Int]()

    for (line <- lines) {
      nums += line.toInt
    }
    nums.toArray
  }

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
      for (m <- j to right.length - 1) {
        product(k) = right(m)
        k += 1
      }
    } else {
      for (m <- i to left.length - 1) {
        product(k) = left(m)
        k += 1
      }
    }

    // Return the inversion count
    count
  }

  def getInvCount(nums: Array[Int]): Long = {
    // If there is at most a single number there is nothing to do
    if (nums.length <= 1) return 0L

    // Split the input array into halves
    val mid = nums.length / 2
    val left = new Array[Int](mid)
    val right = new Array[Int](nums.length - mid)

    // Populate the two sub-arrays
    for (i <- 0 to mid - 1)
      left(i) = nums(i)
    for (i <- 0 to nums.length - mid - 1)
      right(i) = nums(mid + i)

    // Recursively count inversions in both sub-arrays
    val countLeft = getInvCount(left)
    val countRight = getInvCount(right)

    // Merge the two sub-arrays together and count inversions
    // caused by the merge
    val product = new Array[Int](nums.length)
    val countMerge = mergeAndCount(left, right, product)

    // Now sort the original array to secure
    // correct results of the recursive calls
    for (i <- 0 to nums.length - 1)
      nums(i) = product(i)

    // Return the sum of all collected counts
    countLeft + countRight + countMerge
  }


}
