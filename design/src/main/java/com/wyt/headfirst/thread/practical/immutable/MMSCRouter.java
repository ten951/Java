package com.wyt.headfirst.thread.practical.immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Darcy
 *         Created by Administrator on 2017/3/30.
 */
public final class MMSCRouter {

    //volatile 修饰保证多线程环境下该变量的可见性
    private static volatile MMSCRouter instance = new MMSCRouter();
    //维护手机号前缀到彩信中心之间的映射关系
    private final Map<String, MMSCInfo> routeMap;


    public MMSCRouter() {
        this.routeMap = MMSCRouter.retrieveRouteMapFromDB();
    }


    private static Map<String, MMSCInfo> retrieveRouteMapFromDB() {
        return new HashMap<>();
    }

    public static MMSCRouter getInstance() {
        return instance;
    }

    public static void setInstance(MMSCRouter newInstance) {
        instance = newInstance;
    }

    /**
     * 根据手机号前缀获取对应的彩信中心信息
     *
     * @param msisdnPrefix 手机号前缀
     * @return 彩信中心信息
     */
    public MMSCInfo getMMC(String msisdnPrefix) {
        return routeMap.get(msisdnPrefix);
    }

    private static Map<String, MMSCInfo> deepCopy(Map<String, MMSCInfo> m) {
        Map<String, MMSCInfo> result = new HashMap<>();
        for (String key : result.keySet()) {
            result.put(key, new MMSCInfo(m.get(key)));
        }
        return result;
    }

    public Map<String, MMSCInfo> getRouteMap() {
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }
}
