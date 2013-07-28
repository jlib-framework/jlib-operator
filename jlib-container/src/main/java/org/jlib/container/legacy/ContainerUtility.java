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

package org.jlib.container.legacy;

import java.util.Collection;
import java.util.Set;

import org.jlib.core.array.ArrayUtility;
import org.jlib.core.observer.ObserverUtility;
import org.jlib.core.observer.ValueObserver;
import org.jlib.core.observer.ValueObserverException;
import org.jlib.core.operator.HandledOperator;
import org.jlib.core.operator.OperatorException;

import org.jlib.container.InvalidContainerArgumentException;
import org.jlib.container.InvalidContainerStateException;

import org.jlib.core.traverser.NoItemToRemoveException;
import org.jlib.core.traverser.NoNextItemException;
import org.jlib.core.traverser.ObservedRemoveTraverser;
import org.jlib.core.traverser.RemoveTraversable;
import org.jlib.core.traverser.RemoveTraverser;

import org.jlib.container.ObservedRemoveMany;
import org.jlib.container.ItemToRemoveNotContainedException;

import static java.util.Arrays.asList;
import static org.jlib.container.collection.CollectionUtility.toSet;
import static org.jlib.core.language.ExceptionMessageUtility.message;

/**
 * Utility class providing methods operating on {@link TraversableContainer} instances.
 *
 * @author Igor Akkerman
 */
public final class ContainerUtility {

    /** No visible constructor. */
    private ContainerUtility() {
    }

    /**
     * Removes the specified Item of this {@link RetainItemsByTraversable}.
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param container
     *        {@link ObservedRetainItemsByTraversable} containing the Item
     *
     * @param item
     *        {@link Item} to retain
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the removal
     *
     * @throws ItemToRemoveNotContainedException
     *         if this {@link RetainItemsByTraversable} does not contain {@code Item}
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     *
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */
    @SafeVarargs
    // TODO: check declared exceptions
    @SuppressWarnings({ "DuplicateThrows", "ProhibitedExceptionDeclared" })
    public static <Item> void remove(final RemoveItemByItem<Item> container, final Item item,
                                     final ValueObserver<Item>... observers)
    throws ItemToRemoveNotContainedException, InvalidContainerArgumentException, InvalidContainerStateException,
           RuntimeException {

        ObserverUtility.operate(new HandledOperator() {

            @Override
            @SuppressWarnings("OverlyBroadCatchBlock")
            public void operate()
            throws OperatorException, RuntimeException {
                try {
                    container.removeItem(item);
                }
                // TODO: check caught exceptions
                catch (InvalidContainerArgumentException | InvalidContainerStateException exception) {
                    throw new OperatorException(message("retain: {0}", item), exception);
                }
            }
        }, item, observers);
    }

    /**
     * Removes all Items provided by the specified {@link Iterable} from the
     * specified {@link RetainItemsByTraversable}.
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param container
     *        {@link ObservedRetainItemsByTraversable} containing the Items
     *
     * @param items
     *        {@link Iterable} providing the Items to retain
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     */
    public static <Item> void remove(final RemoveItemByItem<Item> container, final Iterable<? extends Item> items)
    throws InvalidContainerArgumentException, InvalidContainerStateException {
        for (final Item item : items)
            container.removeItem(item);
    }

    /**
     * Removes all Items in the specified comma separated sequence from the
     * specified {@link RetainItemsByTraversable}.
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param container
     *        {@link RetainItemsByTraversable} containing the Items
     *
     * @param items
     *        {@link Iterable} providing the Items to retain
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     *
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */
    @SafeVarargs
    @SuppressWarnings("DuplicateThrows")
    public static <Item> void remove(final RemoveItemByItem<Item> container, final Item... items)
    throws InvalidContainerArgumentException, InvalidContainerStateException, RuntimeException {
        remove(container, ArrayUtility.iterable(items));
    }

    /**
     * Removes all Items provided by the specified {@link Iterable} from the
     * specified {@link ObservedRetainItemsByTraversable}.
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param container
     *        {@link ObservedRetainItemsByTraversable} containing the Items
     *
     * @param items
     *        {@link Iterable} providing the Items to retain
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the removal
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     *
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */
    @SafeVarargs
    @SuppressWarnings("DuplicateThrows")
    public static <Item> void remove(final ObservedRemoveMany<Item> container, final Iterable<? extends Item> items,
                                     final ValueObserver<Item>... observers)
    throws InvalidContainerArgumentException, InvalidContainerStateException, RuntimeException {
        for (final Item item : items)
            container.remove(item, observers);
    }

