package com.promise.common;

/**
 * PromiseExecutionResult represents the execution result.
 */
public enum PromiseExecutionResultState
{
    /**
     * Everything OK.
     */
    FINISHED,

    /**
     * With warning.
     */
    WARNING,

    /**
     * With error.
     */
    ERROR,

    /**
     * The execution is abort.
     */
    ABORT,

    /**
     * If no result yet.
     */
    UNKNOWN
}
