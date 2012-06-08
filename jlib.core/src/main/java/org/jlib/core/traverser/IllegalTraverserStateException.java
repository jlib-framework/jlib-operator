package org.jlib.core.traverser;

import org.jlib.core.IllegalJlibStateException;

/**
 * {@link IllegalJlibStateException} thrown when an illegal state has been
 * reached in a {@link Traverser}.
 * 
 * @author Igor Akkerman
 */
public abstract class IllegalTraverserStateException
extends IllegalJlibStateException {

    /** serialVersionUID */
    private static final long serialVersionUID = -5070211173782251202L;

    /** traversed {@link Traversible} */
    private final Traversible<?> traversible;

    /**
     * Creates a new {@link IllegalTraverserStateException}.
     * 
     * @param traversible
     *        traversed {@link Traversible}
     */
    public IllegalTraverserStateException(final Traversible<?> traversible) {
        this(traversible, null);
    }

    /**
     * Creates a new {@link IllegalTraverserStateException}.
     * 
     * @param traversible
     *        traversed {@link Traversible}
     * 
     * @param cause
     *        {@link Throwable} that caused this
     *        {@link IllegalThreadStateException}
     */
    public IllegalTraverserStateException(final Traversible<?> traversible, final Throwable cause) {
        this(traversible, "{1}", cause);
    }

    /**
     * Creates a new {@link IllegalTraverserStateException}.
     * 
     * @param traversible
     *        traversed {@link Traversible}
     * 
     * @param messagePattern
     *        {@link String} specifying the error message pattern
     * 
     * @param messageParameters
     *        comma separated sequence of {@link Object} instances specifying
     *        the message parameters
     */
    public IllegalTraverserStateException(final Traversible<?> traversible, final String messagePattern,
                                          final Object... messageParameters) {
        this(traversible, messagePattern, (Throwable) null, messageParameters);
    }

    /**
     * Creates a new {@link IllegalTraverserStateException}.
     * 
     * @param traversible
     *        traversed {@link Traversible}
     * 
     * @param messagePattern
     *        {@link String} specifying the error message pattern
     * 
     * @param cause
     *        {@link Throwable} that caused this
     *        {@link IllegalThreadStateException}
     * 
     * @param messageParameters
     *        comma separated sequence of {@link Object} instances specifying
     *        the message parameters
     */
    public IllegalTraverserStateException(final Traversible<?> traversible, final String messagePattern,
                                          final Throwable cause, final Object... messageParameters) {
        super(messagePattern, cause, traversible, messageParameters);

        this.traversible = traversible;
    }

    /**
     * Returns the traversed {@link Traversible}.
     * 
     * @return traversed {@link Traversible}
     */
    public Traversible<?> getTraversible() {
        return traversible;
    }
}
