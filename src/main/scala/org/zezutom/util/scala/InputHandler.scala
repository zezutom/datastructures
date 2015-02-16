package org.zezutom.util.scala

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Handles console / file input
 */
class InputHandler {

  def readNumbers(): Array[Int] = {
    val nums = ArrayBuffer[Int]()

    for (line <- io.Source.stdin.getLines) {
      nums += line.toInt
    }
    nums.toArray
  }

  def readNumbers(path: String): Array[Int] = {
    val nums = ArrayBuffer[Int]()

    for (line <- getLines(path)) {
      nums += line.toInt
    }
    nums.toArray
  }

  def readGraph(path: String): Map[Int, Array[Int]] = {
    var graph: Map[Int, Array[Int]] = Map()

    for (line <- getLines(path)) {
      val nums = line.split("[^0-9]+")
      graph += (nums.head.toInt -> nums.tail.map(_.toInt))
    }
    graph
  }

  private def getLines(path: String): Iterator[String] = {
    Source.fromFile(path).getLines()
  }
}
