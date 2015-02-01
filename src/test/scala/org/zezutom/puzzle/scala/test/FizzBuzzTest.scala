package org.zezutom.puzzle.scala.test

import org.junit.Assert._
import org.junit.Test
import org.zezutom.puzzle.scala.FizzBuzz


class FizzBuzzTest {

  val fizzBuzz:FizzBuzz = new FizzBuzz

  @Test def small_scale() {
    assertEquals("1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz", fizzBuzz.fizzBuzz(15))
  }

  @Test def large_scale() {
    val fizzyBuzzy = fizzBuzz.fizzBuzz(100)
    assertTrue(fizzyBuzzy.contains("2 Fizz"))
    assertTrue(fizzyBuzzy.contains("19 Buzz Fizz 22"))
    assertTrue(fizzyBuzzy.contains("44 FizzBuzz 46 47 Fizz 49 Buzz Fizz 52"))
    assertTrue(fizzyBuzzy.contains("89 FizzBuzz 91 92 Fizz 94 Buzz Fizz 97 98 Fizz Buzz"))
  }

}
