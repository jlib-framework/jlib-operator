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

import java.util.function.Consumer;

import org.jlib.operator.OperatorException;

/**
 * {@link Observer} built from specified {@link Value} {@link Consumer}s.
 * That is, its methods {@link #before}, {@link #afterSuccess} and {@link #afterFailure} use previously specified
 * {@link Value} {@link Consumer}s.
 *
 * @param <Value>
 *        type of observed value
 */
public class ConsumersObserver<Value>
implements Observer<Value> {

    private Consumer<Value> beforeConsumer = value -> {};

    private Consumer<Value> afterSuccessConsumer = value -> {};

    private Consumer<Value> afterFailureConsumer = value -> {};

    ConsumersObserver() {}

    public ConsumersObserver<Value> beforeDo(final Consumer<Value> beforeConsumer) {
        this.beforeConsumer = beforeConsumer;

        return this;
    }

    public ConsumersObserver<Value> afterSuccessDo(final Consumer<Value> afterSuccessConsumer) {
        this.afterSuccessConsumer = afterSuccessConsumer;

        return this;
    }

    public ConsumersObserver<Value> afterFailureDo(final Consumer<Value> afterFailureConsumer) {
        this.afterFailureConsumer = afterFailureConsumer;

        return this;
    }

    @Override
    public void before(final Value value)
    throws RuntimeException {
        beforeConsumer.accept(value);
    }

    @Override
    public void afterSuccess(final Value value)
    throws RuntimeException {
        afterSuccessConsumer.accept(value);
    }

    @Override
    public void afterFailure(final Value value, final OperatorException operatorException)
    throws RuntimeException {
        afterFailureConsumer.accept(value);
    }
}
