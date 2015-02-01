package org.zezutom.puzzle.scala

/**
 * Write a program that prints the numbers from 1 to 100 following the rules below:
 * - for multiples of three print “Fizz” (instead of the number)
 * - for the multiples of five print “Buzz”
 * - for numbers which are multiples of both three and five print “FizzBuzz”
 * - otherwise print the number
 */
class FizzBuzz {

  def fizzBuzz(count:Int):String = {
    Array.tabulate(count)(n => n + 1 match {
      case x if (x % 3 == 0 && x % 5 == 0) => "FizzBuzz"
      case x if (x % 3 == 0) => "Fizz"
      case x if (x % 5 == 0) => "Buzz"
      case _ => (n + 1).toString
    }).mkString(" ").trim()
  }
}
