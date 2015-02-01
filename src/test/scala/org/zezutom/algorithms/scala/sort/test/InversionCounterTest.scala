package org.zezutom.algorithms.scala.sort.test

import org.junit.Assert._
import org.junit._
import org.zezutom.algorithms.scala.sort.InversionCounter

import scala.io.Source

class InversionCounterTest {

  val counter:InversionCounter = new InversionCounter

  @Test def small_scale_test {
    val nums = Array(2, 4, 1, 3, 5)

    // 3 inversions: (2, 1), (4, 1), (4, 3)
    assertEquals(3, counter.getInvCount(nums))

    // array is sorted
    assertEquals("1 2 3 4 5", nums.mkString(" ").trim())
  }

  @Test def large_scale_test {
    val path = getClass.getClassLoader.getResource("IntegerArray.txt").getPath
    val nums = counter.readFromFile(path)

    // The count of inversions is too large to reason about. Take it for a fact.
    assertEquals(2407905288L, counter.getInvCount(nums))
  }

}
