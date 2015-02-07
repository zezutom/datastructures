package org.zezutom.algorithms.scala.sort.test

import org.junit.Assert._
import org.junit._
import org.zezutom.algorithms.scala.sort.QuickSort
import org.zezutom.util.scala.StdInHandler

class QuickSortTest {

  val quickSort: QuickSort = new QuickSort

  val stdInHandler:StdInHandler = new StdInHandler

  @Test def comparison_count_pivot_as_first_small_scale {
    val nums = Array(2, 4, 1, 3, 5)

    // total of comparisons
    assertEquals(6, quickSort.sortUsingFirst(nums))

    // array is sorted
    assertEquals("1 2 3 4 5", nums.mkString(" ").trim())
  }

  @Test def comparison_count_pivot_as_first_large_scale {
    val path = getClass.getClassLoader.getResource("QuickSort.txt").getPath
    val nums = stdInHandler.readFromFile(path)

    // The count of inversions is too large to reason about. Take it for a fact.
    assertEquals(162085, quickSort.sortUsingFirst(nums))
  }

  @Test def comparison_count_pivot_as_last_small_scale {
    val nums = Array(2, 4, 1, 3, 5)

    // total of comparisons
    assertEquals(8, quickSort.sortUsingLast(nums))

    // array is sorted
    assertEquals("1 2 3 4 5", nums.mkString(" ").trim())
  }

  @Test def comparison_count_pivot_as_last_large_scale {
    val path = getClass.getClassLoader.getResource("QuickSort.txt").getPath
    val nums = stdInHandler.readFromFile(path)

    // The count of inversions is too large to reason about. Take it for a fact.
    assertEquals(164123, quickSort.sortUsingLast(nums))
  }

  @Test def comparison_count_pivot_as_median_small_scale {
    val nums = Array(2, 4, 1, 3, 5)

    quickSort.sortUsingMedian(nums)
    // total of comparisons
    assertEquals(10, quickSort.sortUsingFirst(nums))

    // array is sorted
    assertEquals("1 2 3 4 5", nums.mkString(" ").trim())
  }

  @Test def comparison_count_pivot_as_median_large_scale {
    val path = getClass.getClassLoader.getResource("QuickSort.txt").getPath
    val nums = stdInHandler.readFromFile(path)

    // The count of inversions is too large to reason about. Take it for a fact.
    assertEquals(138382, quickSort.sortUsingMedian(nums))
  }

}
