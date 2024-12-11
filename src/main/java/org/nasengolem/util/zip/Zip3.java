package org.nasengolem.util.zip;

import org.jooq.lambda.tuple.Tuple3;

import java.util.Iterator;

/**
 * The {@code Zip3} class combines three iterables into a single iterable of triples (tuples).
 * It generates a sequence of {@link Tuple3} objects, where each tuple contains one element from
 * each of the three iterables. If the iterables are of different lengths, the resulting sequence
 * stops when the shortest iterable is exhausted.
 *
 * <p>This functionality is inspired by the {@code zip} function in Python.
 * For more information on the Python equivalent, see
 * <a href="https://docs.python.org/3/library/functions.html#zip">Python zip documentation</a>.</p>
 *
 * <p>Since Java does not natively support tuples or pattern matching, this implementation
 * uses tuples from the {@code org.jooq.lambda.tuple} package to fill that gap.</p>
 *
 * @param <V1> the type of elements in the first iterable.
 * @param <V2> the type of elements in the second iterable.
 * @param <V3> the type of elements in the third iterable.
 * @param <I1> the type of the first iterable, which must be an {@code Iterable<V1>}.
 * @param <I2> the type of the second iterable, which must be an {@code Iterable<V2>}.
 * @param <I3> the type of the third iterable, which must be an {@code Iterable<V3>}.
 * @author Paul Steinbach
 */
public class Zip3<V1, V2, V3, I1 extends Iterable<V1>, I2 extends Iterable<V2>, I3 extends Iterable<V3>>
        extends Zip<Tuple3<V1, V2, V3>> {

    private final Iterator<V1> itr1;
    private final Iterator<V2> itr2;
    private final Iterator<V3> itr3;

    /**
     * Constructs a {@code Zip3} instance by initializing iterators for the provided iterables.
     *
     * @param iterable1 the first iterable to zip.
     * @param iterable2 the second iterable to zip.
     * @param iterable3 the third iterable to zip.
     */
    public Zip3(I1 iterable1, I2 iterable2, I3 iterable3) {
        this.itr1 = iterable1.iterator();
        this.itr2 = iterable2.iterator();
        this.itr3 = iterable3.iterator();
    }

    /**
     * Returns {@code true} if all three iterables have more elements to iterate.
     *
     * @return {@code true} if all three iterables have remaining elements, otherwise {@code false}.
     */
    @Override
    public boolean hasNext() {
        return itr1.hasNext() && itr2.hasNext() && itr3.hasNext();
    }

    /**
     * Returns the next {@link Tuple3} of elements from the three iterables.
     *
     * @return the next tuple containing one element from each iterable.
     */
    @Override
    public Tuple3<V1, V2, V3> next() {
        return new Tuple3<>(itr1.next(), itr2.next(), itr3.next());
    }
}
