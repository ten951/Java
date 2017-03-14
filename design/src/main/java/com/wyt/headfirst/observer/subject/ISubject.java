package com.wyt.headfirst.observer.subject;

import com.wyt.headfirst.observer.observer.IObserver;

import java.util.Observer;

/**
 * 出版者或者主题
 *
 * @author Darcy
 *         Created by Administrator on 2016/8/24.
 */
public interface ISubject {
    /**
     * 注册订阅者
     *
     * @param o 订阅者对象
     */
    void registerObserver(IObserver o);

    /**
     * 取消订阅
     *
     * @param o 订阅者对象
     */
    void removerObserver(IObserver o);

    /**
     * 通知所有订阅者
     */
    void notifyObservers();
}
