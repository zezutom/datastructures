package org.zezutom.puzzle.java;

/**
 * Write a program that prints the numbers from 1 to 100 following the rules below:
 * - for multiples of three print “Fizz” (instead of the number)
 * - for the multiples of five print “Buzz”
 * - for numbers which are multiples of both three and five print “FizzBuzz”
 * - otherwise print the number
 */
public class FizzBuzz {

    public String fizzBuzz(int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= count; i++) {
            sb.append((i % 3 == 0 && i % 5 == 0)
                    ? "FizzBuzz" : (i % 3 == 0)
                    ? "Fizz" : (i % 5 == 0)
                    ? "Buzz" : i).append(" ");
        }

        return sb.toString().trim();
    }
}
