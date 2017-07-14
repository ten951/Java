package com.wyt.headfirst.thread.practical.guarded;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Darcy
 *         Created by Administrator on 2017/4/1.
 */
public class ConditionVarBlocker implements Blocker {
    private final Lock lock;
    private final Condition condition;

    public ConditionVarBlocker() {
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    /**
     * 在保护条件成立时执行目标动作;否则阻塞当前线程,知道保护条件成功!
     *
     * @param guardedAction 带保护条件的目标动作
     * @return
     * @throws Exception
     */
    @Override
    public <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception {
        lock.lockInterruptibly();//获得锁,但优先响应中断
        V result;
        try {
            final Predicate guard = guardedAction.guard;
            while (!guard.evaluate()) {
                condition.await();//当前线程等待
            }
            result = guardedAction.call();//执行被保护的方法
            return result;
        } finally {
            lock.unlock();//释放锁
        }
    }

    /**
     * 执行stateOperation锁指定的操作后,决定是否唤醒本Blocker所暂挂的所有线程中的一个线程
     *
     * @param stateOperation 更改状态的操作,其call方法的返回为true时,该方法才会唤醒被暂挂的线程
     * @throws Exception
     */
    @Override
    public void signalAfter(Callable<Boolean> stateOperation) throws Exception {
        lock.lockInterruptibly();//获得锁,但优先响应中断
        try {
            if (stateOperation.call()) {
                condition.signal();//唤醒等待队列中 随机一个线程
            }
        } finally {
            lock.unlock();//释放锁
        }
    }

    @Override
    public void signal() throws InterruptedException {
        lock.lockInterruptibly();//获得锁,但优先响应中断
        try {
            condition.signal();//唤醒等待队列中 随机一个线程
        } finally {
            lock.unlock();//释放锁
        }
    }

    /**
     * 执行stateOperation锁指定的操作后,决定是否唤醒本Blocker所暂挂的所有线程
     *
     * @param stateOperation 更改状态的操作,其call方法的返回为true时,该方法才会唤醒被暂挂的线程
     * @throws Exception
     */
    @Override
    public void broadcastAfter(Callable<Boolean> stateOperation) throws Exception {
        lock.lockInterruptibly();//获得锁,但优先响应中断
        try {
            if (stateOperation.call()) {
                condition.signalAll();//唤醒等待队列中所有线程
            }
        } finally {
            lock.unlock();//释放锁
        }
    }
}
