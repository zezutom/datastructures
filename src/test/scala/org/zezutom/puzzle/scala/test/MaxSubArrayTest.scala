package org.zezutom.puzzle.scala.test

import org.junit.Assert._
import org.junit.Test
import org.zezutom.puzzle.scala.MaxSubArray

class MaxSubArrayTest {

  val maxSubArray: MaxSubArray = new MaxSubArray

  @Test def slow(): Unit = {
    test(maxSubArray.slow)
  }


  @Test def faster(): Unit = {
    test(maxSubArray.faster)
  }

  private def test(f: Array[Int] => (Int, Int, Int)) = {
    val (start, end, max) = f(Array(-2, -4, 3, -1, 5, 6, -7, -2, 4, -3, 2))
    assertTrue(start == 2)
    assertTrue(end == 5)
    assertTrue(max == 13)
  }
}
