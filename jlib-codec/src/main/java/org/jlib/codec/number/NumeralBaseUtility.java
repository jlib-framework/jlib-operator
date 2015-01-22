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

package org.jlib.codec.number;

import org.jlib.core.math.InvalidNumeralBaseException;

public class NumeralBaseUtility {

    private NumeralBaseUtility() {}

    /**
     * Returns a representation of ths specified number to the specified base,
     * such that {@code sum(result[index] [exp] index) == number}.
     *
     * @param number
     *        integer value to represent
     *
     *  @param base
     *        integer specifying the base of the representation
     *
     * @throws InvalidNumeralBaseException
     *         if {@code base < 1}
     */
    public static int[] toBase(int number, final int base)
    throws InvalidNumeralBaseException {

        if (base < 1)
            throw new InvalidNumeralBaseException(base);

        int maximumValueForCurrentDigitsCount;
        int digitsCount = 0;

        for (maximumValueForCurrentDigitsCount = base;
             maximumValueForCurrentDigitsCount <= number;
             maximumValueForCurrentDigitsCount *= base)
            digitsCount++;

        final int[] digits = new int[digitsCount + 1];

        for (int digitIndex = digitsCount; digitIndex >= 0; digitIndex--) {
            digits[digitIndex] = 0;
            maximumValueForCurrentDigitsCount /= base;
            while (number - maximumValueForCurrentDigitsCount >= 0) {
                number -= maximumValueForCurrentDigitsCount;
                digits[digitIndex]++;
            }
        }

        return digits;
    }
}