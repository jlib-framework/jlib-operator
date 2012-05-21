package org.jlib.container.sequence.array;

import org.jlib.container.sequence.Sequence;
import org.jlib.container.sequence.index.IndexSequenceCreator;
import org.jlib.container.sequence.index.SequenceIndexOutOfBoundsException;
import org.jlib.container.sequence.replace.DefaultReplaceIndexSequenceIterator;
import org.jlib.container.sequence.replace.ReplaceIndexSequence;
import org.jlib.container.sequence.replace.ReplaceIndexSequenceIterator;

/**
 * {@link ArraySequence} allowing its Elements to be replaced.
 * 
 * @param <Element>
 *        type of the elements of the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public class ReplaceArraySequence<Element>
extends ArraySequence<Element>
implements ReplaceIndexSequence<Element> {

    /** {@link IndexSequenceCreator} of {@link ReplaceArraySequence} instances */
    public static final IndexSequenceCreator<? extends ReplaceArraySequence<?>> CREATOR =
        new IndexSequenceCreator<ReplaceArraySequence<?>>() {

            @Override
            public ReplaceArraySequence<?> createSequence(final int firstIndex, final int lastIndex)
            throws IllegalArgumentException {
                return new ReplaceArraySequence<Object>(firstIndex, lastIndex);
            }
        };

    /**
     * Returns the {@link IndexSequenceCreator} of {@link ReplaceArraySequence}
     * instances.
     * 
     * @return {@link IndexSequenceCreator} of {@link ReplaceArraySequence}
     *         instances
     */
    @SuppressWarnings("unchecked")
    public static <Element> IndexSequenceCreator<? extends ReplaceArraySequence<Element>> getCreator() {
        return (IndexSequenceCreator<ReplaceArraySequence<Element>>) CREATOR;
    }

    /**
     * Creates a new {@link ReplaceArraySequence}.
     * 
     * @param firstIndex
     *        first index of this {@link ReplaceArraySequence}
     * 
     * @param lastIndex
     *        last index of this {@link ReplaceArraySequence}
     */
    protected ReplaceArraySequence(final int firstIndex, final int lastIndex) {
        super(firstIndex, lastIndex);
    }

    @Override
    // raising visibility from protected to public
    public void replace(final int index, final Element element)
    throws SequenceIndexOutOfBoundsException {
        super.replace(index, element);
    }

    @Override
    public ReplaceIndexSequenceIterator<Element> createIterator() {
        return new DefaultReplaceIndexSequenceIterator<>(this);
    }

    @Override
    public ReplaceIndexSequenceIterator<Element> createIterator(final int startIndex)
    throws SequenceIndexOutOfBoundsException {
        return new DefaultReplaceIndexSequenceIterator<>(this, startIndex);
    }
}
