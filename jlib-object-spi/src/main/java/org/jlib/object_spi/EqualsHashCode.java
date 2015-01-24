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

package org.jlib.object_spi;

/**
 * Unification of interfaces {@link Equals} and {@link HashCode}.
 * An implementation of this interface must ensure that the algorithms are compatible,
 * such that if {@code areEqual(object1, object2) == true} then {@code hashCode(object1) == hashCode(object2)}.
 *
 * @param <Obj>
 */
public interface EqualsHashCode<Obj>
extends Equals<Obj>,
        HashCode<Obj> {
    // unifying to provide matching equals/hashCode implementation
}