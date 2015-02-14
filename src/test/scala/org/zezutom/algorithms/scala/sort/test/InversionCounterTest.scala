package org.zezutom.algorithms.scala.sort.test

import org.junit.Assert._
import org.junit._
import org.zezutom.algorithms.scala.sort.InversionCounter
import org.zezutom.util.scala.TestUtil

class InversionCounterTest {

  val counter: InversionCounter = new InversionCounter

  val testUtil: TestUtil = new TestUtil

  @Test def small_scale_test {
    testUtil.assertSort(3, counter.sortAndCount)
  }

  @Test def large_scale_test {
    assertEquals(2407905288L, counter.sortAndCount(testUtil.readFromFile("IntegerArray.txt")))
  }

}
