var assert = require("chai").assert,
    QuickSort = require("../../../main/js/algorithms/sort/quicksort"),
    TestUtil = require("./test-util"),
    DataSet = require("../data/quicksort-dataset");

describe('QuickSort', function() {
    const quickSort = new QuickSort(),
        testUtil = new TestUtil();

    var assertSort = function(expectedCount, sortMethod) {
        testUtil.assertSort(expectedCount, sortMethod.bind(quickSort));
    };

    var assertComparisonCount = function(expected, sort) {
        sort = sort.bind(quickSort);
        assert.equal(expected, sort(new DataSet().data));
    };

    it('should sort a small array using the first item as a pivot and count comparisons', function() {
        assertSort(6, quickSort.sortUsingFirst);
    });

    it('should sort a small array using the last item as a pivot and count comparisons', function() {
        assertSort(8, quickSort.sortUsingLast);
    });

    it('should sort a small array using the median as a pivot and count comparisons', function() {
        assertSort(6, quickSort.sortUsingMedian);
    });

    it('should sort a large array using the first item as a pivot and count comparisons', function() {
        assertComparisonCount(162085, quickSort.sortUsingFirst);
    });

    it('should sort a large array using the last item as a pivot and count comparisons', function() {
        assertComparisonCount(164123, quickSort.sortUsingLast);
    });

    it('should sort a large array using the median as a pivot and count comparisons', function() {
        assertComparisonCount(138382, quickSort.sortUsingMedian);
    });
});