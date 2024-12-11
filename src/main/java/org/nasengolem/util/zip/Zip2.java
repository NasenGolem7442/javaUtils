package org.nasengolem.util.zip;

import org.jooq.lambda.tuple.Tuple2;

import java.util.Iterator;

/**
 * The {@code Zip2} class combines two iterables into a single iterable of pairs (tuples).
 * It generates a sequence of {@link Tuple2} objects, where each tuple contains one element from the
 * first iterable and one element from the second iterable. If the iterables are of different lengths,
 * the resulting sequence stops when the shorter iterable is exhausted.
 *
 * <p>This functionality is inspired by the {@code zip} function in Python.
 * For more information on the Python equivalent,
 * see <a href="https://docs.python.org/3/library/functions.html#zip">Python zip documentation</a>.</p>
 *
 * <p>Since Java does not natively support tuples or pattern matching, this implementation
 * uses tuples from the {@code org.jooq.lambda.tuple} package to fill that gap.</p>
 *
 * @param <V1> the type of elements in the first iterable.
 * @param <V2> the type of elements in the second iterable.
 * @param <I1> the type of the first iterable, which must be an {@code Iterable<V1>}.
 * @param <I2> the type of the second iterable, which must be an {@code Iterable<V2>}.
 * @author Paul Steinbach
 */
public class Zip2<V1, V2, I1 extends Iterable<V1>, I2 extends Iterable<V2>>
        extends Zip<Tuple2<V1, V2>> {

    private final Iterator<V1> itr1;
    private final Iterator<V2> itr2;

    /**
     * Constructs a {@code Zip2} instance by initializing iterators for the provided iterables.
     *
     * @param iterable1 the first iterable to zip.
     * @param iterable2 the second iterable to zip.
     */
    public Zip2(I1 iterable1, I2 iterable2) {
        this.itr1 = iterable1.iterator();
        this.itr2 = iterable2.iterator();
    }

    /**
     * Returns {@code true} if both iterables have more elements to iterate.
     *
     * @return {@code true} if both iterables have remaining elements, otherwise {@code false}.
     */
    @Override
    public boolean hasNext() {
        return itr1.hasNext() && itr2.hasNext();
    }

    /**
     * Returns the next {@link Tuple2} of elements from the two iterables.
     *
     * @return the next tuple containing one element from each iterable.
     */
    @Override
    public Tuple2<V1, V2> next() {
        return new Tuple2<>(itr1.next(), itr2.next());
    }
}
