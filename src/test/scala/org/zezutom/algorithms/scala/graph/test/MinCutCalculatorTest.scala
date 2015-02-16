package org.zezutom.algorithms.scala.graph.test

import org.junit.Assert._
import org.junit._
import org.zezutom.algorithms.scala.graph.MinCutCalculator
import org.zezutom.util.scala.TestUtil

class MinCutCalculatorTest {

  val minCutCalc: MinCutCalculator = new MinCutCalculator(true)

  @Test def min_cut_of_a_small_graph: Unit = {
    val graph = new minCutCalc.Graph(
      vertices = Vector(Set(1), Set(2), Set(3), Set(4)),
      edges = Vector((1, 2), (1, 3), (2, 1), (2, 3), (2, 4), (3, 1), (3, 2), (3, 4), (4, 2), (4, 3))
    )
    assertEquals(2, minCutCalc.calculateMinCut(graph))
  }

  @Test def min_cut_of_a_larger_graph: Unit = {
    val graphDefinition = new TestUtil().getAbsolutePath("kargerMinCut.txt");
    assertEquals(17, minCutCalc.calculateMinGraph(graphDefinition))
  }
}
