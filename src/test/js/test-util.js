var assert = require("chai").assert;

function TestUtil() {}

TestUtil.prototype = {

    assertSort: function(expectedCount, sort) {
        const nums = [2, 4, 1, 3, 5], result = sort(nums);

        // total of inversions or comparisons
        assert.equal(expectedCount, (result.invCount) ? result.invCount : result);

        // array is sorted
        assert.deepEqual([1, 2, 3, 4, 5], (result.sortArr) ? result.sortArr : nums);
    }
}
module.exports = TestUtil;