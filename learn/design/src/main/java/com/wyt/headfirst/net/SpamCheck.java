package com.wyt.headfirst.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/28.
 */
public class SpamCheck {
    public static final String BLACKHOlE = "www.rz158.com";

    private static boolean isSpammer(String arg) {
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACKHOlE;
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            InetAddress.getByName(query);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }

}
