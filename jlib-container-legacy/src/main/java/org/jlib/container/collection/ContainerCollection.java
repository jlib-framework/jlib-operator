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

package org.jlib.container.collection;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

import org.jlib.container.Container;

import static org.jlib.container.containsadapter.ContainsAdapterUtility.allOf;

/**
 * Adapter allowing a {@link TraversableContainer} to be used as a {@link Collection}. A
 * {@link ContainerCollection} is backed by a {@link TraversableContainer} specified at
 * initialization.
 *
 * @param <Item>
 *        type of the items held in the {@link TraversableContainer}
 *
 * @author Igor Akkerman
 */
public class ContainerCollection<Item>
extends AbstractCollection<Item> {

    /** adapted and backed {@link TraversableContainer} */
    private final Container<Item> delegateContainer;

    /**
     * Creates a new {@link ContainerCollection} backed by the specified
     * {@link TraversableContainer}.
     *
     * @param delegateContainer
     *        {@link TraversableContainer} backing this {@link ContainerCollection}
     */
    public ContainerCollection(final Container<Item> delegateContainer) {
        super();

        this.delegateContainer = delegateContainer;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Iterator<Item> iterator() {
        return delegateContainer.iterator();
    }

    @Override
    public int size() {
        return delegateContainer.getCount();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(final Object object) {
        // (pretty) safe cast thanks to type erasure
        return delegateContainer.contains((Item) object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean containsAll(final Collection<?> items) {
        // (pretty) safe cast thanks to type erasure
        return delegateContainer.contains(allOf((Collection<? extends Item>) items));
    }

    @Override
    public boolean isEmpty() {
        return delegateContainer.isEmpty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Item[] toArray() {
        return (Item[]) super.toArray();
    }

    /**
     * <p>
     * Verifies whether the specified {@link Object} is itself an
     * ContainerCollection and the Containers backed by this TraversableContainer and the
     * specified {@link ContainerCollection}, respectively, are equal by their
     * {@code equals} methods.
     * </p>
     *
     * @param otherObject
     *        Object to which the backed TraversableContainer is compared
     * @return {@code true} if the backed TraversableContainer is equal to
     *         {@code otherObject}; {@code false} otherwise
     */
    @Override
    public boolean equals(final Object otherObject) {
        return otherObject instanceof ContainerCollection<?> && //
               delegateContainer.equals(((ContainerCollection<?>) otherObject).delegateContainer);
    }

    @Override
    public int hashCode() {
        return delegateContainer.hashCode() << 1;
    }
}