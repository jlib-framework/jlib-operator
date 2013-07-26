package org.jlib.container.itemssupplier;

import java.util.Collection;
import java.util.Iterator;

import org.jlib.core.array.ArrayIterator;
import org.jlib.core.traverser.InvalidTraversableArgumentException;
import org.jlib.core.traverser.InvalidTraversableStateException;
import org.jlib.core.traverser.Traversable;
import org.jlib.core.traverser.TraversableIterator;

import org.jlib.container.ContainsSingle;

public final class ItemsSupplierUtility {

    public static <Item, ContainsSingleTraversable extends ContainsSingle<Item> & Traversable<Item>> /*
               */ ItemsSupplier<Item> allOf(final ContainsSingleTraversable items) {

        return new PreferContainsSingleItemsSupplier<Item>() {

            @Override
            public boolean contains(final Item item)
            throws InvalidTraversableArgumentException, InvalidTraversableStateException {
                return items.contains(item);
            }

            @Override
            public Iterator<Item> iterator() {
                return new TraversableIterator<>(items);
            }
        };
    }

    public static <Item> ItemsSupplier<Item> allOf(final Traversable<Item> items) {

        return new PreferIterateItemsSupplier<Item>() {
            @Override
            public Iterator<Item> iterator() {
                return new TraversableIterator<>(items);
            }
        };
    }

    public static <Item> ItemsSupplier<Item> allOf(final Collection<Item> items) {

        return new PreferContainsSingleItemsSupplier<Item>() {

            @Override
            public boolean contains(final Item item)
            throws InvalidTraversableArgumentException, InvalidTraversableStateException {
                return items.contains(item);
            }

            @Override
            public Iterator<Item> iterator() {
                return items.iterator();
            }
        };
    }

    @SafeVarargs
    public static <Item> ItemsSupplier<Item> allOf(final Item... items) {

        return new PreferIterateItemsSupplier<Item>() {

            @Override
            public Iterator<Item> iterator() {
                return new ArrayIterator<>(items);
            }
        };
    }

    public static <Item> ItemsSupplier<Item> allOf(final Iterable<Item> items) {

        return new PreferIterateItemsSupplier<Item>() {
            @Override
            public Iterator<Item> iterator() {
                return items.iterator();
            }
        };
    }

    private ItemsSupplierUtility() {
    }
}