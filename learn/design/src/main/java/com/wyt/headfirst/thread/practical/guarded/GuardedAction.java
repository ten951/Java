package com.wyt.headfirst.thread.practical.guarded;

import java.util.concurrent.Callable;

/**
 * @author Darcy
 *         Created by Administrator on 2017/4/1.
 */
public abstract class GuardedAction<V> implements Callable<V> {

    protected final Predicate guard;

    public GuardedAction(Predicate guard) {
        this.guard = guard;
    }
}
