package org.nasengolem.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nasengolem.util.datastructures.CappedArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CappedArrayListTest {

    private CappedArrayList<Integer> cappedResizableList;

    @BeforeEach
    public void setUp() {
        cappedResizableList = new CappedArrayList<>(3);
    }

    @Test
    public void testAddElementSuccess() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        Assertions.assertEquals(2, cappedResizableList.size(), "The size should be 2 after adding two elements.");
        Assertions.assertEquals(1, cappedResizableList.get(0));
        Assertions.assertEquals(2, cappedResizableList.get(1));
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cappedResizableList.get(2), "Expected an IndexOutOfBoundsException to be thrown when getting an element at an invalid index.");
        Assertions.assertTrue(exception.getMessage().contains("Index"), "Exception message should indicate that the index is invalid.");
    }

    @Test
    public void testAddElementExceedsCapacity() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        cappedResizableList.add(3);
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> cappedResizableList.add(4),
                "Expected an IllegalStateException to be thrown when adding an element to a full list.");
        Assertions.assertTrue(exception.getMessage().contains("full"), "Exception message should indicate that the list is full.");
        Assertions.assertEquals(3, cappedResizableList.size(), "The size should remain 3 after trying to add an element to a full list.");
        Assertions.assertEquals(1, cappedResizableList.get(0));
        Assertions.assertEquals(2, cappedResizableList.get(1));
        Assertions.assertEquals(3, cappedResizableList.get(2));
        Exception exception2 = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cappedResizableList.get(3), "Expected an IndexOutOfBoundsException to be thrown when getting an element at an invalid index.");
        Assertions.assertTrue(exception2.getMessage().contains("Index"), "Exception message should indicate that the index is invalid.");
    }

    @Test
    public void testAddElementInvalidIndex() {
        cappedResizableList.add(1);
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cappedResizableList.add(2, 3),
                "Expected an IndexOutOfBoundsException to be thrown when adding an element at an invalid index.");
        Assertions.assertTrue(exception.getMessage().contains("Index"), "Exception message should indicate that the index is invalid.");
        Assertions.assertTrue(exception.getMessage().contains("2"), "Exception message should indicate the invalid index.");
    }

    @Test
    public void testRemoveElementSuccess() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        cappedResizableList.add(3);
        cappedResizableList.remove(1);
        Assertions.assertEquals(2, cappedResizableList.size(), "The size should be 2 after removing one element.");
        Assertions.assertEquals(1, cappedResizableList.get(0));
        Assertions.assertEquals(3, cappedResizableList.get(1));
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cappedResizableList.get(2), "Expected an IndexOutOfBoundsException to be thrown when getting an element at an invalid index.");
        Assertions.assertTrue(exception.getMessage().contains("Index"), "Exception message should indicate that the index is invalid.");

    }

    @Test
    public void invalidCapacity() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> new CappedArrayList<>(-1),
                "Expected an IllegalArgumentException to be thrown when creating a list with negative capacity.");
        Assertions.assertTrue(exception.getMessage().contains("capacity"), "Exception message should indicate that the capacity is invalid.");
    }

    @Test
    public void invalidIndex() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cappedResizableList.get(2),
                "Expected an IndexOutOfBoundsException to be thrown when getting an element at an invalid index.");
        Assertions.assertTrue(exception.getMessage().contains("Index"), "Exception message should indicate that the index is invalid.");
        Assertions.assertTrue(exception.getMessage().contains("2"), "Exception message should indicate the invalid index.");
    }

    @Test
    public void testSetElementSuccess() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        cappedResizableList.set(1, 3);
        Assertions.assertEquals(2, cappedResizableList.size(), "The size should remain 2 after setting an element.");
        Assertions.assertEquals(1, cappedResizableList.get(0));
        Assertions.assertEquals(3, cappedResizableList.get(1));
    }

    @Test
    public void testSetElementInvalidIndex() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        Exception exception = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cappedResizableList.set(2, 3),
                "Expected an IndexOutOfBoundsException to be thrown when setting an element at an invalid index.");
        Assertions.assertTrue(exception.getMessage().contains("Index"), "Exception message should indicate that the index is invalid.");
        Assertions.assertTrue(exception.getMessage().contains("2"), "Exception message should indicate the invalid index.");
    }

    @Test
    public void testShrinkValid() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        cappedResizableList.add(3);
        cappedResizableList.shrink(1);
        Assertions.assertEquals(1, cappedResizableList.size(), "The size should be 1 after resizing the list.");
        Assertions.assertEquals(1, cappedResizableList.get(0));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cappedResizableList.get(1),
                "Index should now be invalid since the list was resized.");
    }

    @Test
    public void testShrinkInvalid() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        cappedResizableList.add(3);
        Assertions.assertDoesNotThrow(() -> cappedResizableList.shrink(3),
                "Expected no exception to be thrown when resizing the list to the same size.");
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> cappedResizableList.shrink(4),
                "Expected an IllegalArgumentException to be thrown when resizing the list to a larger size.");
        Assertions.assertTrue(exception.getMessage().contains("size"), "Exception message should indicate that the size is invalid.");
        Assertions.assertThrows(IllegalArgumentException.class, () -> cappedResizableList.shrink(-1),
                "Expected an IndexOutOfBoundsException to be thrown when resizing the list to a negative size.");
    }

    @Test
    public void testEquals() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        List<Integer> arrList = new ArrayList<>(List.of(1, 2));
        Assertions.assertEquals(arrList, cappedResizableList, "Lists of the same elements should be equal.");
        cappedResizableList.shrink(1);
        Assertions.assertNotEquals(arrList, cappedResizableList, "After the resize, the cappedLists former elements should not be used for comparison.");
        Assertions.assertEquals(cappedResizableList, arrList.subList(0,1), "The sublist should be equal to the original list.");
        cappedResizableList.clear();
        Assertions.assertNotEquals(arrList, cappedResizableList, "An empty list should not be equal to a non-empty list.");
        Assertions.assertThrows(NoSuchElementException.class, () -> cappedResizableList.getFirst(),
                "Expected an IllegalArgumentException to be thrown when getting an element from an empty list.");
        arrList.clear();
        Assertions.assertEquals(arrList, cappedResizableList, "Two empty lists should be equal.");
    }

    @Test
    public void testToArray() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        Object[] arr = cappedResizableList.toArray();
        Assertions.assertEquals(2, arr.length, "The array should have the same length as the list.");
        Assertions.assertEquals(1, arr[0], "The first element should be 1.");
        Assertions.assertEquals(2, arr[1], "The second element should be 2.");
    }

    @Test
    public void testRemoveLast() {
        cappedResizableList.add(1);
        cappedResizableList.add(2);
        cappedResizableList.add(3);
        cappedResizableList.removeLast();
        Assertions.assertEquals(2, cappedResizableList.size(), "The size should be 2 after removing the last element.");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> cappedResizableList.get(2), "Expected an IndexOutOfBoundsException to be thrown when getting an element at an invalid index.");
        Assertions.assertDoesNotThrow(() -> cappedResizableList.removeLast());
        Assertions.assertDoesNotThrow(() -> cappedResizableList.removeLast());
        Assertions.assertThrows(NoSuchElementException.class, () -> cappedResizableList.removeLast(), "Expected a NoSuchElementException to be thrown when removing the last element from an empty list.");
    }
}
