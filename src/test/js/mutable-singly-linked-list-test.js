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
    }
});
