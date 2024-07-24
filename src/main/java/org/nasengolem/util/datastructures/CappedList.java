package org.nasengolem.util.datastructures;

import java.util.*;

/**
 * Random-Access {@code List} implementation, that has a custom capacity and allows
 * custom resizing. Implements all optional list operations and permits all elements, including
 * {@code null}. The main feature of this list is, that it can be resized to a smaller
 * size. After resizing, the Array behaves as if it was of the new size and all
 * elements after the new size are removed. Additionally, the list is capped, meaning that
 * it can never store more elements than its fixed capacity.
 *
 * <p>The {@code resize}, {@code add}, {@code addLast}, {@code size}, {@code isEmpty},
 * {@code get}, {@code set}, {@code getFirst}, {@code getLast}, {@code removeLast},
 * {@code iterator}, {@code listIterator}, and {@code reversed} operations run in
 * constant time. All the operations run in linear time (roughly speaking).
 *
 * <p>Each {@code CappedResizableList} instance has a <i>fixed capacity</i>. The capacity
 * is the maximum number of elements the list can store. However, the list does not
 * need to store that many elements. The actual amount of elements stored in the list
 * is called the <i>size</i> of the list. The size of the list is always less than or
 * equal to the capacity. Unlike the <i>capacity</i>, the <i>size</i> of the list isn't
 * fixed. It changes when elements are added or removed from the list or when the list
 * is resized.
 *
 * <p>Like the {@code ArrayList}, the {@code CappedResizableList} is <strong>not synchronized</strong>
 * and its iterator is fail-fast. Read the documentation of the {@code ArrayList} for more
 * information about these topics.
 *
 * @param <E> the type of the elements in this list
 * @author Paul Steinbach
 * @see Collection
 * @see AbstractList
 * @see ArrayList
 */
public class CappedShrinkableList<E> extends AbstractList<E>
        implements List<E>, Shrinkable, RandomAccess {
    private static final String ILLEGAL_RESIZE_MESSAGE = "Invalid newSize: %d. newSize must be non-negative and less than or equal to the current size: %d. Resize can only decrease the size.";
    private static final String ILLEGAL_CAPACITY_MESSAGE = "Illegal capacity: %d";
    private static final String FULL_LIST_MESSAGE = "Can't add %s to the list, since the list is full.";

    private final E[] elements;
    private final int capacity;
    private int size;

    /**
     * Constructs a new {@code CappedResizableList} with the specified capacity.
     *
     * @param capacity the capacity defining the maximum number of elements the list can store
     * @throws IllegalArgumentException if the capacity is negative
     */
    @SuppressWarnings("unchecked")
    public CappedShrinkableList(int capacity) {
        this.capacity = capacity;
        if (capacity < 0) {
            throw new IllegalArgumentException(String.format(ILLEGAL_CAPACITY_MESSAGE, capacity));
        }
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    public CappedShrinkableList(CappedShrinkableList<? extends E> cappedShrinkableList) {
        this.capacity = cappedShrinkableList.capacity;
        this.elements = Arrays.copyOf(cappedShrinkableList.elements, capacity);
        this.size = cappedShrinkableList.size;
    }

    public CappedShrinkableList(Collection<? extends E> collection, int capacity) {
        this(capacity);
        if (capacity < collection.size()) {
            throw new IllegalArgumentException("The capacity must be greater than or equal to the size of the collection.");
        }
        addAll(collection);
    }

    /**
     * Resizes the list to a new smaller size. All elements at indices beyond
     * the new size won't be visible anymore.
     *
     * @param newSize the new size of the list
     * @throws IllegalArgumentException if the new size is negative or greater than the current size
     */
    public void shrink(int newSize) {
        if (newSize < 0 || newSize > size) {
            throw new IllegalArgumentException(ILLEGAL_RESIZE_MESSAGE.formatted(newSize, size));
        }
        size = newSize;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elements[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        if (size == capacity) {
            throw new IllegalStateException(FULL_LIST_MESSAGE.formatted(element));
        }
        Objects.checkIndex(index, size + 1);
        System.arraycopy(elements, index,
                elements, index + 1,
                size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E removedElement = elements[index];
        System.arraycopy(elements, index + 1,
                elements, index,
                size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return elements[--size];
    }
}