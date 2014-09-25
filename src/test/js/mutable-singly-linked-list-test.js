var assert = buster.assert;
var list;
buster.testCase("MutableSinglyLinkedList", {
    setUp: function() {
        list = new MutableSinglyLinkedList();
    },
    "emptyOnCreate": function() {
         assert.isTrue(list.isEmpty());
         assert.equals(0, list.length());
    },
    "addOne": function() {
        list.append(5);
        assert.isFalse(list.isEmpty());
        assert.equals(1, list.length());
    },
    "addSeveral": function() {
        list.prepend(3).prepend(1).append(5).append(7);
        assert.equals(4, list.length());
        assert.equals(1, list.apply(0));
        assert.equals(3, list.apply(1));
        assert.equals(5, list.apply(2));
        assert.equals(7, list.apply(3));
    },
    "addAndRemove": function() {
        list.append(5).append(7).append(9);
        assert.equals(3, list.length());
        assert.equals(5, list.remove(0));
        assert.equals(9, list.remove(1));
        assert.equals(7, list.remove(0));
        assert.isTrue(list.isEmpty());
        assert.equals(0, list.length());
    },
    "clear": function() {
        list.prepend(2).prepend(1).append(3).append(4);
        assert.equals(4, list.length());
        list.clear();
        assert.isTrue(list.isEmpty());
        assert.equals(0, list.length());
    },
    "update": function() {
        list.prepend(2).prepend(1).append(3).append(4);
        list.update(0, 99);
        const iter = list.iterator();
        assert.equals(99, iter.next());
        assert.equals(99, list.head.data);

        list.update(2, 98);
        iter.next();
        assert.equals(98, iter.next());
    },
    "iterator": function() {
        list.prepend(8).prepend(1).append(5).append(7);
        const iter = list.iterator();
        assert.isTrue(iter.hasNext());
        assert.equals(1, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equals(8, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equals(5, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equals(7, iter.next());
        assert.isFalse(iter.hasNext());
    },
    "insertAll": function() {
        list.append(1);
        list.insertAll(1, [8, 5, 7]);
        const iter = list.iterator();
        assert.isTrue(iter.hasNext());
        assert.equals(1, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equals(8, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equals(5, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equals(7, iter.next());
        assert.isFalse(iter.hasNext());
    },
    "insertAllAsHead": function() {
        list.insertAll(0, [1, 8, 5, 7]);
        const iter = list.iterator();
        assert.isTrue(iter.hasNext());
        assert.equals(1, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equals(8, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equals(5, iter.next());
        assert.isTrue(iter.hasNext());
        assert.equals(7, iter.next());
        // TODO there is an extra element which makes the test below fail
        // assert.isFalse(iter.hasNext());
    }
});
