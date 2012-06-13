package org.jlib.container.sequence;

import org.jlib.container.EmptyContainer;
import org.jlib.container.sequence.index.ReplaceIndexSequence;
import org.jlib.core.observer.ValueObserver;
import org.jlib.core.traverser.BidirectionalTraverser;
import org.jlib.core.traverser.ReplaceTraverser;

/**
 * Empty {@link Sequence}.
 * 
 * @param <Item>
 *        type of the items
 * 
 * @author Igor Akkerman
 */
public class EmptySequence<Item>
extends EmptyContainer<Item>
implements ObservedReplaceSequence<Item> {

    /** sole instance of this class */
    private static final EmptySequence<?> INSTANCE = new EmptySequence<>();

    /**
     * Returns the sole instance of this class.
     * 
     * @param <Item>
     *        type of potential items potentially held in this
     *        {@link EmptySequence}
     * 
     * @return sole {@link ReplaceIndexSequence}
     */
    @SuppressWarnings("unchecked")
    public static <Item> EmptySequence<Item> getInstance() {
        return (EmptySequence<Item>) INSTANCE;
    }

    /**
     * Creates a new {@link EmptySequence}.
     */
    protected EmptySequence() {
        super();
    }

    @Override
    public EmptySequenceTraverser<Item> createSequenceTraverser() {
        return EmptySequenceTraverser.getInstance();
    }

    @Override
    public BidirectionalTraverser<Item> createBidirectionalTraverser() {
        return createSequenceTraverser();
    }

    @Override
    public ReplaceTraverser<Item> createReplaceTraverser() {
        return createSequenceTraverser();
    }

    @Override
    public ObservedReplaceSequenceTraverser<Item> createReplaceSequenceTraverser() {
        return createSequenceTraverser();
    }

    @Override
    @SuppressWarnings("unchecked")
    public ObservedReplaceSequenceTraverser<Item> createReplaceSequenceTraverser(final ValueObserver<Item>... observers) {
        return createSequenceTraverser();
    }

    // equals/hashCode don't need to be extended as Object.equals already checks for identity
}
