/**
 * Write a program that prints the numbers from 1 to 100 following the rules below:
 * - for multiples of three print “Fizz” (instead of the number)
 * - for the multiples of five print “Buzz”
 * - for numbers which are multiples of both three and five print “FizzBuzz”
 * - otherwise print the number
 */
function FizzBuzz() {}

FizzBuzz.prototype = {
    fizzBuzz: function(count) {
        var arr = [];
        for (var i = 1; i <= count; i++) {
            arr[i - 1] = (i % 3 == 0 && i % 5 == 0) ? 'FizzBuzz'
                : (i % 3 == 0) ? 'Fizz' : (i % 5 == 0) ? 'Buzz' : i;
        }
        return arr;
    }
};

module.exports = FizzBuzz;
