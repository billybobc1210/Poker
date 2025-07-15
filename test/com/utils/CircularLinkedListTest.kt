package com.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

public class CircularLinkedListTest {
    @Test
    fun insertAtStartTest() {
        val circularList = CircularLinkedList<Int>()

        circularList.insertAtStart(1)
        circularList.insertAtStart(2)
        circularList.insertAtStart(3)
        circularList.insertAtStart(4)
        circularList.insertAtStart(5)
        circularList.insertAtStart(6)
        circularList.insertAtStart(7)
        circularList.insertAtStart(8)
        circularList.insertAtStart(9)
        circularList.insertAtStart(10)

        val list = circularList.map { it }

        assertEquals(10, circularList.size)
        assertEquals(10, list.size)
        assertEquals(10, list[0].value)
        assertEquals(9, list[1].value)
        assertEquals(8, list[2].value)
        assertEquals(7, list[3].value)
        assertEquals(6, list[4].value)
        assertEquals(5, list[5].value)
        assertEquals(4, list[6].value)
        assertEquals(3, list[7].value)
        assertEquals(2, list[8].value)
        assertEquals(1, list[9].value)
    }

    @Test
    fun insertAtEndTest() {
        val circularList = CircularLinkedList<Int>()

        circularList.insertAtEnd(1)
        circularList.insertAtEnd(2)
        circularList.insertAtEnd(3)
        circularList.insertAtEnd(4)
        circularList.insertAtEnd(5)
        circularList.insertAtEnd(6)
        circularList.insertAtEnd(7)
        circularList.insertAtEnd(8)
        circularList.insertAtEnd(9)
        circularList.insertAtEnd(10)

        val list = circularList.map { it }

        assertEquals(10, circularList.size)
        assertEquals(10, list.size)
        assertEquals(1, list[0].value)
        assertEquals(2, list[1].value)
        assertEquals(3, list[2].value)
        assertEquals(4, list[3].value)
        assertEquals(5, list[4].value)
        assertEquals(6, list[5].value)
        assertEquals(7, list[6].value)
        assertEquals(8, list[7].value)
        assertEquals(9, list[8].value)
        assertEquals(10, list[9].value)
    }

    @Test
    fun insertAfterTest() {
        val circularList = CircularLinkedList<Int>()

        circularList.insertAtEnd(1)
        circularList.insertAtEnd(2)
        circularList.insertAtEnd(3)
        circularList.insertAtEnd(4)
        circularList.insertAtEnd(5)
        circularList.insertAtEnd(6)
        circularList.insertAtEnd(7)
        circularList.insertAtEnd(8)
        circularList.insertAtEnd(9)
        circularList.insertAtEnd(10)

        var list = circularList.map { it }

        circularList.insertAfter(list[2], 11) // 1, 2, 3, 11, 4, 5, 6, 7, 8, 9, 10
        circularList.insertAfter(list[0], 12) // 1, 12, 2, 3, 11, 4, 5, 6, 7, 8, 9, 10
        circularList.insertAfter(list[9], 13) // 1, 12, 2, 3, 11, 4, 5, 6, 7, 8, 9, 10, 13

        list = circularList.map { it }

        assertEquals(13, circularList.size)
        assertEquals(13, list.size)
        assertEquals(1, list[0].value)
        assertEquals(12, list[1].value)
        assertEquals(2, list[2].value)
        assertEquals(3, list[3].value)
        assertEquals(11, list[4].value)
        assertEquals(4, list[5].value)
        assertEquals(5, list[6].value)
        assertEquals(6, list[7].value)
        assertEquals(7, list[8].value)
        assertEquals(8, list[9].value)
        assertEquals(9, list[10].value)
        assertEquals(10, list[11].value)
        assertEquals(13, list[12].value)
    }

    @Test
    fun insertBeforeTest() {
        val circularList = CircularLinkedList<Int>()

        circularList.insertAtEnd(1)
        circularList.insertAtEnd(2)
        circularList.insertAtEnd(3)
        circularList.insertAtEnd(4)
        circularList.insertAtEnd(5)
        circularList.insertAtEnd(6)
        circularList.insertAtEnd(7)
        circularList.insertAtEnd(8)
        circularList.insertAtEnd(9)
        circularList.insertAtEnd(10)

        var list = circularList.map { it }

        circularList.insertBefore(list[2], 11) // 1, 2, 11, 3, 4, 5, 6, 7, 8, 9, 10
        circularList.insertBefore(list[0], 12) // 12, 1, 2, 11, 3, 4, 5, 6, 7, 8, 9, 10
        circularList.insertBefore(list[9], 13) // 12, 1, 2, 11, 3, 4, 5, 6, 7, 8, 9, 13, 10

        list = circularList.map { it }

        assertEquals(13, circularList.size)
        assertEquals(13, list.size)
        assertEquals(12, list[0].value)
        assertEquals(1, list[1].value)
        assertEquals(2, list[2].value)
        assertEquals(11, list[3].value)
        assertEquals(3, list[4].value)
        assertEquals(4, list[5].value)
        assertEquals(5, list[6].value)
        assertEquals(6, list[7].value)
        assertEquals(7, list[8].value)
        assertEquals(8, list[9].value)
        assertEquals(9, list[10].value)
        assertEquals(13, list[11].value)
        assertEquals(10, list[12].value)
    }

