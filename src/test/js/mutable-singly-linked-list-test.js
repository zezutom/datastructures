var assert = require("chai").assert;
var MutableSinglyLinkedList = require("../../main/js/mutable-singly-linked-list.js");

describe('MutableSinglyLinkedList', function() {
    var list;

    beforeEach(function() {
        list = new MutableSinglyLinkedList();
    });

    it('should be empty on create', function() {
        assert.isTrue(list.isEmpty());
        assert.equal(0, list.length());
    });

    it('should be possible to add a single node', function() {
        list.append(5);
        assert.isFalse(list.isEmpty());
        assert.equal(1, list.length());
    });

    it('should be possible to add several nodes', function() {
        list.prepend(3).prepend(1).append(5).append(7);
        assert.equal(4, list.length());
        assert.equal(1, list.apply(0));
        assert.equal(3, list.apply(1));
        assert.equal(5, list.apply(2));
        assert.equal(7, list.apply(3));
    });

    it('should be possible to add and remove nodes', function() {
        list.append(5).append(7).append(9);
        assert.equal(3, list.length());
        assert.equal(5, list.remove(0));
        assert.equal(9, list.remove(1));
        assert.equal(7, list.remove(0));
        assert.isTrue(list.isEmpty());
        assert.equal(0, list.length());
    });

    it('should be possible to clear the list', function() {
        list.prepend(2).prepend(1).append(3).append(4);
        assert.equal(4, list.length());
        list.clear();
        assert.isTrue(list.isEmpty());
        assert.equal(0, list.length());
    });

    it('should be possible to update the list', function() {
        list.prepend(2).prepend(1).append(3).append(4);
        list.update(0, 99);
        const iter = list.iterator();
        assert.equal(99, iter.next());
        assert.equal(99, list.head.data);

        list.update(2, 98);
        iter.next();
        assert.equal(98, iter.next());
    });

    it('should be possible to iterate over the list', function() {
        list.prepend(8).prepend(1).append(5).append(7);
        const iter = list.iterator();
        assert.isTrue(iter.hasNext());
        assert.equal(1, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equal(8, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equal(5, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equal(7, iter.next());
        assert.isFalse(iter.hasNext());
    });

    it('should be possible to insert a collection of elements', function() {
        list.append(1);
        list.insertAll(1, [8, 5, 7]);
        const iter = list.iterator();
        assert.isTrue(iter.hasNext());
        assert.equal(1, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equal(8, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equal(5, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equal(7, iter.next());
        assert.isFalse(iter.hasNext());
    });

    it('should be possible to insert a collection of elements to the head', function() {
        list.insertAll(0, [1, 8, 5, 7]);
        const iter = list.iterator();
        assert.isTrue(iter.hasNext());
        assert.equal(1, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equal(8, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equal(5, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equal(7, iter.next());
        assert.isFalse(iter.hasNext());
    });

});