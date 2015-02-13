package org.zezutom.algorithms.scala.sort.test

import org.junit.Assert._
import org.junit._
import org.zezutom.algorithms.scala.sort.QuickSort
import org.zezutom.scala.util.TestUtil

class QuickSortTest {

  val filename: String = "QuickSort.txt"

  val quickSort: QuickSort = new QuickSort

  val testUtil:TestUtil = new TestUtil

  @Test def comparison_count_pivot_as_first_small_scale {
    testUtil.assertSort(6, quickSort.sortUsingFirst)
  }

  @Test def comparison_count_pivot_as_first_large_scale {
    assertEquals(162085, quickSort.sortUsingFirst(testUtil.readFromFile(filename)))
  }

  @Test def comparison_count_pivot_as_last_small_scale {
    testUtil.assertSort(8, quickSort.sortUsingLast)
  }

  @Test def comparison_count_pivot_as_last_large_scale {
    assertEquals(164123, quickSort.sortUsingLast(testUtil.readFromFile(filename)))
  }

  @Test def comparison_count_pivot_as_median_small_scale {
    testUtil.assertSort(6, quickSort.sortUsingMedian)
  }

  @Test def comparison_count_pivot_as_median_large_scale {
    assertEquals(138382, quickSort.sortUsingMedian(testUtil.readFromFile(filename)))
  }
}
