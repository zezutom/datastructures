var assert = require("chai").assert,
    InversionCounter = require("../../../main/js/algorithms/sort/inversion-counter"),
    DataSet = require("../data/inversion-counter-dataset"),
    TestUtil = require("./test-util");

describe('InversionCounter', function() {
    const counter = new InversionCounter(), testUtil = new TestUtil();

    it('should sort a small array and count inversions', function() {
        testUtil.assertSort(3, counter.sortAndCount.bind(counter));
    });

    it('should sort a large array and count inversions', function() {
        assert.equal(2407905288, counter.sortAndCount(new DataSet().data).invCount);
    });
});