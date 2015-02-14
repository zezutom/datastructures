package org.zezutom.util.scala

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

/**
 * Handles console / file input
 */
class StdInHandler {

  def readStdIn(): Array[Int] = {
    val nums = ArrayBuffer[Int]()

    for (line <- io.Source.stdin.getLines) {
      nums += line.toInt
    }
    nums.toArray
  }

  def readFromFile(path:String): Array[Int] = {
    readLines(Source.fromFile(path).getLines())
  }

  private def readLines(lines:Iterator[String]): Array[Int] = {
    val nums = ArrayBuffer[Int]()

    for (line <- lines) {
      nums += line.toInt
    }
    nums.toArray
  }
}
