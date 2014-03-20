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

package org.jlib.container.operation.sequence.index;

import org.jlib.core.observer.ValueObserver;
import org.jlib.core.iterator.NoItemToReplaceException;
import org.jlib.core.iterator.ObservedReplaceIterator;
import org.jlib.core.iterator.ReplaceIterator;
import org.jlib.core.value.ValueNotAccessibleException;

import org.jlib.container.operation.InvalidContainerArgumentException;
import org.jlib.container.operation.InvalidContainerStateException;
import org.jlib.container.operation.sequence.Sequence;

/**
 * Default implementation of a {@link IndexSequenceIterator}
 * @param <Item>
 *        type of items held in the {@link Sequence}
 *
 * @param <Sequenze>
 *        type of the traversed {@link ReplaceIndexSequence}
 *
 * @author Igor Akkerman
 */
public class DefaultReplaceIndexSequenceIterator<Item, Sequenze extends ReplaceIndexSequence<Item>>
extends DefaultIndexSequenceIterator<Item, Sequenze>
implements ObservedReplaceIterator<Item>,
           ReplaceIterator<Item>,
           IndexSequenceIterator<Item> {

    /** replace {@link ValueObserver} items */
//    private final AppendSequence<ValueObserver<Item>> iteratorReplaceObservers = new FillupArraySequence<>();

    /**
     * Creates a new {@link DefaultReplaceIndexSequenceIterator} over the Items
     * of the specified {@link ObservedReplaceIndexSequence} beginning at its
     * first index.
     *
     * @param sequence
     *        {@link ReplaceIndexSequence} to traverse
     */
    public DefaultReplaceIndexSequenceIterator(final Sequenze sequence) {
        super(sequence);
    }

    /**
     * Creates a new {@link DefaultReplaceIndexSequenceIterator} over the Items
     * of the specified {@link ReplaceIndexSequence} beginning the traversal at
     * the specified index.
     *
     * @param sequence
     *        {@link ReplaceIndexSequence} to traverse
     *
     * @param initialNextItemIndex
     *        integer specifying the index of the initial next Item
     *
     * @throws InvalidSequenceIndexException
     *         if {@code startIndex < sequence.getFirstIndex() ||
     *                   startIndex > sequence.getLastIndex()}
     *
     * @throws InvalidContainerArgumentException
     *         if some property of {@code newItem} prevents the operation from
     *         being performed
     *
     * @throws InvalidContainerStateException
     *         if an error occurs performing the operation
     */
    public DefaultReplaceIndexSequenceIterator(final Sequenze sequence, final int initialNextItemIndex)
    throws InvalidSequenceIndexException, InvalidContainerArgumentException, InvalidContainerStateException {
        super(sequence, initialNextItemIndex);
    }

    @Override
    public final void addReplaceObserver(final ValueObserver<Item> replaceObserver) {
//        iteratorReplaceObservers.append(replaceObserver);
    }

    @Override
    public final void replace(final Item newItem)
    throws NoItemToReplaceException, InvalidContainerArgumentException, InvalidContainerStateException {
        try {
            getSequence().replace(getLastAccessedItemIndex(), newItem);
        }
        catch (final ValueNotAccessibleException exception) {
            throw new NoItemToReplaceException(getSequence(), exception);
        }
    }

    @Override
    @SafeVarargs
    public final void replace(final Item newItem, final ValueObserver<Item>... operationObservers)
    throws NoItemToReplaceException, InvalidContainerArgumentException, InvalidContainerStateException,
           RuntimeException {
//        ObserverUtility.operate(new HandledOperator() {
//
//            @Override
//            public void operate()
//            throws OperatorException, RuntimeException {
//                try {
//                    replace(newItem);
//                }
//                catch (InvalidContainerArgumentException | InvalidSequenceIteratorStateException exception) {
//                    throw new OperatorException(message("replace: {0}", newItem), exception);
//                }
//            }
//        }, /*
//     */ newItem, concatenated(iteratorReplaceObservers, iterable(operationObservers)).toArray());
    }
}