    /**
     * Removes all Items in the specified comma separated sequence from the
     * specified {@link RetainItemsByTraversable}.
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param container
     *        {@link RetainItemsByTraversable} containing the Items
     *
     * @param observers
     *        array of {@link ValueObserver} instances attending the removal
     *
     * @param items
     *        {@link Iterable} providing the Items to retain
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     *
     * @throws ValueObserverException
     *         if an error occurs during the {@link ValueObserver} operation
     */

    @SafeVarargs
    public static <Item> void remove(final ObservedRemoveMany<Item> container, final ValueObserver<Item>[] observers,
                                     final Item... items)
    throws InvalidContainerArgumentException, InvalidContainerStateException, ValueObserverException {
        remove(container, ArrayUtility.iterable(items), observers);
    }

    /**
     * Removes all Items from the specified {@link RemoveTraversable} <em>except</em> the Items provided by the
     * specified {@link Iterable}.
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param traversable
     *        {@link RetainItemsByTraversable} containing the Items to retain
     *
     * @param items
     *        {@link Iterable} providing the Items to remove
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of one {@link Item} in {@code items}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     */
    public static <Item> void retain(final RemoveTraversable<Item> traversable, final Iterable<? extends Item> items)
    throws InvalidContainerArgumentException, InvalidContainerStateException {
        final Set<Item> retainedItemsSet = toSet(items);

        final RemoveTraverser<Item> containerTraverser = traversable.createTraverser();
        while (containerTraverser.hasNextItem())
            if (! retainedItemsSet.contains(containerTraverser.getNextItem()))
                containerTraverser.remove();
    }

    /**
     * Removes all Items from the specified {@link RetainItemsByTraversable}
     * <em>except</em> for the Items contained by the specified
     * {@link Collection} .
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param <RetainedItem>
     *        type of the items retained in the {@link TraversableContainer}
     *
     * @param container
     *        {@link RetainItemsByTraversable} containing the Items to retain
     *
     * @param items
     *        {@link Collection} containing the Items to remove
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     */
    @SafeVarargs
    public static <Item, RetainedItem extends Item> void retain(final RetainItemsByTraversable<Item> container,
                                                                final RetainedItem... items)
    throws InvalidContainerArgumentException, InvalidContainerStateException {
//        remove(container, toSet(items));
    }

    /**
     * Removes all Items from the specified {@link RetainItemsByTraversable}
     * <em>except</em> for the Items contained by the specified
     * {@link Collection} .
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param container
     *        {@link RetainItemsByTraversable} containing the Items to retain
     *
     * @param items
     *        {@link Collection} containing the Items to remove
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     */
    public static <Item> void retain(final RetainItemsByTraversable<Item> container,
                                     final Collection<? extends Item> items)
    throws InvalidContainerArgumentException, InvalidContainerStateException {
        final RemoveTraverser<Item> itemsTraverser = null; // container.createTraverser();
        while (itemsTraverser.hasNextItem())
            if (! items.contains(itemsTraverser.getNextItem()))
                itemsTraverser.remove();
    }

    /**
     * Removes all Items from the specified {@link ObservedRetainItemsByTraversable}
     * <em>except</em> the Items provided by the specified {@link Iterable}.
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param container
     *        {@link ObservedRetainItemsByTraversable} containing the Items to retain
     *
     * @param items
     *        {@link Iterable} providing the Items to remove
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the removal
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     *
     * @throws ValueObserverException
     *         if an error occurs during the {@link ValueObserver} operation
     */
    @SafeVarargs
    public static <Item> void retain(final ObservedRetainItemsByTraversable<Item> container,
                                     final Iterable<? extends Item> items, final ValueObserver<Item>... observers)
    throws InvalidContainerArgumentException, InvalidContainerStateException, ValueObserverException {

        final Set<Item> retainedItemsSet = toSet(items);

        final ObservedRemoveTraverser<Item> containerTraverser = null; // container.createTraverser();

        for (final ValueObserver<Item> observer : observers)
            containerTraverser.addRemoveObserver(observer);

        while (containerTraverser.hasNextItem())
            if (! retainedItemsSet.contains(containerTraverser.getNextItem()))
                containerTraverser.remove(observers);
    }

