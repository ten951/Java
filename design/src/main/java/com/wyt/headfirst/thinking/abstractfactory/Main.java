package com.wyt.headfirst.thinking.abstractfactory;

import com.wyt.headfirst.thinking.abstractfactory.factory.Factory;
import com.wyt.headfirst.thinking.abstractfactory.factory.Link;
import com.wyt.headfirst.thinking.abstractfactory.factory.Page;
import com.wyt.headfirst.thinking.abstractfactory.factory.Tray;

/**
 *
 * 针对接口编程(抽象类)
 * @author Darcy
 *         Created by Administrator on 2017/3/28.
 */
public class Main {
    public static void main(String[] args) {
       // Factory factory = Factory.getFactory("com.wyt.headfirst.thinking.abstractfactory.listfactory.ListFactory");
        Factory factory = Factory.getFactory("com.wyt.headfirst.thinking.abstractfactory.tablefactory.TableFactory");
        Link people = factory.createLink(" 人民日报", "http://www.people.com.cn/");
        Link gmw = factory.createLink(" 光明日报", "http://www.gmw.cn/");

        Link us_yahoo = factory.createLink("Yahoo!", "http://www.yahoo.com/");
        Link jp_yahoo = factory.createLink("Yahoo!Japan", "http://www.yahoo.co.jp/");
        Link excite = factory.createLink("Excite", "http://www.excite.com/");
        Link google = factory.createLink("Google", "http://www.google.com/");

        Tray traynews = factory.createTray(" 日报 ");
        traynews.add(people);
        traynews.add(gmw);

        Tray trayyahoo = factory.createTray(" Yahoo ");
        trayyahoo.add(us_yahoo);
        trayyahoo.add(jp_yahoo);

        Tray traysearch = factory.createTray(" 检索引擎 ");
        traysearch.add(trayyahoo);
        traysearch.add(excite);
        traysearch.add(google);

        Page page = factory.createPage("LinkPage", " 王永天 ");
        page.add(traynews);
        page.add(traysearch);
        page.output();


    }
}
