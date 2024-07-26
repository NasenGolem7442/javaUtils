package org.nasengolem.util.datastructures;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;

/**
 * Random-Access {@link List} implementation with a fixed capacity. This list can be used whenever the maximum size of a
 * list is known in advance, making dynamic growth unnecessary.
 *
 * <p> Although the capacity is fixed, the list behaves as if it was dynamic. This means that methods like
 * {@link List#add(Object)} are available, but will throw an {@link IllegalStateException} if the list is full.
 * Adding elements to a full CappedList should therefore usually be avoided.
 *
 * <p> Some use cases for this list are datastructures that are limited
 * due to real-world constraints or datastructures that get (partially) cleared on a regular basis.
 *
 * @author Paul Steinbach
 * @param <E> the type of the elements in this list
 * @see Collection
 * @see AbstractShrinkableList
 * @see ArrayList
 */
public class CappedList<E> extends AbstractShrinkableList<E>
        implements List<E>, RandomAccess, Shrinkable { //TODO: Possibly implement serializable

    private static final String NEGATIVE_CAPACITY_MESSAGE = "Illegal capacity of %d. The capacity must be non-negative.";
    private static final String CAPACITY_TOO_SMALL_MESSAGE = "Illegal capacity of %d for the given collection of size %d."
            + " The capacity must be at least as large as the collection size.";

    private static final String NEGATIVE_RESIZE_MESSAGE = "Illegal newSize of %d. The newSize must be non-negative.";
    private static final String SIZE_TOO_LARGE_MESSAGE = "Illegal newSize of %d for an original size of %d."
            + " The newSize must be at least as small as the original size.";

    private static final String FULL_LIST_MESSAGE = "Can't add the element '%s' to the list, since it is full.";
    private static final String TOO_MANY_ELEMENTS_MESSAGE = "Can't add all elements from the collection to the list, "
            + "since the current size of %d plus the collection size of %d exceed the capacity of %d.";

    private final E[] elements;
    private final int capacity;
    private int size;

    /**
     * Constructs a new {@link CappedList} with the specified capacity.
     *
     * @param capacity the capacity defining the maximum number of elements the list can store
     * @throws IllegalArgumentException if the capacity is negative
     */
    @SuppressWarnings("unchecked")
    public CappedList(int capacity) {
        this.capacity = capacity;
        if (capacity < 0) {
            throw new IllegalArgumentException(String.format(NEGATIVE_CAPACITY_MESSAGE, capacity));
        }
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Copy constructor. Constructs a {@link CappedList} with the same capacity and elements as the
     * given {@link CappedList}.
     *
     * @param cappedList the {@link CappedList} to copy
     * @throws NullPointerException if the cappedList is {@code null}
     */
    public CappedList(CappedList<? extends E> cappedList) {
        this.capacity = cappedList.capacity;
        this.elements = Arrays.copyOf(cappedList.elements, capacity);
        this.size = cappedList.size;
    }

    /**
     * Constructs a {@link CappedList} with the specified capacity. The list contains the elements
     * of the specified collection, in the order they are returned by the collection's iterator.
     *
     * @param collection the collection whose elements are to be placed into this list
     * @param capacity the capacity defining the maximum number of elements the list can store
     * @throws IllegalArgumentException if the capacity is smaller than the collections size
     * @throws NullPointerException if the collection is null
     */
    public CappedList(Collection<? extends E> collection, int capacity) {
        this(capacity);
        if (capacity < collection.size()) {
            throw new IllegalArgumentException(CAPACITY_TOO_SMALL_MESSAGE.formatted(capacity, collection.size()));
        }
        addAll(collection);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public int shrink(int newSize) {
        if (newSize < 0) {
            throw new IllegalArgumentException(NEGATIVE_RESIZE_MESSAGE.formatted(newSize));
        } else if (newSize > size) {
            throw new IllegalArgumentException(SIZE_TOO_LARGE_MESSAGE.formatted(newSize, size));
        }
        return size = newSize;
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elements[index];
    }

    /**
     * {@inheritDoc}
     *
     * @return the number of elements in this list
     */
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

    /**
     * Returns {@code true} if this list is full. This means that no further elements can be added.
     *
     * @return {@code true} if this collection if full
     * This implementation returns {@code size() == capacity}.
     */
    public boolean isFull() {
        return size() == capacity;
    }

    /**
     * Inserts the specified element at the specified position in this list, if the list is not full already. The element
     * currently at that position (if any) and any subsequent elements will then be shifted to the right (adds one to
     * their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegalStateException if the list reached its capacity
     */
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

    /**
     * Inserts all elements of the specified collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException if the specified collection is null
     * @throws IllegalStateException if the list reached its capacity
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        //TODO: Double check if this is better than the default implementation
        Objects.requireNonNull(c);
        Objects.checkIndex(index, size + 1);

        int numNew = c.size();

        if (numNew == 0) {
            return false;
        } else if (size + numNew > capacity) {
            throw new IllegalStateException(TOO_MANY_ELEMENTS_MESSAGE.formatted(size, numNew, capacity));
        }

        System.arraycopy(elements, index,
                elements, index + numNew,
                size - index);

        int i = index;
        for (E e : c) {
            elements[i++] = e;
        }

        size += c.size();
        return true;
    }

    /**
     * Appends all elements of the specified collection to the end of
     * this list, in the order that they are returned by the
     * specified collection's Iterator.
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     * @throws IllegalStateException if the list reached its capacity
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c);

        int numNew = c.size();

        if (numNew == 0) {
            return false;
        } else if (size + numNew > capacity) {
            throw new IllegalStateException(TOO_MANY_ELEMENTS_MESSAGE.formatted(size, numNew, capacity));
        }

        int i = size;
        for (E e : c) {
            elements[i++] = e;
        }

        size += c.size();
        return true;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
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
}