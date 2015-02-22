/**
 * Implementation of a Quick Sort with 3 different kinds of pivot selection:
 * - pivot as the first element of an unsorted array
 * - pivot as the median of the unsorted array
 * - pivot as the last element of an unsorted array
 *
 * At best, the running time is of O(n log n), where n is the length of the input array
 *
 */
function QuickSort() {
    this.quickSort = function(nums, left, right, shuffle) {
        if (left >= right) return 0;
        if (shuffle) shuffle(nums, left, right);
        const q = this.partition(nums, left, right);
        return (right - left) + this.quickSort(nums, left, q - 1, shuffle) + this.quickSort(nums, q + 1, right, shuffle);
    };

    this.partition = function(nums, left, right) {
        const pivot = left;
        var i = left + 1;

        for (var j = i; j <= right; j++) {
            if (nums[j] < nums[pivot]) {
                this.swap(nums, i, j);
                i++;
            }
        }
        this.swap(nums, i - 1, pivot)
        return i - 1
    };

    this.swap = function(nums, from, to) {
        const tmp = nums[from];
        nums[from] = nums[to];
        nums[to] = tmp;
    };
}

QuickSort.prototype = {
    sortUsingFirst: function(nums) {
        return this.quickSort(nums, 0, nums.length - 1);
    },
    sortUsingLast: function(nums) {
        var me = this;
        return this.quickSort(nums, 0, nums.length - 1, function(nums, left, right) {
            me.swap(nums, left, right);
        });
    },
    sortUsingMedian: function(nums) {
        var me = this;
        return this.quickSort(nums, 0, nums.length - 1, function(nums, left, right) {
            const i = Math.floor((left + right) / 2),
                first = nums[left], last = nums[right], avg = nums[i];

            // #1 The approximated value fits within the range - the value becomes a pivot
            if ((first < avg && avg <= last) || (last <= avg && avg < first))
                me.swap(nums, left, i);

            // #2 The value falls out of the range - take the upper bounder as a pivot
            else if ((avg <= last && last < first) || (first < last && last <= avg))
                me.swap(nums, left, right);

        });
    }
}
module.exports = QuickSort;
