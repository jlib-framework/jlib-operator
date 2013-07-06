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

package org.jlib.test.junit.matchers.text;

import static org.apache.commons.lang3.StringUtils.isBlank;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;

public class IsStringBlank
extends TypeSafeMatcher<String> {

    /** sole {@link IsStringBlank} instance */
    private static final IsStringBlank INSTANCE = new IsStringBlank();

    /**
     * Returns the sole {@link IsStringBlank} instance.
     *
     * @return sole {@link IsStringBlank} instance
     */
    @Factory
    public static IsStringBlank blank() {
        return INSTANCE;
    }

    /**
     * Creates a new {@link IsStringBlank}.
     */
    private IsStringBlank() {
        super(String.class);
    }

    @Override
    public boolean matchesSafely(String string) {
        return isBlank(string);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("blank string");
    }
}
