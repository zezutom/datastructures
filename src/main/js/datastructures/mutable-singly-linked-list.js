function MutableSinglyLinkedList() {
    this.count = 0;
    this.head = null;
    this.tail = null;
}

function Node(data, next) {
    this.data = data;
    this.next = next;
}

function Iterator(items) {
    this.index = 0;
    this.items = items;
};

Iterator.prototype = {
    next: function() { return this.items[this.index++]; },
    hasNext: function() { return this.index < this.items.length; }
};

function NodeIterator(walker) { this.walker = walker; };

NodeIterator.prototype.hasNext = function() { return this.walker != null; };

NodeIterator.prototype.next = function() {
    var data = this.walker.data;
    this.walker = this.walker.next;
    return data;
};

MutableSinglyLinkedList.prototype = {

    length: function() { return this.count || 0; },

    isEmpty: function() { return this.length() <= 0; },

    /**
     * Appends an node at the end of the list
     * @param data
     * @return an updated list
     */
    append: function(data) {
        var node = new Node(data, null);

        if (this.tail == null) {
            // The list is empty, therefore both head and tail point to the new node
            this.tail = node;
            this.head = this.tail;
        } else {
            // Add a new node to the tail and move the tail pointer to the new node
            this.tail.next = node;
            this.tail = this.tail.next;
        }
        this.increment();
        return this;
    },

    /**
     * Inserts an node at the beginning of the list (prepend)
     * @param data
     * @return an updated list
     */
    prepend: function(data) {
        // Point the head to the new node which in turn points
        // to the node previously referred by head
        this.head = new Node(data, this.head);
        if (this.tail == null) this.tail = this.head;
        this.increment();
        return this;
    },

    /**
     * Returns a value of a node defined by a specific position in the list
     * @param n
     * @return the found value
     */
    apply: function(n) {
        if (n < 0 || n >= this.count) outOfBounds(n);
        return this.walk(n, this.head).data;
    },

    /**
     * Clears the list
     */
    clear: function() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    },

    /**
     * Inserts the given collection to the list
     * @param n     a position in the list the collection should be inserted at
     * @param elems a collection of inserted elements
     */
    insertAll: function(n, elems) {
        // Note that it is fine to insert at the position of the very last node
        if (n < 0 || n > this.count) this.outOfBounds(n);
        // What if we are inserting at the beginning of the list?
        if (n == 0) {
            if (elems.length > 0) {
                // Make the head point to the beginning of the inserted list
                const iterator = new Iterator(elems);
                this.head = new Node(iterator.next(), this.head);
                this.increment();
                var walker = this.head;
                // Insert all of the provided nodes, but the head
                while (iterator.hasNext()) {
                    walker.next = new Node(iterator.next(), walker.next);
                    walker =  walker.next;
                    this.increment();
                }
                // walker.next is actually guaranteed to be null in this case
                if (walker.next == null) this.tail = walker;
            }
        } else {
            // Get to the node preceding the one the insertion starts at
            var walker = this.walk(n - 1, this.head);
            // Insert all of the provided nodes
            elems.forEach(function(elem) {
                walker.next = new Node(elem, walker.next);
                walker = walker.next;
                this.increment();
            }, this);
            // What if we are at the end of the list?
            if(walker.next == null) this.tail = walker;
        }
    },

    /**
     * Provides an efficient way of iterating over a list. Don't use apply(n), since that one has an internal loop.
     * Whereas an iterator maintains a pointer to the current position in the list.
     * @return NodeIterator list iterator
     */
    iterator: function() {
        return new NodeIterator(this.head);
    },

    /**
     * Removes a node at a specified position and returns its value
     * @param n a position in the list
     * @return  a value of the removed node
     */
    remove: function(n) {
        if (n < 0 || n >= this.count) this.outOfBounds(n);
        if (n == 0) {
            // Remove the head of the list
            const headData = this.head.data;
            this.head = this.head.next;
            // What if there was a single node on the list only?
            // Just make sure tail is cleaned up too
            if (this.head == null) this.tail = null;
            this.decrement();
            return headData;
        } else {
            // Find the node preceding the one we want to remove
            var walker = this.walk(n - 1, this.head);
            // Capture the value of the node which is going to be removed
            const data = walker.next.data;
            // 'Remove' the node by changing the pointer to its follower
            // The node is now subject to GC, since nothing is pointing to it
            walker.next = walker.next.next;
            // Make sure tail is adjusted properly in case a node at the very end was removed
            if (walker.next == null) this.tail = walker;
            this.decrement();
            // Return the data of the removed node
            return data;
        }
    },

    /**
     * Picks a node at a given position and replaces its value with a new one
     * @param n    a position in the list
     * @param data a new value the found node is to be updated with
     */
    update: function(n, data) {
        if (n < 0 || n >= this.count) this.outOfBounds(n);
        var walker = this.walk(n, this.head);
        walker.data = data;
    },

    increment: function() { this.count++; },

    decrement: function() { this.count--; },

    walk: function (n, node) {
        for (var i = 0; i < n; i++) node = node.next;
        return node;
    },

    outOfBounds: function(n) { throw "Requested " + n + " of " + this.count; }
};

module.exports = MutableSinglyLinkedList;