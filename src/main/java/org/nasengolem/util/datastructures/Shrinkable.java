package org.nasengolem.util.datastructures;

/**
 * Interface for objects that can be resized to a smaller size in constant time. Classes should only implement this
 * interface if the resize reduces their scope from outside, meaning elements beyond the new size won't be visible
 * anymore.
 *
 * @see     AbstractShrinkableList
 */
@FunctionalInterface
public interface Shrinkable {//TODO: Double-check if scope is the right term here

    /**
     * Shrinks the list to a new smaller size. All elements at indices beyond the new size won't be visible anymore.
     *
     *
     * @param newSize the new size of the list
     * @return the old size of the list
     * @throws IllegalArgumentException if the new size is negative or greater than the current size
     */
    int shrink(int newSize);//TODO: Double-check if I should return the old size
    //TODO: Double-check if IllegalArgumentException is correct here
}
