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

package org.jlib.container.matrix;

/**
 * AbstractIndexMatrixIterator traversing the elements of a
 * {@link FlexiblyTraversableMatrix} horizontally. That is, the traversal
 * algorithm is as follows:
 * 
 * <pre>
 * {@literal
 * foreach column
 *     foreach row
 *         process element at (column, row)}
 * </pre>
 * 
 * @param <Entry>
 *        type of elements stored in the ArraySequenceMatrix
 *        
 * @author Igor Akkerman
 */
public final class VerticalMatrixIterator<Entry>
extends EntityMatrixIterator<Entry> {

    /**
     * Creates a new {@link VerticalMatrixIterator} for the specified
     * {@link FlexiblyTraversableMatrix}.
     * 
     * @param matrix
     *        {@link FlexiblyTraversableMatrix} to traverse
     */
    public VerticalMatrixIterator(final FlexiblyTraversableMatrix<Entry> matrix) {
        super(matrix, matrix.getColumns());
    }
}
