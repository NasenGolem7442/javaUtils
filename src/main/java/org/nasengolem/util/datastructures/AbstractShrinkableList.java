package org.nasengolem.util.datastructures;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/**
 * Interface for objects that can be resized to a smaller size. Note that this interface should
 * only be implemented
 *
 */
public abstract class AbstractShrinkableList<E> extends AbstractList<E>
        implements List<E>, RandomAccess {
    abstract void shrink(int newSize);

}
