package org.zezutom.scala.util

import org.junit.Assert._
import org.zezutom.util.scala.StdInHandler

class TestUtil {

  val stdInHandler:StdInHandler = new StdInHandler

  def assertSort(expectedComparisonCount:Int, sort:(Array[Int]) => Long): Unit = {
    val nums = Array(2, 4, 1, 3, 5)

    // total of comparisons
    assertEquals(expectedComparisonCount, sort(nums))

    // array is sorted
    assertArray(nums, 1, 2, 3, 4, 5)
  }

  def assertArray(actual:Array[Int], expected:Int*): Unit = {
    assertTrue(actual.deep == expected.toArray.deep)
  }

  def readFromFile(filename:String): Array[Int] = {
    val path = getClass.getClassLoader.getResource(filename).getPath
    stdInHandler.readFromFile(path)
  }
}
