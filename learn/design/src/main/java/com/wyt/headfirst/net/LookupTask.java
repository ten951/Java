package com.wyt.headfirst.net;

import java.net.InetAddress;
import java.util.concurrent.Callable;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/28.
 */
public class LookupTask implements Callable<String> {
    private String line;

    public LookupTask(String line) {
        this.line = line;
    }

    @Override
    public String call() throws Exception {
        int index = line.indexOf(" ");
        String address = line.substring(0, index);
        String theRest = line.substring(index);
        String hostName = InetAddress.getByName(address).getHostName();
        return hostName + " " + theRest;
    }
}
