package com.utils

class Node<T>(var value: T, var next: Node<T>? = null, var prev: Node<T>? = null)

class CircularLinkedList<T> : Iterable<Node<T>> {
    var head: Node<T>? = null
        private set
    var tail: Node<T>? = null
        private set

    var size = 0
        private set

    fun isEmpty(): Boolean = size == 0

    fun insertAtStart(value: T) {
        val newNode = Node(value)

        if (head == null) {
            newNode.next = newNode
            newNode.prev = newNode
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            newNode.prev = head!!.prev
            head!!.prev!!.next = newNode
            head!!.prev = newNode
            head = newNode
//            head?.let { _head ->
//                newNode.next = _head
//                newNode.prev = _head.prev
//                _head.prev.next = newNode
//                _head.prev = newNode
//                head = newNode
//            }
        }
        size++
    }

    fun insertAtEnd(value: T) {
        val newNode = Node(value)

        if (tail == null) {
            newNode.next = newNode
            newNode.prev = newNode
            head = newNode
            tail = newNode
        } else {
            newNode.next = tail!!.next
            newNode.prev = tail
            tail!!.next!!.prev = newNode
            tail!!.next = newNode
            tail = newNode
//            tail?.let { _tail ->
//                newNode.next = _tail.next
//                newNode.prev = _tail
//                _tail.next.prev = newNode
//                _tail.next = newNode
//                tail = newNode
//            }
        }

        size++
    }

    fun insertAfter(node: Node<T>, value: T): Node<T>? {
        var result: Node<T>? = null

        if (contains(node)) {
            val newNode = Node(value)
            newNode.next = node.next
            newNode.prev = node
            node.next!!.prev = newNode
            node.next = newNode
            result = newNode
            size++
            if (tail === node) {
                tail = newNode
            }
        }

        return result
    }

    fun insertBefore(node: Node<T>, value: T): Node<T>? {
        var result: Node<T>? = null

        if (contains(node)) {
            val newNode = Node(value)
            newNode.next = node
            newNode.prev = node.prev
            node.prev!!.next = newNode
            node.prev = newNode
            result = newNode
            if (head === node) {
                head = newNode
            }
            size++
        }

        return result
    }

    fun contains(node: Node<T>): Boolean {
        return (any { it === node })
    }

    fun find(node: Node<T>): Node<T>? {
        var result: Node<T>? = null

        for (n in this) {
            if (n === node) {
                return n
            }
        }

        return result
    }

    fun remove(node: Node<T>): Node<T>? {
        var result: Node<T>? = null

        if (contains(node)) {
            if (size == 1) {
                head = null
                tail = null
                size = 0
            } else {
                if (node === head) {
                    head!!.next!!.prev = head!!.prev
                    head!!.prev!!.next = head!!.next
                    head = node.next
                } else if (node == tail) {
                    tail!!.next!!.prev = tail!!.prev
                    tail!!.prev!!.next = tail!!.next
                    tail = node.prev
                }

                result = node!!.next
                node.prev!!.next = node.next
                node.next!!.prev = node.prev
            }
        }

        size--

        return result
    }

    override fun iterator(): Iterator<Node<T>> = object : Iterator<Node<T>> {
        private var current = head
        private var count = 0

        override fun hasNext() = count < size

        override fun next(): Node<T> {
            if (!hasNext()) {
                throw NoSuchElementException()
            }

            val result = current!!
            current = current?.next
            count++

            return result
        }
    }
}
