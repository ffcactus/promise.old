package com.promise.common;

/**
 * PromiseExecutionState represents the execution state.
 */
public enum PromiseExecutionState
{
    /**
     * Initialized, but not running.
     */
    READY,

    /**
     * Running at the moment.
     */
    RUNNING,

    /**
     * The execution is terminated.
     */
    TERMINIATED,
}
