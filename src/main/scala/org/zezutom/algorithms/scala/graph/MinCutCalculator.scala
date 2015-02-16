package org.zezutom.algorithms.scala.graph

import scala.io.Source
import scala.util.Random

/**
 * Karger's algorithm - a randomized contraction algorithm for finding
 * a minimum cut in an undirected graph.
 * (http://en.wikipedia.org/wiki/Karger%27s_algorithm)
 *
 * This is a "naive" implementation as a new graph is created from
 * the old one in each iteration.
 *
 *  Run-time complexity: O(n2)
 */
class MinCutCalculator(verbose: Boolean = false) {

  /**
   * Representation of an undirected graph
   *
   * @param vertices  a list of contracted (merged) vertices
   * @param edges     a list of all edges (vertexFrom, vertexTo)
   */
  case class Graph(vertices: Vector[Set[Int]], edges: Vector[(Int, Int)])


  /**
   * Calculates the minimum cut using Karger's algorithm
   *
   * @param graphTxt  graph textual definition as an absolute file path
   * @return  the minimum cut in the graph as a result of a large(r) number of iterations
   */
  def calculateMinGraph(graphTxt: String): Int = {
    calculateMinCut(loadGraph(graphTxt))
  }

  /**
   * Calculates the minimum cut using Karger's algorithm
   *
   * @param graph
   * @return  the minimum cut in the graph as a result of a large(r) number of iterations
   */
  def calculateMinCut(graph: Graph): Int = {
    val (iter, printStep) = (100, 10)

    if (verbose) println(s"Searching for min cut through ${iter} loops")

    implicit val rand = new Random
    var minCut = contractVerticesAndCountCuts(graph)
    if (verbose) {
      println(s"Found ${minCut} as a suitable candidate\nSearching further...")
    }
    for (i <- 1 to iter) {
      rand.setSeed(i)
      if (verbose & (i % printStep == 0)) println(s"Loop ${i}\tmin cut: ${minCut}")
      val cuts = contractVerticesAndCountCuts(graph)
      if (minCut > cuts) {
        minCut = cuts
        if (verbose) println(s"Loop ${i} found a new minimum of ${minCut} cuts")
      }
    }
    minCut
  }

  private def contractVerticesAndCountCuts(graph: Graph)(implicit rand:Random): Int = {
    if (graph.vertices.size <= 2) return graph.edges count {
        // Only count the edges between two standalone vertices (X -- n edges -- Y)
        // Vertices which have already been contracted do not count!
        case (x, y) ⇒ (graph.vertices.head contains x) & !(graph.vertices.head contains y)
      }

    def indexOf(vertex: Int): Int = {
      graph.vertices indexWhere (_ contains vertex)
    }

    // Take a random edge (v1, v2)
    val (v1, v2) = graph.edges(rand.nextInt(graph.edges.size))
    val (v1_index, v2_index) = (indexOf(v1), indexOf(v2))

    // Contract v2 into v1
    val contractedVertex = graph.vertices(v1_index) ++ graph.vertices(v2_index)
    val newVertices = graph.vertices
      .updated(v1_index, contractedVertex)                 // creates new Vector with Set having both v1 an v2
      .patch(from = v2_index, patch = Nil, replaced = 1)   // removes the contracted vertex

    // Filter out any loops
    val newEdges = graph.edges.filterNot {
      case (from, to) ⇒                                   // removes the edge of the contracted vertex
        (contractedVertex contains from) && (contractedVertex contains to)
    }
    // construct a new graph and iterate
    contractVerticesAndCountCuts(Graph(newVertices, newEdges))    
  }

  /**
   * Parses the graph definition and turns it into the actual Graph object
   * 
   * @param graphTxt  graph textual definition as an absolute file path 
   * @return          a new Graph instance
   */
  private def loadGraph(graphTxt: String): Graph =
    Source.fromFile(graphTxt)
      .mkString
      .lines
      .foldLeft(Graph(Vector(), Vector())) {
      (graph, line) ⇒
        val vertices = line.split("[^0-9]+").foldLeft(Vector[Int]()) {
          (v, vertexStr) ⇒ v :+ vertexStr.toInt
        }
        val vertex = vertices.head
        val edges = vertices.tail.map(target ⇒ (vertex, target))
        Graph(
          graph.vertices :+ Set(vertex),
          graph.edges ++ edges)
    }
}
