/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2018 Igor Akkerman
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

package org.jlib.operator;

import org.jlib.value.Accessor;

/**
 * {@link Accessor} operating on the held Vallue using an
 * {@link OptionalValueOperator}.
 *
 * @param <Value>
 *        type of the held value
 *
 * @author Igor Akkerman
 */
interface OperatedValue<Value>
    extends Accessor<Value> {

    /**
     * Operates on the held Vallue using the specified
     * {@link OptionalValueOperator}.
     *
     * @param operator
     *        {@link OptionalValueOperator} operating on the held Vallue
     */
    void operate(OptionalValueOperator<Value> operator);
}
