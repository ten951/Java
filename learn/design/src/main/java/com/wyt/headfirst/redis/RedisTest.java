package com.wyt.headfirst.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * @author Darcy
 *         Created by Darcy on 2017/7/13.
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis conn = new Jedis("192.168.1.10");
        conn.auth("darcy");
    /*    conn.hset("darcy:test","1","yes");
        conn.hset("darcy:test","0","no");*/
        Map<String, String> stringStringMap = conn.hgetAll("darcy:test");
        System.out.println("stringStringMap = " + stringStringMap);
    }
}
