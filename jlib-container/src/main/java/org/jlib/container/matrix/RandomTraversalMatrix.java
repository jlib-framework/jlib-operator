package org.jlib.container.matrix;

import java.util.Iterator;
import java.util.RandomAccess;

import org.jlib.container.sequence.Sequence;
import org.jlib.core.traverser.Traverser;

/**
 * {@link Matrix} traversable in various orders. A {@link Sequence} of all
 * columns or rows can be retrieved and the {@link Matrix} can be traversed by a
 * custom {@link MatrixTraverser}. The default {@link MatrixTraverser} returned
 * by {@link #createTraverser()} and the default {@link Iterator} returned by
 * {@link #iterator()} can be customized using
 * {@link #setDefaultTraversalOrder(MatrixTraversalOrder)}. A
 * {@link RandomTraversalMatrix} is not necessarily a {@link RandomAccess}
 * {@link Matrix}.
 * 
 * @param <Entry>
 *        type of the entries of the Matrix
 * 
 * @see MatrixTraversalOrder
 * 
 * @author Igor Akkerman
 */
public interface RandomTraversalMatrix<Entry>
extends Matrix<Entry> {

    /**
     * Returns the {@link Sequence} of the rows of this {@link Matrix}, each
     * provided as a {@link Sequence}.
     * 
     * @return IndexSequence of the MatrixRows of this ArrayMatrix
     */
    public Sequence<? extends Sequence<Entry>> getColumns();

    /**
     * Returns the {@link Sequence} of the rows of this {@link Matrix}, each
     * provided as a {@link Sequence}.
     * 
     * @return IndexSequence of the MatrixRows of this ArrayMatrix
     */
    public Sequence<? extends Sequence<Entry>> getRows();

    /**
     * <p>
     * Returns a {@link MatrixTraversible} providing a {@link MatrixTraverser}
     * traversing the Items of this {@link Matrix} using the specified
     * {@link MatrixTraversalOrder}.
     * </p>
     * <p>
     * Example:
     * </p>
     * 
     * <pre>
     * for (Integer matrixEntry : matrix.iteratedInOrder(VERTICAL)
     *     System.out.print(matrixEntry + " ");
     * </pre>
     * 
     * @param iterationOrder
     *        {@link MatrixTraversalOrder} used by the returned {@link Iterable}
     * 
     * @return {@link MatrixTraversible} providing a {@link MatrixTraverser}
     *         traversing the Items of this {@link Matrix} using the specified
     *         {@link MatrixTraversalOrder}.
     */
    public MatrixTraversible<Entry> traversedInOrder(final MatrixTraversalOrder iterationOrder);

    /**
     * Registers the {@link MatrixTraversalOrder} used by each {@link Traverser}
     * returned by {@link #createTraverser()}.
     * 
     * @param defaultIterationOrder
     *        {@link MatrixTraversalOrder} used by default {@link Traverser
     *        Traversers}
     */
    public void setDefaultTraversalOrder(final MatrixTraversalOrder defaultIterationOrder);
}