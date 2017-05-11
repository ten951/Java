package com.wyt.headfirst.thread.twotermination;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Darcy
 *         Created by Administrator on 2017/5/10.
 */
public class TerminationToken {
    //使用volatile修饰 以保证无须显示锁的情况下改变量的内存可见性
    protected volatile boolean toShutdown = false;
    public final AtomicInteger reservations = new AtomicInteger(0);
    /**
     * 在多个可停止线程实例共享一个TerminationToken实例的情况下,该队列用于记录
     * 那些共享TerminationToken实例的可定制现场,以便尽可能减少锁的使用的情况下,实现这些线程的停止
     */
    private final Queue<WeakReference<Terminatable>> coordinatedThreads;

    public TerminationToken() {
        this.coordinatedThreads = new ConcurrentLinkedQueue<>();
    }

    public boolean isToShutdown() {
        return toShutdown;
    }
    protected void setToShutdown(boolean toShutdown) {
        this.toShutdown = toShutdown;
    }

    protected void register(Terminatable thread) {
        coordinatedThreads.add(new WeakReference<>(thread));
    }

    /**
     * 通知TerminationToken实例：共享该实例的所有可停止线程中的一个线程停止了， 以便其停止其它未被停止的线程。
     *
     * @param thread
     *          已停止的线程
     */
    protected void notifyThreadTermination(Terminatable thread) {
        WeakReference<Terminatable> wrThread;
        Terminatable otherThread;
        while (null != (wrThread = coordinatedThreads.poll())) {
            otherThread = wrThread.get();
            if (null != otherThread && otherThread != thread) {
                otherThread.terminate();
            }
        }
    }
}
