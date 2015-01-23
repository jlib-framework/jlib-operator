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

package org.jlib.core.reflection;

import org.jlib.core.property.PropertyNotSetException;
import org.jlib.core.property.PropertyUtility;

/**
 * <p>
 * Utility class providing an easy-to-use interface for creating objects using
 * reflection. It consists of static methods solely. Objects can be created
 * providing their class, an Object of their class, their class name or the name
 * of a system property specifying their class name.
 * </p>
 * <p>
 * Additionally, when using the methods of the class {@link Class} for
 * instantiating classes using reflection, many Exceptions have to be caught.
 * Usually, the application that uses these methods makes no difference why the
 * instantiation failed. They simply catch {@code Exception} to perform the
 * exception handling. This is not a clean way to handle this probli.
 * </p>
 * <p>
 * In contrast, the methods of this class throw one single checked exception,
 * {@link ClassInstantiationException}, so that the application only needs to
 * catch that single one. The {@code ClassInstantiationException} contains the
 * information about the original Exception that caused it so if the actual
 * reason is of interest, it still can be retrieved.
 * </p>
 *
 * @author Igor Akkerman
 */
//TODO: review thrown exceptions
public final class ReflectionUtility {

    /**
     * <p>
     * Creates a new instance of the specified class.
     * </p>
     * <p>
     * This method calls {@link Class#newInstance()}. If it throws an Exception,
     * it is wrapped into a {@code ClassInstantiationException} and thrown by
     * this method.
     * </p>
     *
     * @param <Obj>
     *        type of the object to create
     *
     * @param clazz
     *        Class to instantiate
     *
     * @return a new instance of {@code clazz}
     *
     * @throws ClassInstantiationException
     *         if the instantiation of the specified class fails
     */
    public static <Obj> Obj newInstanceOf(final Class<? extends Obj> clazz)
    throws ClassInstantiationException {
        try {
            return clazz.newInstance();
        }
        catch (final InstantiationException | IllegalAccessException exception) {
            throw new ClassInstantiationException(clazz, exception);
        }
    }

    /**
     * <p>
     * Creates a new instance of the class of the specified Object.
     * </p>
     * <p>
     * This method calls {@link Class#newInstance()}. If that method throws a
     * Exception of any kind, it is wrapped into a
     * {@code ClassInstantiationException}, which is then thrown by this method.
     * </p>
     *
     * @param <Obj>
     *        type of the object to create
     * @param object
     *        Object instance of the class to instantiate
     * @return a new instance of the specified class
     * @throws ClassInstantiationException
     *         if the instantiation of the specified class fails
     */
    @SuppressWarnings("unchecked")
    public static <Obj> Obj newInstanceOfClassOf(final Obj object)
    throws ClassInstantiationException {
        return newInstanceOf((Class<? extends Obj>) object.getClass());
    }

    /**
     * <p>
     * Creates a new instance of the class specified by its name.
     * </p>
     * <p>
     * This method calls {@link Class#newInstance()}. If that method throws a
     * Exception of any kind, it is wrapped into a
     * {@code ClassInstantiationException}, which is then thrown by this method.
     * </p>
     *
     * @param <Obj>
     *        type of the object to create
     * @param className
     *        String specifying the name of the class to instantiate
     * @return a new instance of the specified class
     * @throws ClassInstantiationException
     *         if the instantiation of the specified class fails or the
     *         instantiated object is not an instance of the class represented
     *         by {@code Obj} or a subclass
     */
    @SuppressWarnings("unchecked")
    public static <Obj> Obj newInstanceOf(final String className)
    throws ClassInstantiationException {
        try {
            return newInstanceOf((Class<? extends Obj>) Class.forName(className));
        }
        catch (final ClassNotFoundException exception) {
            throw new ClassInstantiationException(className, exception);
        }
    }

    /**
     * Creates a new instance of the class defined in the specified system property.
     *
     * @param <Obj>
     *        type of the object to create
     *
     * @param propertyName
     *        String specifying the name of the system property in which the
     *        class name is defined
     *
     * @return a new instance of the specified class
     *
     * @throws SecurityException
     *         if a security manager exists and its {@code checkPropertyAccess}
     *         method doesn't allow access to the specified system property
     *
     * @throws IllegalArgumentException
     *         if (@code propertyName} is the empty {@link String}
     *
     * @throws PropertyNotSetException
     *         if the specified system property is not set
     *
     * @throws ClassInstantiationException
     *         <ul>
     *         <li>if the specified system property is not set (cause is a
     *         {@link PropertyNotSetException}) or</li>
     *         <li>if the instantiation of the specified class fails (cause is
     *         one of the exceptions thrown by {@link Class#forName(String)}) or
     *         </li>
     *         <li>the instantiated object is not an instance of the class
     *         represented by {@code Obj} or a subclass (cause is a
     *         {@link ClassCastException}).</li>
     *         </ul>
     */
    public static <Obj> Obj newInstanceByProperty(final String propertyName)
    throws SecurityException, PropertyNotSetException, ClassInstantiationException {
        final String className = PropertyUtility.getProperty(propertyName);
        // the cast is necessary to the Sun compiler, not to the Eclipse compiler
        return newInstanceOf(className);
    }

    /** no visible constructor */
    private ReflectionUtility() {
    }
}
