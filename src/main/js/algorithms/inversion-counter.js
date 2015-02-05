/**
 * Computes the number of inversions in the provided array of integers.
 *
 * Assumptions about the data:
 * - distinct values (duplicate values yield unpredictable results!)
 * - don't need to be sorted, but will end up being sorted in the end
 *
 * This is a modified merge sort: runs in time O(n log n), where n is the length of the array
 */
function InversionCounter() {}

InversionCounter.prototype = {

    mergeAndCount: function(left, right) {
        var count = 0, sortArr = [];

        while(left.length && right.length) {
            if(right[0] >= left[0]) {
                sortArr.push(left.shift());
            } else {
                count += left.length;
                sortArr.push(right.shift());
            }
        }
        return {'invCount': count, 'sortArr': sortArr.concat(left, right)};
    },
    sortAndCount: function(nums) {
        if (nums.length < 2) return {'invCount': 0, 'sortArr': nums};

        var mid = nums.length / 2 >> 0;     // integer division
        var left = this.sortAndCount(nums.slice(0, mid));
        var right = this.sortAndCount(nums.slice(mid));
        var merged = this.mergeAndCount(left.sortArr, right.sortArr);
        return {'invCount': left.invCount + right.invCount + merged.invCount, 'sortArr': merged.sortArr};
    }
}
module.exports = InversionCounter;