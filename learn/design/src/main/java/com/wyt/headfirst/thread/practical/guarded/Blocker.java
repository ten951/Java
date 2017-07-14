package com.wyt.headfirst.thread.practical.guarded;

import java.util.concurrent.Callable;

/**
 * @author Darcy
 *         Created by Administrator on 2017/4/1.
 */
public interface Blocker {

    /**
     * 在保护条件成立时执行目标动作;否则阻塞当前线程,知道保护条件成功!
     *
     * @param guardedAction 带保护条件的目标动作
     * @return
     * @throws Exception
     */
    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    /**
     * 执行stateOperation锁指定的操作后,决定是否唤醒本Blocker所暂挂的所有线程中的一个线程
     *
     * @param stateOperation 更改状态的操作,其call方法的返回为true时,该方法才会唤醒被暂挂的线程
     * @throws Exception
     */
    void signalAfter(Callable<Boolean> stateOperation) throws Exception;

    void signal() throws InterruptedException;

    /**
     * 执行stateOperation锁指定的操作后,决定是否唤醒本Blocker所暂挂的所有线程
     *
     * @param stateOperation 更改状态的操作,其call方法的返回为true时,该方法才会唤醒被暂挂的线程
     * @throws Exception
     */
    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
