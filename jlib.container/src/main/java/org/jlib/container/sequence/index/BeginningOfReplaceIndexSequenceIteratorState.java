package org.jlib.container.sequence.index;

import org.jlib.container.sequence.BeginningOfSequenceIteratorState;
import org.jlib.container.sequence.NoSuchSequenceElementException;
import org.jlib.container.sequence.Sequence;
import org.jlib.container.sequence.SequenceIterator;

/**
 * {@link IndexSequenceIteratorState} when a {@link SequenceIterator} is at the
 * beginning of an {@link IndexSequence}.
 * 
 * @param <Element>
 *        type of the elements held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public abstract class BeginningOfReplaceIndexSequenceIteratorState<Element>
extends BeginningOfSequenceIteratorState<Element>
implements IndexSequenceIteratorState<Element> {

    /** traversed {@link IndexSequence} */
    private final IndexSequence<Element> sequence;

    /**
     * Creates a new {@link BeginningOfReplaceIndexSequenceIteratorState}.
     * 
     * @param sequence
     *        traversed {@link IndexSequence}
     */
    public BeginningOfReplaceIndexSequenceIteratorState(final IndexSequence<Element> sequence) {
        super();

        this.sequence = sequence;
    }

    @Override
    public int getPreviousElementIndex()
    throws NoSuchSequenceElementException {
        throw new NoSuchSequenceElementException(sequence);
    }

    @Override
    public int getNextElementIndex() {
        return sequence.getFirstIndex();
    }

    @Override
    public Element next() {
        return sequence.getFirst();
    }

    @Override
    public IndexSequenceIteratorState<Element> getPreviousState() {
        return this;
    }
}