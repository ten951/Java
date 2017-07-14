package com.wyt.headfirst.thread.practical.guarded;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

/**
 * 负责连接告警服务器,并发送告警信息至告警服务器
 *
 * @author Darcy
 *         Created by Administrator on 2017/4/1.
 */
public class AlarmAgent {
    //用于记录AlarmAgent是否连接上告警服务器
    private volatile boolean connectedToServer = false;

    //模式角色,GuardedSuspension.Predicate
    private final Predicate agentConnected = () -> connectedToServer;

    //模式角色:GuardedSuspension.Blocker
    private final Blocker blocker = new ConditionVarBlocker();
    //心跳定时器
    private final Timer heartbeatTimer = new Timer(true);

    /**
     * 发送告警信息
     *
     * @param alarm 告警信息
     * @throws Exception
     */
    public void sendAlarm(final AlarmInfo alarm) throws Exception {
        //模式角色:GuardedSuspension.GuardedAction
        GuardedAction<Void> guardedAction = new GuardedAction<Void>(agentConnected) {

            /**
             * Computes a result, or throws an exception if unable to do so.
             *
             * @return computed result
             * @throws Exception if unable to compute a result
             */
            @Override
            public Void call() throws Exception {
                doSendAlarm(alarm);
                return null;
            }
        };
        blocker.callWithGuard(guardedAction);
    }

    //通过网络连接将告警信息发送给告警服务器
    private void doSendAlarm(AlarmInfo alarm) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }
    }

    public void init() {
        //告警服务器连接线程
        Thread thread = new Thread(new ConnectingTask());
        thread.start();
        heartbeatTimer.schedule(new HeartbeatTask(), 60000, 2000);
    }

    public void disconnect() {
        connectedToServer = false;
    }

    protected void onConnected() {
        try {
            blocker.signalAfter(() -> {
                connectedToServer = true;
                return Boolean.TRUE;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDisconnected() {
        connectedToServer = false;
    }

    //负责与告警服务器建立网络连接
    private class ConnectingTask implements Runnable {

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            onConnected();
        }
    }

    //心跳定时任务:定时检查与告警服务器的连接是否正常,发现连接异常后自动重新连接
    private class HeartbeatTask extends TimerTask {

        /**
         * The action to be performed by this timer task.
         */
        @Override
        public void run() {
            if (testConnection()) {
                onDisconnected();
                reconnect();
            }
        }

        private boolean testConnection() {
            return true;
        }

        private void reconnect() {
            ConnectingTask connectingThread = new ConnectingTask();
            connectingThread.run();
        }
    }


}
