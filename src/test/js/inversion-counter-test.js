var assert = require("chai").assert;
var InversionCounter = require("../../main/js/algorithms/inversion-counter.js");
var IntArrContainer = require("./int-array-container.js");

describe('InversionCounter', function() {
    const counter = new InversionCounter();

    it('should sort a small array and return the expected inversion count', function() {
        const nums = [2, 4, 1, 3, 5];
        const out = counter.sortAndCount(nums);
        // 3 inversions: (2, 1), (4, 1), (4, 3)
        assert.equal(3, out.invCount);

        // array is sorted
        assert.deepEqual([1, 2, 3, 4, 5], out.sortArr);
    });

    it('should sort a large array and return the expected inversion count', function() {
        const nums = new IntArrContainer().data();
        const out = counter.sortAndCount(nums);
        // The count of inversions is too large to reason about. Take it for a fact.
        assert.equal(2407905288, out.invCount);
    });
});