    @Test
    fun mixedInsertTest() {
        val circularList = CircularLinkedList<Int>()

        circularList.insertAtEnd(1)
        circularList.insertAtEnd(2)
        circularList.insertAtEnd(3)
        circularList.insertAtEnd(4)
        circularList.insertAtEnd(5)
        circularList.insertAtEnd(6)
        circularList.insertAtEnd(7)
        circularList.insertAtEnd(8)
        circularList.insertAtEnd(9)
        circularList.insertAtEnd(10)

        var list = circularList.map { it }

        circularList.insertBefore(list[2], 11) // 1, 2, 11, 3, 4, 5, 6, 7, 8, 9, 10
        circularList.insertAfter(list[0], 12)  // 1, 12, 2, 11, 3, 4, 5, 6, 7, 8, 9, 10
        circularList.insertAtStart(13)         // 13, 1, 12, 2, 11, 3, 4, 5, 6, 7, 8, 9, 10
        circularList.insertAtEnd(14)           // 13, 1, 12, 2, 11, 3, 4, 5, 6, 7, 8, 9, 10, 14
        circularList.insertAfter(list[9], 15)  // 13, 1, 12, 2, 11, 3, 4, 5, 6, 7, 8, 9, 10, 15, 14
        circularList.insertAtStart(16)         // 16, 13, 1, 12, 2, 11, 3, 4, 5, 6, 7, 8, 9, 10, 15, 14
        circularList.insertAtEnd(17)           // 16, 13, 1, 12, 2, 11, 3, 4, 5, 6, 7, 8, 9, 10, 15, 14, 17
        circularList.insertBefore(list[5], 18) // 16, 13, 1, 12, 2, 11, 3, 4, 5, 18, 6, 7, 8, 9, 10, 15, 14, 17
        circularList.insertAfter(list[4], 19)  // 16, 13, 1, 12, 2, 11, 3, 4, 5, 19, 18, 6, 7, 8, 9, 10, 15, 14, 17
        circularList.insertAtStart(20)         // 20, 16, 13, 1, 12, 2, 11, 3, 4, 5, 19, 18, 6, 7, 8, 9, 10, 15, 14, 17

        list = circularList.map { it }

        assertEquals(20, circularList.size)
        assertEquals(20, list.size)
        assertEquals(20, list[0].value)
        assertEquals(16, list[1].value)
        assertEquals(13, list[2].value)
        assertEquals(1, list[3].value)
        assertEquals(12, list[4].value)
        assertEquals(2, list[5].value)
        assertEquals(11, list[6].value)
        assertEquals(3, list[7].value)
        assertEquals(4, list[8].value)
        assertEquals(5, list[9].value)
        assertEquals(19, list[10].value)
        assertEquals(18, list[11].value)
        assertEquals(6, list[12].value)
        assertEquals(7, list[13].value)
        assertEquals(8, list[14].value)
        assertEquals(9, list[15].value)
        assertEquals(10, list[16].value)
        assertEquals(15, list[17].value)
        assertEquals(14, list[18].value)
        assertEquals(17, list[19].value)
    }

    @Test
    fun findTest() {
        val circularList = CircularLinkedList<Int>()

        circularList.insertAtEnd(1)
        circularList.insertAtEnd(2)
        circularList.insertAtEnd(3)
        circularList.insertAtEnd(4)
        circularList.insertAtEnd(5)
        circularList.insertAtEnd(6)
        circularList.insertAtEnd(7)
        circularList.insertAtEnd(8)
        circularList.insertAtEnd(9)
        circularList.insertAtEnd(10)

        val list = circularList.map { it }

        for (i in 0 until list.size) {
            var success = true

            try {
                assertEquals(i + 1, circularList.find(list[i])!!.value)
            } catch (e: Exception) {
                success = false
            }

            assertTrue(success)
        }
    }

    @Test
    fun removeTest() {
        val circularList = CircularLinkedList<Int>()

        circularList.insertAtEnd(1)
        circularList.insertAtEnd(2)
        circularList.insertAtEnd(3)
        circularList.insertAtEnd(4)
        circularList.insertAtEnd(5)
        circularList.insertAtEnd(6)
        circularList.insertAtEnd(7)
        circularList.insertAtEnd(8)
        circularList.insertAtEnd(9)
        circularList.insertAtEnd(10)

        var list = circularList.map { it }

        circularList.remove(list[5])   // 1, 2, 3, 4, 5, 7, 8, 9, 10
        circularList.remove(list[1])   // 1, 3, 4, 5, 7, 8, 9, 10
        circularList.remove(list[9])   // 1, 3, 4, 5, 7, 8, 9
        circularList.remove(list[0])   // 3, 4, 5, 7, 8, 9
        circularList.remove(list[6])   // 3, 4, 5, 8, 9

        list = circularList.map { it }

        assertEquals(5, circularList.size)
        assertEquals(5, list.size)
        assertEquals(3, list[0].value)
        assertEquals(4, list[1].value)
        assertEquals(5, list[2].value)
        assertEquals(8, list[3].value)
        assertEquals(9, list[4].value)
    }
}
