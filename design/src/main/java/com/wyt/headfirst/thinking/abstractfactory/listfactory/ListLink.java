package com.wyt.headfirst.thinking.abstractfactory.listfactory;

import com.wyt.headfirst.thinking.abstractfactory.factory.Link;

/**
 * ListLink和Item  就是典型的模板方法模式
 * Link是Item的子类,但是没有实现makeHTML() 所以依然是抽象类
 * ListLink是Link的装饰器
 * ListLink实现makeHTML() 确定具体返回什么HTML字符串
 *
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public class ListLink extends Link {
    public ListLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "   <li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}
