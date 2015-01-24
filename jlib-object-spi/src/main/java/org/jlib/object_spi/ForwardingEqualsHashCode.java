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

import org.checkerframework.checker.nullness.qual.Nullable;

public class ForwardingEqualsHashCode<Obj>
implements EqualsHashCode<Obj> {

    private final Equals<Obj> equals;
    private final HashCode<Obj> hashCode;

    public ForwardingEqualsHashCode(final Equals<Obj> equals, final HashCode<Obj> hashCode) {
        this.equals = equals;
        this.hashCode = hashCode;
    }

    @Override
    public boolean areEqual(final Obj object1, @Nullable final Object object2) {
        return equals.areEqual(object1, object2);
    }

    @Override
    public int hashCode(final Obj object) {
        return hashCode.hashCode(object);
    }
}