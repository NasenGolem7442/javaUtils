package org.nasengolem.util.datastructures;

import java.util.AbstractList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * This class extends the {@link AbstractList} skeleton by implementing^ {@link Shrinkable}.
 * This means, that the end of the list can be cut off in constant time. The following idioms utilize the
 * {@link #shrink(int)} method to increase performance when deleting the last elements of the list.
 *
 * <pre>
 *      list.removeLast(numberOfElements);
 *      list.subList(from, size()).clear();
 *      list.clear();
 * </pre>
 *
 * @author Paul Steinbach
 * @param <E> the type of elements in this list
 */
public abstract class AbstractShrinkableList<E> extends AbstractList<E>
        implements List<E>, Shrinkable {
    private static final String NOT_ENOUGH_ELEMENTS_MESSAGE = "Can't remove %d elements from a list of size %d.";

    protected AbstractShrinkableList() {
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public abstract int shrink(int newSize);

    /**
     * Removes and returns the last element of the list.
     *
     * @return the removed element
     * @throws NoSuchElementException -
     */
    @Override
    public E removeLast() {
        E last = getLast();
        removeLast(1);
        return last;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        if (toIndex == size()) {
            Objects.checkIndex(fromIndex, size());
            shrink(fromIndex);
        } else {
            super.removeRange(fromIndex, toIndex);
        }
    }

    /**
     * Removes the specified amount of elements from the end of the list.
     *
     * @param numberOfElements the number of elements to remove
     * @throws IllegalStateException if the list does not contain enough elements
     */
    public void removeLast(int numberOfElements) {
        if (numberOfElements > size()) {
            throw new IllegalStateException(NOT_ENOUGH_ELEMENTS_MESSAGE.formatted(numberOfElements, size()));
            //TODO: Double-check if IllegalStateException is correct here
        }
        shrink(size() - numberOfElements);
    }
}
