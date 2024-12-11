package org.nasengolem.util.zip;

import org.jooq.lambda.tuple.Tuple;

import java.util.Iterator;


/**
 * Abstract base class for implementing zipping functionality over multiple iterables.
 * This class implements both {@link Iterator} and {@link Iterable}, allowing instances to be used
 * in enhanced for-loops and to provide their own iteration behavior.
 *
 * <p>The term "zipping" refers to combining multiple iterables into a single iterable of tuples.
 * Since Java does not natively support tuples or pattern matching, this implementation uses tuples
 * from the {@code org.jooq.lambda.tuple} package to fill that gap.
 * This is inspired by the {@code zip} function in Python.
 * For more information on the Python equivalent, see <a href="https://docs.python.org/3/library/functions.html#zip">Python zip documentation</a>.</p>
 *
 * @param <T> the type of elements returned by this iterator.
 * @author Paul Steinbach
 */
public abstract class Zip<T extends Tuple> implements Iterator<T>, Iterable<T> {

    @Override
    public Iterator<T> iterator() {
        return this;
    }
}
