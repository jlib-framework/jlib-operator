/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2015 Igor Akkerman
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

package org.jlib.operator.observer;

import org.jlib.operator.OperatorException;

/**
 * Observer of an operation on a {@link Value}.
 *
 * @param <Value>
 *        type of removed value
 *
 * @author Igor Akkerman
 */
public interface Observer<Value> {

    /**
     * Performs actions before the targeted operation.
     *
     * @param value
     *        {@link Value} operated on
     *
     * @throws RuntimeException
     *         if the operation cannot be completed normally
     */
    @SuppressWarnings("ProhibitedExceptionDeclared")
    default void before(final Value value)
        throws RuntimeException {
        // intentionally without functionality
    }

    /**
     * Performs actions after the targeted operation has been successfully
     * executed.
     *
     * @param value
     *        {@link Value} operated on
     *
     * @throws RuntimeException
     *         if the operation cannot be completed normally
     */
    @SuppressWarnings("ProhibitedExceptionDeclared")
    default void afterSuccess(final Value value)
        throws RuntimeException {
        // intentionally without functionality
    }

    /**
     * Performs actions after the targeted operation has failed throwing an {@link OperatorException}.
     *
     * @param value
     *        {@link Value} operated on
     *
     * @param operatorException
     *        {@link OperatorException} that caused the operation to fail
     *
     * @throws RuntimeException
     *         if the operation cannot be completed normally
     */
    @SuppressWarnings({ "ProhibitedExceptionDeclared", "UnusedParameters" })
    default void afterFailure(final Value value, final OperatorException operatorException)
        throws RuntimeException {
        // intentionally without functionality
    }
}
