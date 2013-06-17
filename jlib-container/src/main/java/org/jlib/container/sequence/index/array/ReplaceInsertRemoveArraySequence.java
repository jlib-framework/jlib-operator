/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2013 Igor Akkerman
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.jlib.container.sequence.index.array;

import org.jlib.container.Container;
import org.jlib.container.ContainerUtility;
import org.jlib.container.sequence.InvalidSequenceArgumentException;
import org.jlib.container.sequence.InvalidSequenceStateException;
import org.jlib.container.sequence.InvalidSequenceItemsCountException;
import org.jlib.container.sequence.Sequence;
import org.jlib.container.sequence.index.DefaultReplaceInsertRemoveIndexSequenceTraverser;
import org.jlib.container.sequence.index.IndexSequenceUtility;
import org.jlib.container.sequence.index.InvalidSequenceIndexRangeException;
import org.jlib.container.sequence.index.ObservedReplaceInsertRemoveIndexSequence;
import org.jlib.container.sequence.index.ObservedReplaceInsertRemoveIndexSequenceTraverser;
import org.jlib.container.sequence.index.SequenceIndexOutOfBoundsException;
import org.jlib.container.sequence.index.SubReplaceInsertRemoveIndexSequence;
import org.jlib.core.observer.ValueObserver;
import org.jlib.core.exception.observer.ValueObserverException;

import java.util.Collection;

/**
 * {@link ReplaceInsertArraySequence} to which Items can be added.
 *
 * @param <Item>
 *        type of items held in the {@link Sequence}
 *
 * @author Igor Akkerman
 */
public class ReplaceInsertRemoveArraySequence<Item>
extends ReplaceInsertRemoveFirstLastArraySequence<Item>
implements ObservedReplaceAppendPrependInsertRemoveIndexSequence<Item> {

    /**
     * Creates a new uninitialized {@link ReplaceInsertRemoveArraySequence} with
     * the specified first and last indices.
     *
     * @param firstIndex
     *        integer specifying the initial first index
     *
     * @param lastIndex
     *        integer specifying the initial last index
     *
     * @throws InvalidSequenceIndexRangeException
     *         if {@code lastIndex < firstIndex}
     */
    protected ReplaceInsertRemoveArraySequence(final int firstIndex, final int lastIndex)
    throws InvalidSequenceIndexRangeException {
        super(firstIndex, lastIndex);
    }

    /**
     * Creates a new {@link ReplaceInsertRemoveArraySequence} with a first index
     * of {@code 0} and the specified number of Items.
     *
     * @param itemsCount
     *        integer specifying the initial number of Items
     *
     * @throws InvalidSequenceItemsCountException
     *         if {@code itemsCount < 1}
     */
    protected ReplaceInsertRemoveArraySequence(final int itemsCount)
    throws InvalidSequenceItemsCountException {
        super(itemsCount);
    }

    /**
     * Creates a new {@link ReplaceInsertRemoveArraySequence} with a first index
     * of {@code 0} containing the specified Items.
     *
     * @param items
     *        comma separated sequence of Items to store
     */
    @SafeVarargs
    public ReplaceInsertRemoveArraySequence(final Item... items) {
        super(items);
    }

    /**
     * Creates a new {@link ReplaceInsertRemoveArraySequence} with the specified
     * first index containing the specified Items.
     *
     * @param firstIndex
     *        integer specifying the first index
     *
     * @param items
     *        comma separated sequence of Items to store
     */
    @SafeVarargs
    public ReplaceInsertRemoveArraySequence(final int firstIndex, final Item... items) {
        super(firstIndex, items);
    }

    /**
     * Creates a new {@link ReplaceInsertRemoveArraySequence} with a first index
     * of {@code 0} containing the specified Items.
     *
     * @param items
     *        {@link Collection} of Items to store
     */
    public ReplaceInsertRemoveArraySequence(final Collection<? extends Item> items) {
        super(items);
    }

    /**
     * Creates a new {@link ReplaceInsertRemoveArraySequence} with the specified
     * first index containing the specified Items.
     *
     * @param firstIndex
     *        integer specifying the first index
     *
     * @param items
     *        {@link Collection} of Items to store
     */
    public ReplaceInsertRemoveArraySequence(final int firstIndex, final Collection<? extends Item> items) {
        super(firstIndex, items);
    }

    /**
     * Creates a new {@link ReplaceInsertRemoveArraySequence} with a first index
     * of {@code 0} containing the specified Items.
     *
     * @param items
     *        {@link Container} of Items to store
     */
    public ReplaceInsertRemoveArraySequence(final Container<? extends Item> items) {
        super(items);
    }

    /**
     * Creates a new {@link ReplaceInsertRemoveArraySequence} with the specified
     * first index containing the specified Items.
     *
     * @param firstIndex
     *        integer specifying the first index
     *
     * @param items
     *        {@link Sequence} of Items to store
     */
    public ReplaceInsertRemoveArraySequence(final int firstIndex, final Sequence<? extends Item> items) {
        super(firstIndex, items);
    }

    @Override
    public void remove(final int index) {
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(final int index, final ValueObserver<Item>... observers) {
        IndexSequenceUtility.remove(this, index, observers);
    }

    @Override
    public void retain(final Container<? extends Item> items)
    throws InvalidSequenceArgumentException, InvalidSequenceStateException {
        ContainerUtility.retain(this, items);
    }

    @Override
    public void retain(final Collection<? extends Item> items)
    throws InvalidSequenceArgumentException, InvalidSequenceStateException {
        ContainerUtility.retain(this, items);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void retain(final Item... items)
    throws InvalidSequenceArgumentException, InvalidSequenceStateException {
        ContainerUtility.retain(this, items);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void retain(final Container<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidSequenceArgumentException, InvalidSequenceStateException, ValueObserverException {
        ContainerUtility.retain(this, items, observers);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void retain(final Collection<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidSequenceArgumentException, InvalidSequenceStateException, ValueObserverException {
        ContainerUtility.retain(this, items, observers);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void retain(final ValueObserver<Item>[] observers, final Item... items)
    throws InvalidSequenceArgumentException, InvalidSequenceStateException, ValueObserverException {
        ContainerUtility.retain(this, observers, items);
    }

    @Override
    public ObservedReplaceInsertRemoveIndexSequence<Item> getSubsequenceView(final int fromIndex, final int toIndex)
    throws SequenceIndexOutOfBoundsException, InvalidSequenceIndexRangeException {
        return new SubReplaceInsertRemoveIndexSequence<>(this, fromIndex, toIndex);
    }

    @Override
    public ObservedReplaceInsertRemoveIndexSequenceTraverser<Item> createTraverser() {
        return new DefaultReplaceInsertRemoveIndexSequenceTraverser<>(this);
    }

    @Override
    public ObservedReplaceInsertRemoveIndexSequenceTraverser<Item> createTraverser(final int startIndex) {
        return new DefaultReplaceInsertRemoveIndexSequenceTraverser<>(this, startIndex);
    }
}