    /**
     * Removes all Items from the specified {@link ObservedRetainItemsByTraversable}
     * <em>except</em> for the Items contained by the specified
     * {@link Collection} .
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param <RetainedItem>
     *        type of the items retained in the {@link TraversableContainer}
     *
     * @param container
     *        {@link ObservedRetainItemsByTraversable} containing the Items to retain
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the removal
     *
     * @param items
     *        comma separated sequence of the Items to remove
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     *
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */

    @SafeVarargs
    @SuppressWarnings("DuplicateThrows")
    public static <Item, RetainedItem extends Item> void retain(final ObservedRetainItemsByTraversable<Item> container,
                                                                final ValueObserver<Item>[] observers,
                                                                final RetainedItem... items)
    throws InvalidContainerArgumentException, InvalidContainerStateException, RuntimeException {
        retain(container, asList(items), observers);
    }

    /**
     * Removes all Items from the specified {@link ObservedRetainItemsByTraversable}
     * <em>except</em> for the Items contained by the specified
     * {@link Collection} .
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param container
     *        {@link ObservedRetainItemsByTraversable} containing the Items to retain
     *
     * @param items
     *        {@link Collection} containing the Items to remove
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the removal
     *
     * @throws InvalidContainerArgumentException
     *         if the operation cannot be completed due to some property of
     *         {@code item}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     *
     * @throws RuntimeException
     *         if a {@link ValueObserver} operation throws this
     *         {@link RuntimeException}
     */
    // TODO: check declared exceptions
    @SafeVarargs
    @SuppressWarnings({ "DuplicateThrows", "ProhibitedExceptionDeclared", "OverlyBroadThrowsClause" })
    public static <Item> void retain(final ObservedRetainItemsByTraversable<Item> container,
                                     final Collection<? extends Item> items, final ValueObserver<Item>... observers)
    throws NoItemToRemoveException, InvalidContainerArgumentException, InvalidContainerStateException,
           RuntimeException {

        final ObservedRemoveTraverser<Item> itemsTraverser = null; // container.createTraverser();

        for (final ValueObserver<Item> observer : observers)
            itemsTraverser.addRemoveObserver(observer);

        while (itemsTraverser.hasNextItem())
            if (! items.contains(itemsTraverser.getNextItem()))
                itemsTraverser.remove();
    }

    /**
     * Removes all Items of the specified {@link RetainItemsByTraversable}.
     *
     * @param traversable
     *        {@link RetainItemsByTraversable} containing the Items
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     */
    public static <Item> void removeAll(final RemoveTraversable<Item> traversable)
    throws InvalidContainerStateException, NoItemToRemoveException, NoNextItemException {
        for (final RemoveTraverser<Item> traverser = traversable.createTraverser(); traverser.hasNextItem(); ) {
            traverser.getNextItem();
            traverser.remove();
        }
    }

    /**
     * Removes all Items of the specified {@link RetainItemsByTraversable}.
     *
     * @param container
     *        {@link ObservedRetainItemsByTraversable} containing the Items
     *
     * @param <Item>
     *        type of the items held in the {@link TraversableContainer}
     *
     * @param observers
     *        comma separated sequence of {@link ValueObserver} instances
     *        attending the removal
     *
     * @throws InvalidContainerStateException
     *         if an error occurs during the operation
     */
    @SafeVarargs
    public static <Item> void removeAll(final ObservedRetainItemsByTraversable<Item> container,
                                        final ValueObserver<Item>... observers)
    throws InvalidContainerStateException {
        for (final ObservedRemoveTraverser<Item> traverser = null; // container.createTraverser();
             traverser.hasNextItem(); ) {
            traverser.getNextItem();
            traverser.remove(observers);
        }
    }

    /**
     * Returns the sum of number of Items in all of the specified
     * {@link TraversableContainer} instances.
     *
     * @param containers
     *        array of {@link Iterable} instances providing the Items
     *
     * @return integer specifying the total number of Items
     */
    public static int getItemsCount(final TraversableContainer<?>[] containers) {
        int itemsCount = 0;

        for (final TraversableContainer<?> container : containers)
            itemsCount += container.getItemsCount();

        return itemsCount;
    }
}
