/*
 * jlib - The Free Java Library
 *
 *    http://www.jlib.org
 *
 * Copyright (c) 2006-2008 Igor Akkerman
 *
 * jlib is distributed under the
 *
 *    COMMON PUBLIC LICENSE VERSION 1.0
 *
 *    http://www.opensource.org/licenses/cpl1.0.php
 */

package org.jlib.container.sequence;

import org.jlib.container.sequence.index.ObservedReplaceIndexSequenceTraverser;
import org.jlib.core.observer.ValueObserver;

/**
 * {@link ReplaceSequence} allowing its replace operations to be attended by
 * {@link ValueObserver} instances.
 * 
 * @param <Item>
 *        type of items held in the {@link Sequence}
 * 
 * @author Igor Akkerman
 */
public interface ObservedReplaceSequence<Item>
extends ReplaceSequence<Item> {

    /**
     * Returns an {@link ObservedReplaceIndexSequenceTraverser} traversing the
     * Items of this {@link ObservedReplaceSequence} in proper order.
     * 
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the replacement
     * 
     * @return {@link ObservedReplaceIndexSequenceTraverser} traversing the
     *         Items of this {@link ObservedReplaceSequence} in proper order
     */
    public ObservedReplaceSequenceTraverser<Item> createReplaceSequenceTraverser(@SuppressWarnings({ "unchecked", /* "varargs" */}) ValueObserver<Item>... observers);
}