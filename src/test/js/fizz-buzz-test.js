var assert = require("chai").assert;
var FizzBuzz = require("../../main/js/puzzles/fizz-buzz.js");

describe('FizzBuzz', function() {

    const fizzBuzz = new FizzBuzz();

    it('should fiddle with a small array in the expected way', function() {
        assert.deepEqual(
            [1, 2, 'Fizz', 4, 'Buzz', 'Fizz', 7, 8, 'Fizz', 'Buzz', 11, 'Fizz', 13, 14, 'FizzBuzz'],
            fizzBuzz.fizzBuzz(15));
    });

    it('should handle a larger array (selective checkpoints)', function() {
        const fizzyBuzzy = fizzBuzz.fizzBuzz(100);
        //fizzyBuzzy.reduce

        //assertTrue(fizzyBuzzy.contains("2 Fizz"))
        //assertTrue(fizzyBuzzy.contains("19 Buzz Fizz 22"))
        //assertTrue(fizzyBuzzy.contains("44 FizzBuzz 46 47 Fizz 49 Buzz Fizz 52"))
        //assertTrue(fizzyBuzzy.contains("89 FizzBuzz 91 92 Fizz 94 Buzz Fizz 97 98 Fizz Buzz"))

    });

});