package org.zezutom.puzzle.scala

/**
 * Given an array of integers find the sub-array whose elements have the largest sum.
 */
class MaxSubArray {

  /**
   *  Inefficient solution:
   *  
   *  3 nested loops, each of up to n-times iterations
   *  running time of O(n3)
   *
   * @param n   an input array of integers
   * @return    the found sub-array identified by its boundaries (start, end) and the max value
   */

  def slow(n: Array[Int]): (Int, Int, Int) = {
    var (start, end, max) = (0, 0, n.head)
    for (j <- 1 until n.length) {
      for (k <- j until n.length) {
        var s = 0
        for (i <- j to k) {
          s += n(i)
          if (s > max) {
            start = j; end = i; max = s
          }
        }
      }
    }
    (start, end, max)
  }

  /**
   * An improved version of the max sub-array algorithm.
   *
   * The improvement is based on the observation that a lot of time is wasted
   * on recomputing all the sub-array summations from scratch in the inner-most loop.
   *
   * To make the algorithm more efficient, the sums are calculated beforehand. The result
   * is an array of "prefix sums". From that point on all of the sub-array sums can be
   * calculated in constant time by using this formula: Sj,k = Sk - Sj-1
   *
   *  2 nested loops, each of up to n-times iterations
   *  running time of O(n2)
   *
   * @param n   an input array of integers
   * @return    the found sub-array identified by its boundaries (start, end) and the max value
   */
  def faster(n: Array[Int]): (Int, Int, Int) = {
    var (start, end, sums) = (0, 0, Vector[Int](0))
    for (i <- 1 until n.length)
      sums = sums :+ (sums(i - 1) + n(i))
    var max = 0
    for (j <- 1 until n.length) {
      for (k <- j until n.length) {
        val s = sums(k) - sums(j - 1)
        if (s > max) {
          start = j; end = k; max = s
        }
      }
    }
    (start, end, max)
  }

  /**
   * The fastest version of the max sub-array algorithm.
   *
   * The improvement takes the idea of partials sums to the next level. Instead
   * of computing prefix sums, we compute maximum suffix sums.
   *
   * That allows to skip nested calculations and reduces the number of loops to a single one.
   *
   *  a single loop, up to n-times iterations
   *  running time of O(n)
   *
   * @param n   an input array of integers
   * @return    the found sub-array identified by its boundaries (start, end) and the max value
   */
  def fastest(n: Array[Int]): (Int, Int, Int) = {
    var (start, end, sums) = (0, 0, Vector[Int](0))
    for (i <- 1 until n.length)
      sums = sums :+ Math.max(0, sums(i - 1) + n(i))
    var (max, k) = (0, 0)
    for (j <- 1 until n.length) {
      if (sums(j) > max) {
        max = sums(j); start = k; end = j;
        k += 1
      }
    }
    (start, end, max)
  }
}
